package lab1;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;

class NodeA1 {
	int s;
	// 0所在位置
	int zero_row, zero_column;
	// 状态数组
	int[][] state;
	// 将数组转化为String（或者可以重写toString方法）
	String statestring;
	// 记录父节点
	NodeA1 father;
	// 记录移动的次数 g(n)
	int counts;
	// 记录状态各点距离各自终点的 曼哈顿距离 h(n)
	int distance;
	//移动到这儿的方向
	int direction;
	
	public NodeA1(NodeA1 father, int[][] state,int[][] GOAL_STATE,int direction, int zero_row, int zero_column,int m) {
		this.s=m;
		this.direction=direction;
		this.father = father;
		this.state = state;
		this.zero_row = zero_row;
		this.zero_column = zero_column;
		this.statestring = Arrays.deepToString(state);

		if (null == this.father)
			this.counts = 0;
		else
			this.counts = this.father.counts + 1;

		// 计算曼哈顿距离
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				// 不用计算0的
				if (state[i][j] == 0)
					continue;
				
				
				int x = 0 ;// 该数字的终点行数
				int y = 0;// 该数字的终点列数
				for (int p = 0; p < m; p++) {
					for (int q = 0; q < m; q++) {
						if(GOAL_STATE[p][q]==state[i][j]) {
							x=p;
							y=q;
							break;
						}	
					}}
				
				this.distance += Math.abs(x - i) + Math.abs(y - j);
			}
		}

	}
}


