package lab1;

import java.awt.*;
import javax.swing.*;


public class Myinit extends JFrame{

	//�˴�Ϊ�˷��㣬ֱ�����ó���20
	
	public JTextField[] blocks = new JTextField[20];
	 public JTextField originField;
	 public JTextField goalField;
	 public JTextField resultField;
	 public JTextField dataField1;
	 public JTextField dataField2;
	 public JTextField dataField3;
	 public JButton confirm;
	    
	  Myinit(int m) {	  
		  super(m*m-1+"���������");
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
	            blocks[i].setFont(new Font("����", Font.PLAIN, 50));
	            panel.add(blocks[i]);
	        }

	        Box tip = Box.createVerticalBox();
	        JLabel label = new JLabel("���뿪ʼ���к�Ŀ�����У��ո���0��ʾ�������м��ÿո�ֿ���");
	        tip.add(label);
	        JLabel originLabel = new JLabel("��ʼ����:");
	        
	        tip.add(originLabel);
	        originField = new JTextField();
	        originField.setFont(new Font("����", Font.PLAIN, 30));	        
	        tip.add(originField);
	        
	        JLabel goalLabel = new JLabel("Ŀ������:");
	        tip.add(goalLabel);
	        
	        goalField = new JTextField();        
	        goalField.setFont(new Font("����", Font.PLAIN, 30));
	        tip.add(goalField);
	        
	        JLabel outLabel = new JLabel("������ʾ:");
	        tip.add(outLabel);
	        
	        Box but = Box.createVerticalBox();

	        confirm = new JButton("��ʼ���У�");
	        
	        
	        but.add(confirm);
	        
	        //���ݿ��ӻ�
	        Box out=Box.createVerticalBox();
	        
	        JLabel dataLabel = new JLabel("��������:");
	        out.add(dataLabel);
	        dataField1 = new JTextField();
	        dataField2 = new JTextField(); 
	        dataField3 = new JTextField(); 
	        dataField1.setFont(new Font("����", Font.PLAIN, 20));
	        dataField2.setFont(new Font("����", Font.PLAIN, 20));
	        dataField3.setFont(new Font("����", Font.PLAIN, 20));

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
