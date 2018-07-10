package bankmanage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class Mypanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Image img1 = Toolkit.getDefaultToolkit().getImage("images/background_3.jpg");
	public void paint(Graphics g)
	{
		g.drawImage(img1, 0, 0, this);
	}
}
public class Customer extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuBar jmb = new JMenuBar();
	JMenu Message = new JMenu("个人业务");
	JMenu logpassword = new JMenu("密码");
	JMenu Find = new JMenu("查询");
	JMenu exit = new JMenu("退出");
	JMenuItem Item1 = new JMenuItem("存款");
	JMenuItem Item2 = new JMenuItem("取款");
	JMenuItem Item3 = new JMenuItem("转账");
	JMenuItem Item4 = new JMenuItem("查询余额");
	JMenuItem Item5 = new JMenuItem("修改密码");
	JMenuItem Item6 = new JMenuItem("退出");
	JPanel jp1 = new JPanel();
	public Customer(){
		super("Customer");
		this.setSize(500, 400);
		setLocationRelativeTo(null);
		setContentPane(new Mypanel());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(jmb);
		jp1.setBackground(Color.white);
		this.add(jp1);
		jmb.add(Message);
		jmb.add(logpassword);
		jmb.add(Find);
		jmb.add(exit);
		Message.add(Item1);
		Message.add(Item2);
		Message.add(Item3);
		Find.add(Item4);
		logpassword.add(Item5);
		exit.add(Item6);
		Item1.addActionListener(this);
		Item2.addActionListener(this);
		Item3.addActionListener(this);
		Item4.addActionListener(this);
		Item5.addActionListener(this);
		Item6.addActionListener(this);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Customer cust = new Customer();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Item1){
			Custdepoist cd = new Custdepoist();
		}
		else if(e.getSource() == Item2){
			Custwithdraw cw = new Custwithdraw();
		}
		else if(e.getSource() == Item3){
			Custtransfer ct = new Custtransfer();
		}
		else if(e.getSource() == Item4){
			Custfind cf = new Custfind();
		}
		else if(e.getSource() == Item5){
			custlogpassword clp = new custlogpassword();
		}
		else
			System.exit(0);
		
		
	}
}
