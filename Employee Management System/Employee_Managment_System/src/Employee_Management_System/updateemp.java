package Employee_Management_System;
import java.awt.Image;
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
		setBounds(300,300,400,200);
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		contentpane.setLayout(null);
		
		JLabel label1 = new JLabel("EMPLOYEE ID :");
		label1.setBounds(140 ,40 ,320 ,25);
		contentpane.add(label1);
		
		txt1 = new JTextField();
		txt1.setBounds(300, 40, 60, 23);
		contentpane.add(txt1);
		
		JButton button1 = new JButton("UPDATE");
		button1.setBounds(200, 80, 100, 25);
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
		
		ImageIcon i1 = new ImageIcon("D:\\Study\\Projects\\Employee Management System\\Icon\\update.png");
		Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel img = new JLabel(i3);
		img.setBounds(0,0,150,150);
		contentpane.add(img);
		img.setVisible(true);
	}
}
