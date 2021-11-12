package lab1;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;




/**
 * ����Ϊ��װ�Ľڵ���
 * 
 * @author 
 *
 */
class NodeBFS {
	//0����λ��
	int s;///��С
	int zero_row,zero_column;
	
	int[][] state;
	//������ת��ΪString�����߿�����дtoString������
	String statestring;
	//��¼���ڵ�
	NodeBFS father;
	//��¼�ƶ��Ĵ���
	int counts;
	//�ƶ�������ķ���
	int direction;
	
	public NodeBFS(NodeBFS father,int[][] state,int direction,int zero_row,int zero_column,int m) {
		this.s=m;
		this.father=father;
		this.state=state;
		this.direction=direction;
		this.zero_row=zero_row;
		this.zero_column=zero_column;
		this.statestring=Arrays.deepToString(state);
		if(null==this.father)
			this.counts=0;
		else
			this.counts=this.father.counts+1;
	}
//	public void print() {
//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(state[i][j]+"\t");
//				
//			}
//			System.out.println();
//		}
//		
//	}
}

public class BFS extends Myinit{
	
	public static final int[][] DIRECTIONS = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };//��������
	public static int m;
	public int[][] START_STATE;
	public int[][] GOAL_STATE;
	// ���У��Ƚ��ȳ�
		
	public Set<String> visited = new HashSet<String>();
	private String result = "";
	private int zeroIdx;
	
    BFS(int l) {  
    	super(l);
    	m=l;
    	
   confirm.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent actionEvent) {
                  if(originField.getText().isEmpty() || goalField.getText().isEmpty() ){
                      JOptionPane.showMessageDialog(null,  "����Ϊ�գ�","�������ʼ���к�Ŀ������", JOptionPane.ERROR_MESSAGE);
                  }
                  else if(!originField.getText().contains(" ") ||!goalField.getText().contains(" ")  ){
                      JOptionPane.showMessageDialog(null, "�������ּ�����ո�","�������ʼ���к�Ŀ������",JOptionPane.ERROR_MESSAGE);
                  }
                  else{
                      IO();
                      if(!isSovable()){
                          JOptionPane.showMessageDialog(null, "������״̬֮�䲻�ɴ", "���ɴ�״̬��", JOptionPane.INFORMATION_MESSAGE);
                      }
                      else{
                          
                          BFSSearch();                                         
                          drawRoute();
                          
                      }
                  }

              }
          });
   this.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent arg0) {
           BFS.this.dispose();
           System.exit(0);
       }
   });
	
}
    public void BFSSearch(){
    	
    	Runtime r = Runtime.getRuntime();
      	r.gc();
      	long startTime = System.currentTimeMillis();
    	// ��������ʼʱ��ʣ���ڴ�
    	long startMem = r.freeMemory();   
    	
    	// ���У��Ƚ��ȳ�
    	Queue<NodeBFS> queue = new LinkedList<NodeBFS>();
    	
    	// ��ʼ״̬�ڵ�
    	NodeBFS start = new NodeBFS(null, START_STATE, 0,zeroIdx/m, zeroIdx%m,m); 	
    	queue.offer(start);
    	visited.add(start.statestring);
    	boolean flag = false;
    	int countsloop=0;
    	while (!queue.isEmpty()) {
    		NodeBFS node = queue.remove();
    		System.out.println(node.statestring);
    		// ���ýڵ��ΪĿ��״̬,������и��ڵ�
    		if (node.statestring.equals(Arrays.deepToString(GOAL_STATE))) {
    			System.out.println("�ܹ�����Ŀ��");
    			
    			
    			long endTime = System.currentTimeMillis();
              	// ����������ʣ���ڴ�
              	long endMem = r.freeMemory();
              	dataField1.setText("���Դ�����"+countsloop+"��");
              	dataField2.setText(dataField2.getText()+"BFS��ʱ�� " + (endTime - startTime) + " ms");
              	dataField2.setText(dataField2.getText()+"  BFS�����ڴ棺 " + ( endMem-startMem ) / 1024 + " Kb");
    			dataField3.setText("����"+node.counts+"��");
              	printResult(node);
    			
    			flag = true;
    		    return;
    		}
    		 if(node.zero_row*m+node.zero_column == -1){
                 System.out.println("Some error occur!");
                 return;
             }
    		 
    		//BFS�㷨���ĸ�����
    		for (int[] di : DIRECTIONS) {
    			int zero_r = node.zero_row + di[0];
    			int zero_c = node.zero_column + di[1];
    			//��dir�жϷ���
    			int dir=di[0]*m+di[1];
    			// ����������������¸�����
    			if (zero_r == -1 || zero_c == -1 || zero_r == m || zero_c == m)
    				continue;
    			
    			//�����������ڡ��������ֻ�㽻���ɹ��Ĵ���
    			countsloop++;
    			//������״̬���ҽ���
    			int[][] newstate=new int[m][m];
    			int i=0;
    			for(int[] row:node.state)
    			newstate[i++]=row.clone();
    			newstate[node.zero_row][node.zero_column] = newstate[zero_r][zero_c];
    			newstate[zero_r][zero_c] = 0;
    			
    			int direct=0;
                if (dir==-m) direct=1;	
                else if(dir==m) direct=2;	
                else if(dir==-1) direct=3;
                else if(dir==1) direct=4;
               
    			NodeBFS newnode = new NodeBFS(node, newstate,direct, zero_r, zero_c,m);
    			// ���ýڵ��ѱ����ʣ������¸����򣬷��򼯺���Ӹýڵ�
    			if (visited.contains(newnode.statestring))
    				continue;
    			
    			queue.add(newnode);
    			visited.add(newnode.statestring);
    			
    			
    			
    		}
    		  if(flag)
	                 break;	 
    	}
    	 
    	
       	
    }
    private void swap(int i, int j){
        String temp = blocks[i].getText();
        blocks[i].setForeground(Color.black);
        blocks[i].setText(blocks[j].getText());
        blocks[j].setText(temp);
        blocks[j].setForeground(Color.red);
    }
    private void drawRoute(){
        // �������̣߳�������ʾ���̣�ʵ��ʵʱˢ��
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
    public void printResult(NodeBFS node){
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



}


