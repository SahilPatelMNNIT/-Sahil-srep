package Employee_Management_System;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class rememp extends JFrame{
	JPanel contentpane;
	JTextField txt1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rememp obj = new rememp();
		obj.setVisible(true);
	}
	public rememp() {
		setTitle("REMOVE EMPLOYEE");
		setBounds(200,200,330,180);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		contentpane.setLayout(null);
		setContentPane(contentpane);
		
		JLabel label1=new JLabel("Enter Employee ID :");
		label1.setBounds(60, 40, 120, 25);
		contentpane.add(label1);
		
		txt1=new JTextField();
		txt1.setBounds(200, 40, 50, 25);
		contentpane.add(txt1);
		
		JButton button1 = new JButton("REMOVE");
		button1.setBounds(100 ,80 , 100, 25);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn c = new conn();
					String query="DELETE FROM empdata WHERE empID='"+txt1.getText()+"';";
					c.s.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "REMOVED SUCCESSFULLY");
					c.con.close();
					dispose();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		contentpane.add(button1);
	}
}
