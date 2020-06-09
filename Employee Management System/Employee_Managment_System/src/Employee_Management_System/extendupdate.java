package Employee_Management_System;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class extendupdate extends JFrame{
	JPanel contentpane;
	JTextField txt1,txt2,txt3,txt4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		extendupdate obj = new extendupdate();
		obj.setVisible(true);
	}
	
	public extendupdate() {
		try {
			conn c = new conn();
			String query = "Select * from empdata where empID='"+updateemp.id+"';";
			ResultSet res = c.s.executeQuery(query);
			res.next();
			setTitle("UPDATE EMPLOYEE DETAILS");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200,200,400,400);
			
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5 ,5 ,5 ,5 ));
			setContentPane(contentpane);
			contentpane.setLayout(null);
			
			JLabel label1 = new JLabel("EMP NAME");
			label1.setBounds(40,40,150,25);
			contentpane.add(label1);
			
			txt1 = new JTextField();
			txt1.setBounds(200,40,150,25);
			txt1.setColumns(10);
			txt1.setText(res.getString("empname"));
			contentpane.add(txt1);
			
			JLabel label2 = new JLabel("EMP AGE");
			label2.setBounds(40,100,150,25);
			contentpane.add(label2);
			
			txt2 = new JTextField();
			txt2.setBounds(200,100,60,25);
			txt2.setColumns(10);
			txt2.setText(String.valueOf(res.getInt("empage")));
			contentpane.add(txt2);
			

			JLabel label3 = new JLabel("EMP ID");
			label3.setBounds(40,160,150,25);
			contentpane.add(label3);
			
			txt3 = new JTextField();
			txt3.setBounds(200,160,60,25);
			txt3.setColumns(10);
			txt3.setText(String.valueOf(res.getInt("empId")));
			contentpane.add(txt3);
			
			JLabel label4 = new JLabel("EMP EMAIL");
			label4.setBounds(40,220,150,25);
			contentpane.add(label4);
			
			txt4 = new JTextField();
			txt4.setBounds(200,220,150,25);
			txt4.setColumns(15);
			txt4.setText(res.getString("empEmail"));
			contentpane.add(txt4);
			
			JButton button1 =  new JButton("UPDATE");
			button1.setBounds(80, 260, 100, 25);
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						conn c1=new conn();
						String query="Update empdata set empname='"+txt1.getText()+"',empage='"+txt2.getText()+"',empId='"+txt3.getText()+"',empEmail='"+txt4.getText()+"' where empId='"+updateemp.id+"';";
						c1.s.executeUpdate(query);
						JOptionPane.showMessageDialog(null,"Updated succesfully");
						c1.con.close();
						setVisible(false);
						dispose();
					}
						catch(Exception e) {
							JOptionPane.showMessageDialog(null,e);
						}
					}
			});
			contentpane.add(button1);
			
			JButton button2 =  new JButton("CANCEL");
			button2.setBounds(220, 260, 100, 25);
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			contentpane.add(button2);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
