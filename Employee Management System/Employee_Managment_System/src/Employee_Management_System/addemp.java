package Employee_Management_System;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class addemp extends JFrame{
	private JTextField empname;
	private JTextField empage;
	private JPanel contentPane;
	private JTextField empId;
	private JTextField empEmail;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addemp frame = new addemp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public addemp() {
		setTitle("Add_Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,600,400);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name=new JLabel("Employee Name");
		name.setBounds(20, 40, 150, 25);
		name.setFont(new Font("Tahoma",Font.PLAIN,15));
		contentPane.add(name);
		
		empname = new JTextField();
		empname.setBounds(150,40,150,25);
		contentPane.add(empname);
		empname.setColumns(10);
		
		JLabel age=new JLabel("Employee Age");
		age.setBounds(20, 100, 100, 25);
		age.setFont(new Font("Tahoma",Font.PLAIN,15));
		contentPane.add(age);
		
		empage = new JTextField();
		empage.setBounds(150,100,150,25);
		contentPane.add(empage);
		empage.setColumns(5);
		
		JLabel ID=new JLabel("Employee Id");
		ID.setBounds(20,160,100,25);
		ID.setFont(new Font("Tahoma",Font.PLAIN,15));
		contentPane.add(ID);
	
		empId = new JTextField();
		empId.setBounds(150,160,150,25);
		empId.setColumns(10);
		contentPane.add(empId);
		
		JLabel email=new JLabel("Emplyee Email");
		email.setBounds(20,220,100,25);
		email.setFont(new Font("Tahoma",Font.PLAIN,15));
		contentPane.add(email);
		
		empEmail = new JTextField();
		empEmail.setBounds(150,220,150,25);
		empEmail.setColumns(10);
		contentPane.add(empEmail);
		
		JButton button1 = new JButton("Add Employee");
		button1.setBounds(400, 100 , 150 , 25);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				conn c1=new conn();
				String query="INSERT INTO empdata VALUES ('"+empname.getText()+"','"+empage.getText()+"','"+empId.getText()+"','"+empEmail.getText()+"');";
				c1.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null,"Added succesfully");
				c1.con.close();
				setVisible(false);
				dispose();
			}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		contentPane.add(button1);
		
		JButton button2=new JButton("Cancel");
		button2.setBounds(400,160,150,25);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(button2);
	}
}
