import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 20316532L;

	private CardLayout contents = new CardLayout();

	private MngPanelSet mngPanelSet = new MngPanelSet();
	private WaiterPanelSet waiterPanelSet = new WaiterPanelSet();
	private KAPanelSet kaPanelSet = new KAPanelSet();
	private LogInPanelSet logInPanelSet = new LogInPanelSet();

	public UserInterface(){
		this.init();

		this.event();

		this.contents.show(this.getContentPane(), "logInPanel");

		this.setVisible(true);
	}
	public void setContents(){
		this.getContentPane().add("logInPanel", this.logInPanelSet.logInPanel);
		this.getContentPane().add("mngPanel", this.mngPanelSet.mngPanel);
		this.getContentPane().add("waiterPanel", this.waiterPanelSet.waiterPanel);
		this.getContentPane().add("kaPanel", this.kaPanelSet.KAPanel);
	}
	public void init(){
		this.setSize(500, 750);

		this.setLocation(250, 250);
		
		this.getContentPane().setLayout(this.contents);

		this.setContents();
	}
	public void event(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.logInPanelSet.logInBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == this.logInPanelSet.logInBtn){
			String tempId = new String(this.logInPanelSet.idTxt.getText());
			String tempPass = new String(this.logInPanelSet.passPass.getPassword());
			Employee tempEmployee = LogInControlObject.logIn(tempId, tempPass);
			if(tempEmployee == null)
				return;
			if(tempEmployee.getType().equals("M"))
				this.contents.show(this.getContentPane(), "mngPanel");
			else if(tempEmployee.getType().equals("W"))
				this.contents.show(this.getContentPane(), "waiterPanel");
			else if(tempEmployee.getType().equals("KA"))
				this.contents.show(this.getContentPane(), "kaPanel");

			
			System.out.println("----------User Information----------");
			System.out.println(tempEmployee.getType() + " " + tempEmployee.getName());
		}
	
	}
}
class MngPanelSet{
	//mngPanel
	public JPanel mngPanel = new JPanel();

	public void setMngPanel(){
		this.mngPanel.setBackground(Color.BLUE);
	}

	//mngPanel
	
	public MngPanelSet(){
		this.setMngPanel();
	}
}

class WaiterPanelSet{
	//WaiterPanel
	public JPanel waiterPanel = new JPanel();
	public void setWaiterPanel(){
		this.waiterPanel.setBackground(Color.GREEN);
	}
	//WaiterPanel
	public WaiterPanelSet(){
		this.setWaiterPanel();
	}

}
class KAPanelSet{
	//KAPanel
	public JPanel KAPanel = new JPanel();
	public void setKAPanel(){
		this.KAPanel.setBackground(Color.YELLOW);
	}
	//KAPanel
	public KAPanelSet(){
		this.setKAPanel();
	}
}

class LogInPanelSet{
	//logInPanel
	public JPanel logInPanel = new JPanel();
	
	public JLabel autoServeLogo = new JLabel(new ImageIcon("../img/autoServeLogo.png"));

	public JLabel idLabel = new JLabel("ID : ", JLabel.LEFT);
	public JLabel passLabel = new JLabel("PW : ", JLabel.LEFT);

	public JTextField idTxt = new JTextField(10);
	public JPasswordField passPass = new JPasswordField(10);

	public JButton logInBtn = new JButton("Log in");
	public JButton withoutLogInBtn = new JButton("Without log in");

	public void setLogInPanel(){
		this.logInPanel.setLayout(null);
		this.logInPanel.setBackground(new Color(250, 255, 168));

		this.idLabel.setBounds(95, 450, 30, 25);
		this.idLabel.setFont(new Font(this.idLabel.getName(), Font.BOLD, 11));
		this.logInPanel.add(this.idLabel);

		this.idTxt.setBounds(130, 450, 155, 25);
		this.logInPanel.add(this.idTxt);

		this.passLabel.setBounds(95, 480, 30, 25);
		this.passLabel.setFont(new Font(this.passLabel.getName(), Font.BOLD, 11));
		this.logInPanel.add(this.passLabel);

		this.passPass.setBounds(130, 480, 155, 25);
		this.logInPanel.add(this.passPass);

		this.logInBtn.setBounds(290, 450, 55, 55);
		this.logInBtn.setFont(new Font(this.logInBtn.getName(), Font.BOLD, 11));
		this.logInBtn.setHorizontalAlignment(SwingConstants.CENTER);
		this.logInBtn.setText("<html><body>Log<br>in</body></html>");
		this.logInPanel.add(this.logInBtn);
		
		this.withoutLogInBtn.setBounds(350, 450, 55, 55);
		this.withoutLogInBtn.setFont(new Font(this.withoutLogInBtn.getName(), Font.BOLD, 11));
		this.withoutLogInBtn.setHorizontalAlignment(SwingConstants.CENTER);
		this.withoutLogInBtn.setText("<html><body>No<br>member</body></html>");
		this.logInPanel.add(this.withoutLogInBtn);

		this.autoServeLogo.setBounds(125, 600, 250, 70);
		this.logInPanel.add(this.autoServeLogo);
	}
	//logInPanel

	public LogInPanelSet(){
		this.setLogInPanel();
	
	}
}
