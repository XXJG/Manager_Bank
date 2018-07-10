package bankmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class adminlogpassword extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	static adminlogpassword alp;
	JPanel jp1 = new JPanel();
	JLabel label1 = new JLabel("用户名",JLabel.LEFT);
	JLabel label2 = new JLabel("原密码",JLabel.LEFT);
	JLabel label3 = new JLabel("新密码",JLabel.LEFT);
	JTextField ID = new JTextField();
	JTextField oldpassword = new JTextField();
	JTextField password = new JTextField();
	JButton Changepassword = new JButton("修改");
	JButton reset = new JButton("清空");
	public adminlogpassword(){
		super("修改登录密码（Admin）");
		this.setSize(500, 400);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		Changepassword.addActionListener(this);
		reset.addActionListener(this);
		label1.setBounds(140,120,50,20);
		jp1.add(label1);
		ID.setBounds(190,120,150,20);
		jp1.add(ID);
		label2.setBounds(140,160,50,20);
		jp1.add(label2);
		oldpassword.setBounds(190,160,150,20);
		jp1.add(oldpassword);
		label3.setBounds(140,200,50,20);
		jp1.add(label3);
		password.setBounds(190,200,150,20);
		jp1.add(password);
		Changepassword.setBounds(190,240,65,20);
		jp1.add(Changepassword);
		reset.setBounds(275,240,65,20);
		jp1.add(reset);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = ID.getText();
		String oldpad = oldpassword.getText();
		String pad = password.getText();
		if(e.getSource() == Changepassword)
		{
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(alp, e1.getMessage());
			}
			try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
						,"system","123456");
				Statement stmt = con.createStatement();
				@SuppressWarnings("unused")
				ResultSet rs = stmt.executeQuery("select * from administor");
				rs = stmt.executeQuery("select * from administor where adminname='"+id+"'");
				if(id.equals(""))
					JOptionPane.showMessageDialog(alp, "用户名未填写");
				else if(rs.next()){
					if(oldpad.equals(""))
						JOptionPane.showMessageDialog(alp, "原密码未填写");
					else{
						if(rs.getString("adminpwd").equals(oldpad)){
							if(pad.equals(0))
								JOptionPane.showMessageDialog(alp, "密码不能为空 ");
							else{
								int a = stmt.executeUpdate("update administor set adminpwd = '"+pad+"' where adminpwd = '"+oldpad+"'");
								if(a == 1){
									JOptionPane.showMessageDialog(alp, "密码已修改！");
								    this.setVisible(false);
								}
								else
									JOptionPane.showMessageDialog(alp, "修改失败");
								rs.close();
								stmt.close();
							}
						}
						else
							JOptionPane.showMessageDialog(alp, "原密码不正确!");
					}
				}
				else
					JOptionPane.showMessageDialog(alp, "用户名不存在，请重新输入");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(alp, e1.getMessage());
			}
		}
		else {
			ID.setText("");
			oldpassword.setText("");
			password.setText("");
			ID.requestFocus();
		}
	}
	public static void main(String[] args) {
		adminlogpassword al = new adminlogpassword();
	}
}
