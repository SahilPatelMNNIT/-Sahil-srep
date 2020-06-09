package Employee_Management_System;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;
public class updateemp extends JFrame{
	JPanel contentpane;
	JTextField txt1;
	protected static String id;
	public static void main(String args[]) {
		try {
			updateemp obj = new updateemp();
			obj.setVisible(true);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public updateemp() {
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,350,200);
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		contentpane.setLayout(null);
		
		JLabel label1 = new JLabel("EMPLOYEE ID :");
		label1.setBounds(40 ,40 ,120 ,25);
		contentpane.add(label1);
		
		txt1 = new JTextField();
		txt1.setBounds(200, 40, 60, 23);
		contentpane.add(txt1);
		
		JButton button1 = new JButton("UPDATE");
		button1.setBounds(100, 80, 100, 25);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					id=txt1.getText();
					conn c = new conn();
					String s = "Select * from empdata where empId='"+id+"';";
					ResultSet res= c.s.executeQuery(s);
					if(res.next())
					{
						extendupdate obj = new extendupdate() ;
						obj.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "EMPLOYEE ID doesn't exist");
					}
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
