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

public class Custfind extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    static Custfind cfd;
    JPanel jp1 = new JPanel();
    JButton search = new JButton("≤È —Ø");
    JButton ext = new JButton("ÕÀ ≥ˆ");
    JLabel label1 = new JLabel("’À ∫≈",JLabel.CENTER);
    JLabel label2 = new JLabel("√‹ ¬Î",JLabel.CENTER);
    JTextField num = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField txt1=new JTextField();
    public Custfind(){
    	super("≤È—Ø”‡∂Ó");
    	this.setSize(500,400);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		label1.setBounds(110,30,100,20);
		jp1.add(label1);
		num.setBounds(180,30,160,20);
		jp1.add(num);
		label2.setBounds(110,70,100,20);
		jp1.add(label2);
		password.setBounds(180,70,160,20);
		jp1.add(password);
		search.addActionListener(this);
		ext.addActionListener(this);
		search.setBounds(150,320,65,20);
		ext.setBounds(250,320,65,20);
		jp1.add(search);
		jp1.add(ext);
		txt1.setBounds(200,180,100,40);
		jp1.add(txt1);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String no,pwd;
		no = num.getText();
		pwd = password.getText();
		if(e.getSource() == search){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(cfd, e1.getMessage());
			}
			if(num.equals("") || password.equals(""))
				JOptionPane.showMessageDialog(cfd, "’À∫≈ªÚ√‹¬ÎŒ¥ÃÓ–¥");
			else{
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
							,"system","123456");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from customer");
					rs = stmt.executeQuery("select custpwd from customer where custnumber='"+no+"'");
					if(rs.next()){
						String sql ="select custmoney from customer where custnumber ='"+no+"'";
						PreparedStatement pstm = con.prepareStatement(sql);
						ResultSet rs1 = pstm.executeQuery();
						while(rs1.next())
						txt1.setText(rs1.getString("custmoney"));
					}
					else{
						JOptionPane.showMessageDialog(this, "Sorry,Wrong password!");
					}
					rs.close();
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(cfd, e1.getMessage());
				}
				
			}
		}
		else{
			dispose();
		}
	}
	public static void main(String[] args) {
		Custfind cf = new Custfind();
	}
}
