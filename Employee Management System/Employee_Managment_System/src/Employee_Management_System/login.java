package Employee_Management_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Label = new JLabel("UserName");
		Label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label.setBounds(22, 68, 105, 26);
		contentPane.add(Label);
		
		JLabel Label_1 = new JLabel("Password");
		Label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label_1.setBounds(22, 105, 105, 26);
		contentPane.add(Label_1);
		
		JLabel Label_2 = new JLabel("LOGIN");
		Label_2.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		Label_2.setBounds(125, 11, 138, 26);
		contentPane.add(Label_2);
		
		textField = new JTextField();
		textField.setBounds(137, 71, 175, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(137, 108, 175, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn c = new conn();
					String s = "Select * from login where Username='"+textField.getText()+"' and Password='"+String.valueOf(passwordField.getPassword())+"';";
					ResultSet res = c.s.executeQuery(s);
					if(res.next())
					{
						Mainmenu obj = new Mainmenu();
						obj.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
					c.con.close();
					dispose();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(122, 145, 105, 23);
		contentPane.add(btnNewButton);
	}
}
