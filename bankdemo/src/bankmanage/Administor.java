package bankmanage;

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

class Mypanel1 extends JPanel{
	private static final long serialVersionUID = 1L;
	Image img2 = Toolkit.getDefaultToolkit().getImage("images/background_3.jpg");
	public void paint(Graphics g)
	{
		g.drawImage(img2, 0, 0, this);
	}
}

public class Administor extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuBar bar = new JMenuBar();
	JMenu menu1 = new JMenu("�û�����");
	JMenu menu2 = new JMenu("�ܴ���");
	JMenu menu3 = new JMenu("����");
	JMenu menu4 = new JMenu("�˳�");
	
	JMenuItem item1 = new JMenuItem("����");
	JMenuItem item5 = new JMenuItem("����");
	JMenuItem item2 = new JMenuItem("�������а�");
	JMenuItem item3 = new JMenuItem("�޸�����");
	JMenuItem item4 = new JMenuItem("�˳�");
	JPanel jp2 = new JPanel();
	public Administor(){
		super("Admin");
		this.setSize(500,400);
		setLocationRelativeTo(null);
		setContentPane(new Mypanel1());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(bar);
		this.add(jp2);
		jp2.setLayout(null);
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		menu1.add(item1);
		menu1.add(item5);
		menu2.add(item2);
		menu3.add(item3);
		menu4.add(item4);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == item1){
			adminaddmessage adm = new adminaddmessage();
		}
		else if(e.getSource() == item2){
			Admincount adct = new Admincount();
		}
		else if(e.getSource() == item3){
			adminlogpassword alpd = new adminlogpassword();
		}
		else if(e.getSource() == item5){
			Admindelete ade = new Admindelete();
		}
		else{
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Administor ad = new Administor();
	}

}
