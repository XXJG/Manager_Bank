package bankmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class adminaddmessage extends JFrame implements ActionListener{
	private static final long serialVersion = 1L;
	static adminaddmessage aam;
	JPanel jp1 = new JPanel();
	JLabel label1 = new JLabel("账号",JLabel.CENTER);
	JLabel label2 = new JLabel("用户名",JLabel.CENTER);
	JLabel label3 = new JLabel("密码",JLabel.CENTER);
	JLabel label4 = new JLabel("身份证",JLabel.CENTER);
	JLabel label5 = new JLabel("开户余额",JLabel.CENTER);
	JLabel label6 = new JLabel("开户时间",JLabel.CENTER);
	JTextField num = new JTextField();
	JTextField nam = new JTextField();
	JTextField password = new JTextField();
	JTextField idt = new JTextField();
	JTextField mon = new JTextField();
	JTextField tim = new JTextField();
	JButton reset = new JButton("清空");
	JButton addmesg = new JButton("添加");
	public adminaddmessage(){
		super("开户");
		this.setSize(500, 400);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		addmesg.addActionListener(this);
		reset.addActionListener(this);
		label1.setBounds(135,60,70,20);
		jp1.add(label1);
		num.setBounds(190,60,140,20);
		jp1.add(num);
		label2.setBounds(130, 100, 70, 20);
		jp1.add(label2);
		nam.setBounds(190,100,140,20);
		jp1.add(nam);
		label3.setBounds(135, 140, 70, 20);
		jp1.add(label3);
		password.setBounds(190, 140, 140, 20);
		jp1.add(password);
		label4.setBounds(130, 180,70,20);
		jp1.add(label4);
		idt.setBounds(190, 180, 140, 20);
		jp1.add(idt);
		label5.setBounds(125, 220, 70, 20);
		jp1.add(label5);
		mon.setBounds(190, 220, 140, 20);
		jp1.add(mon);
		label6.setBounds(125, 260, 70, 20);
		jp1.add(label6);
		tim.setBounds(190, 260, 140, 20);
		jp1.add(tim);
		addmesg.setBounds(190, 290, 65, 20);
		reset.setBounds(265, 290, 65, 20);
		jp1.add(reset);
		jp1.add(addmesg);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addmesg){
			String a,b,c,d,g,f;
			//Float f;
			
			//a = num.getText();
			b = nam.getText();
			c = password.getText();
			d = idt.getText();
			//f = (float) Integer.parseInt(mon.getText());
			f = mon.getText();
			g = tim.getText();
			if(/*a.equals("") || */b.equals("") || c.equals("") ||
					d.equals("") || f.equals("") || g.equals(""))
				JOptionPane.showMessageDialog(this, "信息不全，无法开户！");
			else{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(aam, e1.getMessage());
				}
				try {
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt1 = con.createStatement();
					String sql ="select custnumber from ccd where rownum=1";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs1 = pstm.executeQuery();
					if(rs1.next())
					num.setText(rs1.getString("custnumber"));
					int a2 = stmt1.executeUpdate("delete from ccd where rownum=1");
					a = num.getText();
					int a1 = stmt1.executeUpdate("insert into customer(custnumber,custname,custpwd,custid,custmoney,custdate) " +
							"values('"+a+"','"+b+"','"+c+"','"+d+"',"+mon.getText()+",to_date('"+tim.getText()+"','YYYYMMDD'))");
					if(a1 == 1){
						JOptionPane.showMessageDialog(aam, "成功开户！");
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(aam, "开户失败！");
						setVisible(true);
					}
					stmt1.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(aam, e1.getMessage());
				}
			}
		}
		else
		{
			num.setText("");
			nam.setText("");
			password.setText("");
			idt.setText("");
			mon.setText("");
			tim.setText("");
		//nam.requestFocus();
		}
	}
	public static void main(String[] args) {
		adminaddmessage adm = new adminaddmessage();
	}

}
