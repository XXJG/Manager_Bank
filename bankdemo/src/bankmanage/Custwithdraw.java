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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Custwithdraw extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	static Custwithdraw cwd;
	JPanel jp1 = new JPanel(); 
	JLabel lab1 = new JLabel("账 户",JLabel.LEFT);
	JLabel lab2 = new JLabel("密 码",JLabel.LEFT);
	JLabel lab3 = new JLabel("金 额",JLabel.LEFT);
	JTextField txt1 = new JTextField();  //account
	JPasswordField txt2 = new JPasswordField();   //密码
	JTextField txt3 = new JTextField();   //money
	JButton trans = new JButton("确 认");
	JButton reset = new JButton("清 空");
	JButton quit = new JButton("退 出");
	public Custwithdraw(){
		super("取 款");
		this.setSize(500,500);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		lab1.setBounds(140,40,80,20);
		jp1.add(lab1);
		txt1.setBounds(180,40,170,20);
		jp1.add(txt1);
		lab2.setBounds(140,80,80,20);
		jp1.add(lab2);
		txt2.setBounds(180,80,170,20);
		jp1.add(txt2);
		lab3.setBounds(140, 120, 80, 20);
		jp1.add(lab3);
		txt3.setBounds(180, 120, 170, 20);
		jp1.add(txt3);
		trans.setBounds(120,180,65,20);
		reset.setBounds(225,180,65,20);
		quit.setBounds(330,180,65, 20);
		jp1.add(trans);
		jp1.add(reset);
        jp1.add(quit);
        trans.addActionListener(this);
        reset.addActionListener(this);
        quit.addActionListener(this);
	}
	public static void main(String[] args) {
		Custwithdraw cw = new Custwithdraw();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txt1.getText();
		String pwd = txt2.getText();
		String mon = txt3.getText();
		if(e.getSource() == trans){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(cwd, e1.getMessage());
			}
			if(id.equals("") || pwd.equals("") || mon.equals(""))
				JOptionPane.showMessageDialog(cwd, "账号、密码或取出金额为空！"); 
			else{
				try {
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from customer");
					rs = stmt.executeQuery("select * from customer where custnumber='"+id+"'");
					if(rs.next()){
						if(rs.getString("custpwd").equals(pwd)){
							Statement stmt1 = con.createStatement();
							int a1 = stmt1.executeUpdate("update customer set custmoney=custmoney-'"+mon+"'where custnumber='"+id+"'");
							if(a1 == 1){
								JOptionPane.showMessageDialog(cwd, "取款成功");
								setVisible(false);
							}
							else{
								JOptionPane.showMessageDialog(cwd, "取款失败！");
								setVisible(true);
							}
							stmt1.close();
						}
						else 
							JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
					}
					stmt.close();
					rs.close();
					}catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(cwd, e1.getMessage());
				}
			}
		}
		else if(e.getSource() == reset){
			txt1.setText("");
			txt2.setText("");
			txt3.setText("");
		}
		else
			dispose();
	
	}
}
 