public class A1  extends Myinit{


//	// 绝对不可能到达目标状态的状态
//	public static final int[][] WRONG_STATE = { { 0, 1, 2 }, { 3, 4, 5 }, { 7,6,8 } };
	public static final int[][] DIRECTIONS = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };//左上右下
	public static int m;
	public int[][] START_STATE;
	public static int[][] GOAL_STATE;
	private int zeroIdx;
	
	// 已经到达过的状态 HashSet 无序且唯一（即不重复）
	public Set<String> visited = new HashSet<String>();
	private String result = "";

	A1(int l){
		super(l);
		m=l;
    	
   confirm.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent actionEvent) {
                  if(originField.getText().isEmpty() || goalField.getText().isEmpty() ){
                      JOptionPane.showMessageDialog(null,  "输入为空！","请输入初始序列和目标序列", JOptionPane.ERROR_MESSAGE);
                  }
                  else if(!originField.getText().contains(" ") ||!goalField.getText().contains(" ")  ){
                      JOptionPane.showMessageDialog(null, "请在数字间输入空格","请输入初始序列和目标序列",JOptionPane.ERROR_MESSAGE);
                  }
                  else{
                      IO();
                      if(!isSovable()){
                          JOptionPane.showMessageDialog(null, "这两个状态之间不可达。", "不可达状态！", JOptionPane.INFORMATION_MESSAGE);
                      }
                      else{
                          
                          A1Search();                                         
                          drawRoute();
                          
                      }
                  }

              }
          });
   this.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent arg0) {
           A1.this.dispose();
           System.exit(0);
       }
   });
		
}
    private void swap(int i, int j){
        String temp = blocks[i].getText();
        blocks[i].setForeground(Color.black);
        blocks[i].setText(blocks[j].getText());
        blocks[j].setText(temp);
        blocks[j].setForeground(Color.red);
    }
    private void drawRoute(){
        // 开启多线程，更新显示进程，实现实时刷新
        new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < result.length(); i++){
                    String d = result.substring(i, i+1);
                    if(d.contentEquals("u")){
                        swap(zeroIdx, zeroIdx-m);
                        zeroIdx -=m;
                    }
                    else if(d.contentEquals("d")){
                        swap(zeroIdx, zeroIdx+m);
                        zeroIdx += m;
                    }
                    else if(d.contentEquals("l")){
                        swap(zeroIdx, zeroIdx-1);
                        zeroIdx -= 1;
                    }
                    else{
                        swap(zeroIdx, zeroIdx+1);
                        zeroIdx += 1;
                    }
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void printResult(NodeA1 node){
        if(node.father == null) {
            return;
        }
        printResult(node.father);
        if(node.direction == 1) {
            result += "u";
            resultField.setText(resultField.getText() + "up ");
        }
        else if(node.direction  == 2){
            result += "d";
            resultField.setText(resultField.getText() + "down ");
        }
        else if(node.direction == 3){
            result += "l";
            resultField.setText(resultField.getText() + "left ");
        }
        else if(node.direction == 4){
            result += "r";
            resultField.setText(resultField.getText() + "right ");
        }
       
    }
	public void A1Search(){
		Runtime r = Runtime.getRuntime();
		r.gc();
		// 主方法开始运行的时间
		long startTime = System.currentTimeMillis();
		// 主方法开始时的剩余内存
		long startMem = r.freeMemory();

// **************************************** 真正的主方法: ********************************************

		// 优先队列，每次 poll 的都是 f(n)最小的状态节点,用Comparator接口实现
		PriorityQueue<NodeA1> pqueue = new PriorityQueue<>(new Comparator<NodeA1>() {
			public int compare(NodeA1 o1, NodeA1 o2) {
				return o1.distance + o1.counts - o2.distance - o2.counts;
			}
		});
	

		// 初始状态节点
		NodeA1 start = new NodeA1(null, START_STATE,GOAL_STATE,0, zeroIdx/m, zeroIdx%m,m);
		pqueue.add(start);
		visited.add(start.statestring);

		// 是否到达目标状态
		boolean flag = false;
		// 总共尝试的次数，注意区别于 node.counts
		int countsloop = 0;
		while (!pqueue.isEmpty()) {
			NodeA1 node = pqueue.poll();
			if (node.statestring.equals(Arrays.deepToString(GOAL_STATE))) {
				System.out.println("能够到达目标，总共尝试移动次数为：	" + countsloop + "\n成功的路径如下：");
				
				long endTime = System.currentTimeMillis();
              	// 主方法结束剩余内存
              	long endMem = r.freeMemory();
              	dataField1.setText("尝试次数："+countsloop+"次");
              	dataField2.setText(dataField2.getText()+"A1用时： " + (endTime - startTime) + " ms");
              	dataField2.setText(dataField2.getText()+"  A1消耗内存： " + ( endMem-startMem ) / 1024 + " Kb");
    			dataField3.setText("最少"+node.counts+"步");
              	printResult(node);
				
				flag = true;

				return;
			}

			// 错误状态
//			if (node.statestring.equals(Arrays.deepToString(WRONG_STATE)))
//				continue;

			// A*算法，基于BFS的四个方向
			for (int[] di : DIRECTIONS) {
				int zero_r = node.zero_row + di[0];
				int zero_c = node.zero_column + di[1];
				int dir=di[0]*m+di[1];
				// 若超出界外则进入下个方向
				if (zero_r == -1 || zero_c == -1 || zero_r == m|| zero_c == m)
					continue;

				// 不包括“碰壁”的情况，只算交换成功的次数
				countsloop++;

				// 数组中0与方向上的数字交换
				int[][] newstate = new int[m][m];
				int i = 0;
				for (int[] row : node.state)
				newstate[i++] = row.clone();
				newstate[node.zero_row][node.zero_column] = newstate[zero_r][zero_c];
				newstate[zero_r][zero_c] = 0;
				int direct=0;
                if (dir==-m) direct=1;	
                else if(dir==m) direct=2;	
                else if(dir==-1) direct=3;
                else if(dir==1) direct=4;

				NodeA1 newnode = new NodeA1(node, newstate, GOAL_STATE,direct,zero_r, zero_c,m);
				// 若该节点已被访问，进入下个方向，否则集合添加该节点
				if (visited.contains(newnode.statestring))
					continue;
				pqueue.add(newnode);
				visited.add(newnode.statestring);
			}
		}
		if (flag == false)
			System.out.println("无法到达目标状态");

		
			
	}
	
    private boolean isSovable(){
    	int[] origin=new int[m*m];
    	int[] goal=new int[m*m];
    	for(int i=0;i<m;i++) {
    		for(int j=0;j<m;j++) {
    			origin[i*m+j]=START_STATE[i][j];
    			goal[i*m+j]=GOAL_STATE[i][j];
			
    		}
    	}    	
        int originReverseValue = 0;
        int goalReverseValue = 0;
        for(int i = 1; i < m*m; i++){
            if(origin[i] != 0) {
                for (int j = 0; j < i; j++) {
                    if (origin[j] > origin[i]) originReverseValue++;
                }
            }
        }
        for(int i = 1; i < m*m; i++){
            if(goal[i] != 0){
                for(int j = 0; j < i; j++){
                    if(goal[j] > goal[i]) goalReverseValue++;
                }
            }
        }
        if(originReverseValue % 2 == goalReverseValue % 2){
            return true;
        }
        else
            return false;
    }

    public void IO(){
    	START_STATE = new int[m][m];
    	GOAL_STATE = new int[m][m];
        String originString = originField.getText();
        
        System.out.print(originString);
       
        String []startarr=originString.split(" ");
        for(int i = 0; i < m*m; i++){
        	
        		START_STATE[i/m][i%m] = Integer.parseInt(startarr[i]);
            if(START_STATE[i/m][i%m]== 0)
                {
            	zeroIdx = i;
                blocks[i].setForeground(Color.red);
                }
            blocks[i].setText(String.valueOf(START_STATE[i/m][i%m]));   		           
        }
        String goalString = goalField.getText();
        
        String []goalarr=goalString.split(" ");
        
        for(int i = 0; i < m*m; i++){
        	GOAL_STATE[i/m][i%m] = Integer.parseInt(goalarr[i]);
        }
//        startId = cantor(origin);
//        goalId = cantor(goal);
//        System.out.println("startId, goalId:" + startId + ", " + goalId);
    }
	
//	public static void main(String[] args) {
//		new A1(3);
//
//}
}
