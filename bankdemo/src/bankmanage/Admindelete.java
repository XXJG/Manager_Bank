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

public class Admindelete extends JFrame implements ActionListener{
	private static final long serialVersion = 1L;
	static Admindelete ade;
	JPanel jp1 = new JPanel();
	JLabel label1 = new JLabel("账号",JLabel.CENTER);
	JLabel label3 = new JLabel("密码",JLabel.CENTER);
	JLabel label4 = new JLabel("余额",JLabel.CENTER);
	JTextField num = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField mon = new JTextField();
	JButton jb1 = new JButton("查询");
	JButton jb2 = new JButton("销户");
	JButton jb3 = new JButton("清空");
	JButton jb4 = new JButton("退出");
	
	public Admindelete(){
		super("销户");
		this.setSize(500, 400);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(label1);
		jp1.add(label3);
		jp1.add(label4);
		jp1.add(num);
		jp1.add(password);
		jp1.add(mon);
		jb1.setBounds(100, 240, 65, 20);
		jb2.setBounds(170, 240, 65, 20);
		jb3.setBounds(240, 240, 65, 20);
		jb4.setBounds(310, 240, 65, 20);
		label1.setBounds(135, 60, 70, 20);
		label3.setBounds(135, 140, 70, 20);
		label4.setBounds(135, 290, 70, 20);
		num.setBounds(190, 60, 140, 20);
		password.setBounds(190, 140, 140, 20);
		mon.setBounds(190, 290, 140, 20);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = num.getText();
		//String mone = mon.getText();
		String pw = password.getText();
		if(e.getSource() == jb1){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(ade, e1.getMessage());
			}
			if(id.equals("") || pw.equals(""))
				JOptionPane.showMessageDialog(ade, "账号或密码为空！"); 
			else{
				try {
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from customer");
					rs = stmt.executeQuery("select custpwd from customer where custnumber='"+id+"'");
					if(rs.next()){
						String sql ="select custmoney from customer where custnumber ='"+id+"'";
						PreparedStatement pstm = con.prepareStatement(sql);
						ResultSet rs1 = pstm.executeQuery();
						while(rs1.next())
						mon.setText(rs1.getString("custmoney"));
					}
					else{
						JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
					}
					rs.close();
					stmt.close();	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ade, e1.getMessage());
				}
			}
		
			
		}
		else if(e.getSource() == jb2){
			String id1 = num.getText();
			String mone = mon.getText();
			if(mone.equals("0")){
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ade, e1.getMessage());
				}
				try {
					Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt = con1.createStatement();
					int a1 = stmt.executeUpdate("delete from customer where custnumber='"+id+"'");
					int a2 = stmt.executeUpdate("insert into ccd(custnumber) values('"+id+"')");
					if(a1 == 1){
						JOptionPane.showMessageDialog(ade, "成功销户！");
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(ade, "销户失败");
						setVisible(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ade, e1.getMessage());
				}
			}
			else
				JOptionPane.showMessageDialog(this, "销户失败，请先让用户取款");
		}
		else if(e.getSource() == jb3){
			num.setText("");
			password.setText("");
			mon.setText("");
		}
		else{
			dispose();
		}
	}
	
	public static void main(String[] args) {
		Admindelete adee = new Admindelete();
	}

}
