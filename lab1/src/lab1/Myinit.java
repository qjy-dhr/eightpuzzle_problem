package lab1;

import java.awt.*;
import javax.swing.*;


public class Myinit extends JFrame{

	//此处为了方便，直接设置成了20
	
	public JTextField[] blocks = new JTextField[20];
	 public JTextField originField;
	 public JTextField goalField;
	 public JTextField resultField;
	 public JTextField dataField1;
	 public JTextField dataField2;
	 public JTextField dataField3;
	 public JButton confirm;
	    
	  Myinit(int m) {	  
		  super(m*m-1+"数码求解器");
	        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	        int temp1=dimension.width;
			 int temp2=dimension.height;
			 
			
	        dimension.width = (int) (dimension.width * 0.4);
	        dimension.height = (int) (dimension.height*0.8);

	     
	        Box box = Box.createVerticalBox();
	    
	        this.setLocation((int) ((temp1-dimension.width)/2),(int)((temp2-dimension.height)/2));
	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(m, m));
	        
	        for(int i = 0;  i < m*m; i++) {
	            blocks[i] = new JTextField();
	            blocks[i].setHorizontalAlignment(JTextField.CENTER);
	            blocks[i].setPreferredSize(new Dimension(200,200));
	            blocks[i].setFont(new Font("宋体", Font.PLAIN, 50));
	            panel.add(blocks[i]);
	        }

	        Box tip = Box.createVerticalBox();
	        JLabel label = new JLabel("输入开始序列和目标序列（空格用0表示，数字中间用空格分开）");
	        tip.add(label);
	        JLabel originLabel = new JLabel("开始序列:");
	        
	        tip.add(originLabel);
	        originField = new JTextField();
	        originField.setFont(new Font("宋体", Font.PLAIN, 30));	        
	        tip.add(originField);
	        
	        JLabel goalLabel = new JLabel("目标序列:");
	        tip.add(goalLabel);
	        
	        goalField = new JTextField();        
	        goalField.setFont(new Font("宋体", Font.PLAIN, 30));
	        tip.add(goalField);
	        
	        JLabel outLabel = new JLabel("运行演示:");
	        tip.add(outLabel);
	        
	        Box but = Box.createVerticalBox();

	        confirm = new JButton("开始运行：");
	        
	        
	        but.add(confirm);
	        
	        //数据可视化
	        Box out=Box.createVerticalBox();
	        
	        JLabel dataLabel = new JLabel("运行数据:");
	        out.add(dataLabel);
	        dataField1 = new JTextField();
	        dataField2 = new JTextField(); 
	        dataField3 = new JTextField(); 
	        dataField1.setFont(new Font("宋体", Font.PLAIN, 20));
	        dataField2.setFont(new Font("宋体", Font.PLAIN, 20));
	        dataField3.setFont(new Font("宋体", Font.PLAIN, 20));

	        out.add(dataField1);
	        out.add(dataField2);
	        out.add(dataField3);
	        
	        resultField = new JTextField();   
	        out.add(resultField);
	        
	        
	        box.add(tip);
            box.add(panel);
	        box.add(but);
	        box.add(out);

	        this.add(box);
	        this.setSize(dimension);
	        this.setVisible(true);
	      
		  
	  }  
}
