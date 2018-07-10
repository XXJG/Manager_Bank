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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Custtransfer extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	static Custtransfer ctf;
	JPanel jp1 = new JPanel();
	JLabel lab1 = new JLabel("转账账户",JLabel.LEFT);
	JLabel lab2 = new JLabel("本机账户",JLabel.LEFT);
	JLabel lab3 = new JLabel("转账金额",JLabel.LEFT);
	JLabel lab4 = new JLabel("密码",JLabel.LEFT);
	JLabel lab5 = new JLabel("本机金额",JLabel.LEFT);
	JTextField txt1 = new JTextField();  //转账账户
	JTextField txt2 = new JTextField();   //本机用户
	JTextField txt3 = new JTextField();   //money
	JPasswordField txt4 = new JPasswordField();  //密码
	JButton trans = new JButton("确 认");
	JButton reset = new JButton("清 空");
	JButton quit = new JButton("退 出");
	public Custtransfer(){
		super("转 账");
		this.setSize(500,500);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		lab1.setBounds(125,40,80,20);
		jp1.add(lab1);
		txt1.setBounds(180,40,170,20);
		jp1.add(txt1);
		lab2.setBounds(125,80,80,20);
		jp1.add(lab2);
		txt2.setBounds(180,80,170,20);
		jp1.add(txt2);
		lab4.setBounds(140, 120, 80, 20);
		jp1.add(lab4);
		txt4.setBounds(180, 120, 170, 20);
		jp1.add(txt4);
		lab3.setBounds(125, 160, 80, 20);
		jp1.add(lab3);
		txt3.setBounds(180, 160, 170, 20);
		jp1.add(txt3);	
		trans.setBounds(120,220,65,20);
		reset.setBounds(225,220,65,20);
		quit.setBounds(330,220,65, 20);
		jp1.add(trans);
		jp1.add(reset);
        jp1.add(quit);
        trans.addActionListener(this);
        reset.addActionListener(this);
        quit.addActionListener(this);

	}
	public static void main(String[] args) {
		Custtransfer ct = new Custtransfer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id1 = txt1.getText();
		String id2 = txt2.getText();
		String pwd = txt4.getText();
		String mon = txt3.getText();
		String cmon;
		if(e.getSource() == trans){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(ctf, e1.getMessage());
			}
			if(id1.equals("") || pwd.equals("") || mon.equals("") || id2.equals(""))
				JOptionPane.showMessageDialog(ctf, "转账账号、本机账号、密码或转账金额为空！");
			else{
				try {
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from customer");
					rs = stmt.executeQuery("select * from customer where custnumber='"+id2+"'");
					if(rs.next()){
						if(rs.getString("custpwd").equals(pwd)){
							Statement stmt1 = con.createStatement();
							Statement stmt2 = con.createStatement();
							
							String sql ="select custmoney from customer where custnumber ='"+id2+"'";
							PreparedStatement pstm = con.prepareStatement(sql);
							ResultSet rs3 = pstm.executeQuery();
							while(rs3.next()){
							if(Integer.parseInt(rs3.getString("custmoney")) < Integer.parseInt(mon)){
								JOptionPane.showMessageDialog(ctf, "转账失败！金额不够！");
								setVisible(true);
							}
							else{
							int a1 = stmt1.executeUpdate("update customer set custmoney=custmoney-'"+mon+"'where custnumber='"+id2+"'");
							int a2 = stmt2.executeUpdate("update customer set custmoney=custmoney+'"+mon+"'where custnumber='"+id1+"'");
							if(a1 == 1 && a2 == 1){
								JOptionPane.showMessageDialog(ctf, "转账成功!");
								setVisible(false);
							}
							else{
								JOptionPane.showMessageDialog(ctf, "转账失败！");
								setVisible(true);
							}
							stmt1.close();
							stmt2.close();}}
								
						}
						else{
							JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
						}
					}
					stmt.close();
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ctf, e1.getMessage());
				}
			}
		}
		else if(e.getSource() == reset){
			txt1.setText("");
			txt2.setText("");
			txt3.setText("");
			txt4.setText("");
		}
		else
			dispose();
	}
}
