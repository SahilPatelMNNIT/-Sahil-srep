package Employee_Management_System;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Intro extends JFrame{
	JPanel contentpane;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Intro obj = new Intro();
		obj.setVisible(true);
	}
	
	public Intro() {
		JPanel contentpane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EMPLOYEE MANAGEMENT");
		setBounds(200,200,500,400);
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JButton button = new JButton("WELCOME");
		button.setBounds(200,300,100,40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					login obj = new login();
					obj.setVisible(true);
					dispose();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		contentpane.add(button);
		
		ImageIcon i1 = new ImageIcon("D:\\Study\\Projects\\Employee Management System\\Icon\\intro.jpg");
		Image i2 = i1.getImage().getScaledInstance(550, 480, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel img = new JLabel(i3);
		img.setBounds(0,0,500,400);
		contentpane.add(img);
		img.setVisible(true);
	}
}
