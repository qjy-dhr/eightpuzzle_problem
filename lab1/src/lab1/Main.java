package lab1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Main extends JFrame {
	public static String s;//算法类型
	
	public JButton confirm;
	 public JTextField sField;
	 public JTextField mField;
	 public JTextField dField;
	 public static int m;//长度
	 public static int d;//长度
	
	Main(){
		super("菜单");	
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		 int temp1=dimension.width;
		 int temp2=dimension.height;
		 dimension.width = (int) (dimension.width * 0.2);
	     dimension.height = (int) (dimension.height*0.35);
		 this.setLocation((int) ((temp1-dimension.width)/2),(int)((temp2-dimension.height)/2));
		 
		 Box tip = Box.createVerticalBox();
	        JLabel label = new JLabel("输入算法类型：(BFS、DFS、A1、A2、A3)");
	        tip.add(label);
	        sField = new JTextField();
	        sField.setFont(new Font("宋体", Font.PLAIN, 20));	        
	        tip.add(sField);
	        
	        JLabel label1 = new JLabel("几数码测试：");
	        tip.add(label1);
	        mField = new JTextField();
	        mField.setFont(new Font("宋体", Font.PLAIN, 20));	        
	        tip.add(mField);
	        
	        JLabel label2 = new JLabel("若使用DFS，请输入测试深度：");
	        tip.add(label2);
	        dField = new JTextField();
	        dField.setFont(new Font("宋体", Font.PLAIN, 20));	        
	        tip.add(dField);
	        
       
	        confirm = new JButton("开始测试：");
	        confirm.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent actionEvent) {
	                  if(sField.getText().isEmpty() || mField.getText().isEmpty() ){
	                      JOptionPane.showMessageDialog(null, "请输入算法类型和长度", "输入为空！", JOptionPane.ERROR_MESSAGE);
	                  }
	                  else{
	                     s=sField.getText();
	                     m=Integer.parseInt(mField.getText());
	                     m=(int) Math.sqrt(m+1);
	                     
	                 	if(s.equals("BFS")) {
	            			System.out.println("请输入长度：");
//	            		     m=sc.nextInt();
	            			
	            			new BFS(m);
	            			
	            		}
	            		//2是DFS
	            		else if(s.equals("DFS")) {
	            			d=Integer.parseInt(dField.getText());
	            			System.out.println("请输入长度：");
//	            			m=sc.nextInt();
	            			System.out.println("请输入深度：");
//	            			
	            			new DFS(m,d);
	            			
	            		}
	            		else if(s.equals("A1")) {
	            			System.out.println("请输入长度：");
//	            			m=sc.nextInt();
	            			System.out.println("请输入深度：");
//	            			
	            			new A1(m);
	            			
	            		}
	            		else if(s.equals("A2")) {
	            			System.out.println("请输入长度：");
//	            			m=sc.nextInt();
	            			System.out.println("请输入深度：");
//	            			
	            			new A2(m);
	            			
	            		}
	            		else if(s.equals("A3")) {
	            			System.out.println("请输入长度：");
//	            			m=sc.nextInt();
	            			System.out.println("请输入深度：");
//	            			
	            			new A3(m);
	            			
	            		}
	            		
	            		//调试案例
	            		if (m==4) {
	            			System.out.println("案例：");
	            			System.out.println("5 1 2 4 9 6 3 8 13 15 10 11 14 0 7 12");
	            			System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");	
	            			System.out.println();	
	            			System.out.println("11 9 4 15 1 3 0 12 7 5 8 6 13 2 10 14");
	            			System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");	
	            		}
	            		else if (m==3) {
	            			System.out.println("案例：");
	            			System.out.println("2 8 3 1 6 4 7 0 5");
	            			System.out.println("1 2 3 8 0 4 7 6 5");	
	            			System.out.println();	
	            			System.out.println("2 6 4 1 3 7 0 5 8");
	            			System.out.println("8 1 5 7 3 6 4 0 2");	
	            		}

	                  
	                  
	                  }
	              }
	          });
	        this.addWindowListener(new WindowAdapter() {
	              public void windowClosing(WindowEvent arg0) {
	                 Main.this.dispose();
	                  System.exit(0);
	              }
	          });
	    	
	        
	        tip.add(confirm);
	        this.add(tip);
	        this.setSize(dimension);
	        this.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
//		System.out.println("请输入算法：");
//		Scanner sc=new Scanner(System.in);
		System.out.println(s);
		System.out.println(m);
		//1是BFS
//		if(s==1) {
//			System.out.println("请输入长度：");
////		     m=sc.nextInt();
//			
//			BFS bfs=new BFS(m);
//			
//		}
//		//2是DFS
//		else if(s==2) {
//			
//			System.out.println("请输入长度：");
////			m=sc.nextInt();
//			System.out.println("请输入深度：");
////			int d=sc.nextInt();
////			DFS bfs=new DFS(m,d);
//			
//		}
//		
//		
//		//调试案例
//		if (m==4) {
//			System.out.println("案例：");
//			System.out.println("5 1 2 4 9 6 3 8 13 15 10 11 14 0 7 12");
//			System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");	
//			System.out.println();	
//			System.out.println("11 9 4 15 1 3 0 12 7 5 8 6 13 2 10 14");
//			System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");	
//		}
//		else if (m==3) {
//			System.out.println("案例：");
//			System.out.println("2 8 3 1 6 4 7 0 5");
//			System.out.println("1 2 3 8 0 4 7 6 5");	
//			System.out.println();	
//			System.out.println("2 6 4 1 3 7 0 5 8");
//			System.out.println("8 1 5 7 3 6 4 0 2");	
//		}

	}

}


