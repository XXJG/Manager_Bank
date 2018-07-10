package bankmanage;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Admincount extends JFrame implements ActionListener{
	private static final long serialVersion = 1L;
	static Admincount adc;
	private JScrollPane scpDemo;
	private JTable tabDemo;
	JPanel jp1 = new JPanel();
	JButton search = new JButton("查 询");
	JButton reset = new JButton("退 出");
	
	public Admincount(){
		super("富豪排行榜");
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jp1);
		jp1.setLayout(null);
		search.setBounds(150,20,65,20);
		reset.setBounds(250,20,65,20);
		jp1.add(search);
		jp1.add(reset);
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10,50,400,300);
		jp1.add(this.scpDemo);
		search.addActionListener(this);
		reset.addActionListener(this);
		

	}

	public static void main(String[] args) {
		Admincount adct = new Admincount();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == search){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(adc, e1.getMessage());
			}
			try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
						,"system","123456");
				//Statement stmt = con.createStatement();
				//("select custname from customer order by custmoney DESC");
				//ResultSet rs1 = stmt.executeQuery("select custname,custmoney from customer order by custmoney DESC");
				//
				String sql ="select custnumber,custname,custmoney from customer order by custmoney DESC";
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs1 = pstm.executeQuery();
				int count = 0;
				
				while(rs1.next()){
					 
					count++;

				}
			// rs1 = stmt.executeQuery("select custname,custmoney from customer order by custmoney DESC");
				rs1 = pstm.executeQuery();
				Object[][] info = new Object[count][3];
				count = 0;
				while(rs1.next()){
					info[count][0] = rs1.getString("custnumber"); 
					info[count][1] = rs1.getString("custname"); 
					info[count][2] = rs1.getString("custmoney");
					count++;

				}
				
				String[] title = {"卡号","用户名","金额"};
				this.tabDemo = new JTable(info,title);
				this.tabDemo.getTableHeader();
				this.scpDemo.getViewport().add(tabDemo);
				//ResultSet rs2 = stmt.executeQuery("select custmoney from customer order by custmoney DESC");
				//while(rs2.next()){
					  

				//}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(adc, e1.getMessage());
			}
		}
		else{
			//System.exit(0);
			dispose();
		}
	}
}
