package Employee_Management_System;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class viewemp extends JFrame{
	JPanel contentPane;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		viewemp obj = new viewemp();
		obj.setVisible(true);
	}
	
	public viewemp() {
		try {
			setTitle("View Employee Details");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200,200,800,400);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			BoxLayout boxlayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
			contentPane.setLayout(boxlayout);
			
			String query="SELECT * FROM empdata;";
			conn c=new conn();
			ResultSet res=c.s.executeQuery(query);
			JLabel l1=new JLabel("EMPLOYEE NAME         EMPLOYEE AGE         EMPLOYEE ID          EMPLOYEE EMAIL");
			contentPane.add(l1);
			while(res.next())
			{
				String s=res.getString("empname")+"        "+res.getInt("empage")+"	        "+res.getInt("empId")+"         "+res.getString("empEmail");
				JLabel label= new JLabel(s);
				contentPane.add(label);
			}
			c.con.close();
			JButton button= new JButton("CLOSE");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					dispose();
				}
			});
			contentPane.add(button);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}

}
