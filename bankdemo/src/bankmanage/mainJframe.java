package bankmanage;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class mainJframe extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	static mainJframe mj;
	JPanel jp = new JPanel();
	JMenuBar jm =new JMenuBar();
	JMenuBar mbar = new JMenuBar();
	JMenu Help = new JMenu("Help");
	JMenu About = new JMenu("About");
	JMenuItem Password = new JMenuItem("Forget Password");
	JMenuItem About_us = new JMenuItem("About us");
	JMenuItem howc = new JMenuItem("How to connect us?");
	JLabel label1 = new JLabel("Account");
	JTextField usernumber = new JTextField();
	JLabel label2 = new JLabel("Password");
	JPasswordField pwd = new JPasswordField();
	JButton Enter = new JButton("Enter");
	JButton Exit = new JButton("Exit");
	String url = "images/background_3.jpg";
	ButtonGroup bgp = new ButtonGroup();
	JRadioButton customer = new JRadioButton("Customer");
	JRadioButton Admin = new JRadioButton("Administrator");
	public mainJframe(){
		super("Bank Management System");
		this.setSize(500, 400);
		this.setResizable(false);
		jp.setOpaque(false);
		this.setJMenuBar(jm);
		JLabel img = new JLabel(new ImageIcon(url));
		img.setBounds(0,0,575,85);
		Password.addActionListener(this);
		About_us.addActionListener(this);
		howc.addActionListener(this);
		setJMenuBar(jm);
		jm.add(Help);
		jm.add(About);
		jm.add(Password);
		Help.add(Password);
		About.add(About_us);
		Help.add(howc);
		mbar.add(Help);
		mbar.add(About);
		setJMenuBar(mbar);
		jp.add(img);
		customer.setBounds(210,150,70,30);
		Admin.setBounds(310,150,70,30);
		bgp.add(customer);
		bgp.add(Admin);
		jp.add(customer);
		jp.add(Admin);
		Enter.setBounds(210,180,70,20);
		Exit.setBounds(290,180,70,20);
		Enter.addActionListener(this);
		Exit.addActionListener(this);
		Help.setForeground(Color.blue);
		Password.setForeground(Color.blue);
		About.setForeground(Color.blue);
		About_us.setForeground(Color.blue);
		howc.setForeground(Color.blue);
		jp.add(Enter);
		jp.add(Exit);
		jp.setLayout(null);
		this.add(jp);
		label1.setBounds(150,90,50,25);
		jp.add(label1);
		usernumber.setBounds(210, 90, 150, 25);
		jp.add(usernumber);
		label2.setBounds(150, 125, 50, 25);
		jp.add(label2);
		pwd.setBounds(210, 125, 150, 25);
		jp.add(pwd);
		this.setBounds(110, 60, 540, 320);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String id,password;
		id = usernumber.getText();
		password = pwd.getText();
		if(e.getSource() == Password)
			JOptionPane.showMessageDialog(this, "Please connect to admin", 
					"Forget the password", JOptionPane.INFORMATION_MESSAGE);
		if(e.getSource() == About_us)
			JOptionPane.showMessageDialog(this, 
					" Create by Jian Guo,Xie\n\n AnHui University of Technology\n\n Version 1.0.0",
					"About the software",JOptionPane.INFORMATION_MESSAGE);
		if(e.getSource() == Exit)
			System.exit(0);
		if(e.getSource() == howc)
			JOptionPane.showMessageDialog(this, "MAIL:1661377004@qq.com");
		if(e.getSource() == Enter){
			//Loading database driver
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException ce) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(mj, ce.getMessage());
			}
			if(!(customer.isSelected()) && !(Admin.isSelected()))
				JOptionPane.showMessageDialog(this, "Please Select Customer or Admin");
			else if(customer.isSelected()){
				if(id.equals("") || password.equals(""))
					JOptionPane.showMessageDialog(this, "Sorry,Account or password is Empty");
				else{//verification the database
					try {
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
								,"system","123456");
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("select * from customer");
						rs = stmt.executeQuery("select * from customer where custname='"+id+"'");
						if(rs.next()){
							if(rs.getString("custpwd").equals(password)){
								this.setVisible(false);
								@SuppressWarnings("unused")
								Customer cust = new Customer();
							}
							else 
								JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
							
						}
						else 
							JOptionPane.showMessageDialog(this, "Account does not exist!");
						rs.close();
						stmt.close();
							
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mj, e1.getMessage());
					}
				}
			}
			else{
				if(id.equals("") || password.equals(""))
					JOptionPane.showMessageDialog(this, "Sorry,Account or password is Empty");
				else{
					try{
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
							,"system","123456");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from administor");
					rs = stmt.executeQuery("select * from administor where adminname='"+id+"'");
					if(rs.next()){
						if(rs.getString("adminpwd").equals(password)){
							this.setVisible(false);
							@SuppressWarnings("unused")
							Administor ad = new Administor();
						}
						else
							JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
	
					}
					else
						JOptionPane.showMessageDialog(this, "Account does not exist!");
					rs.close();
					stmt.close();
					}
					catch(SQLException e2){
						JOptionPane.showMessageDialog(mj, e2.getMessage());
					}
				}
				
			}
		}
		
		
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		mainJframe mjf = new mainJframe();
		
	}
	
}
