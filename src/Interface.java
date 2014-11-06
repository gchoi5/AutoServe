import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 20316532L;

	private String log;

	private CardLayout contents = new CardLayout();

	private MngPanelSet mngPanelSet = new MngPanelSet();
	private WaiterPanelSet waiterPanelSet = new WaiterPanelSet();
	private KAPanelSet kaPanelSet = new KAPanelSet();
	private LogInPanelSet logInPanelSet = new LogInPanelSet();
	private MenuPanelSet menuPanelSet = new MenuPanelSet();
	private CustomerPanelSet customerPanelSet = new CustomerPanelSet();
	private KitchenPanelSet kitchenPanelSet = new KitchenPanelSet();

	public UserInterface(){
		this.init();

		this.event();

		this.setResizable(false);
		this.contents.show(this.getContentPane(), "logInPanel");
		//for testing
		//this.contents.show(this.getContentPane(), "menuFramePanel");
		

		this.setVisible(true);
	}
	public void setContents(){
		this.getContentPane().add("logInPanel", this.logInPanelSet.logInPanel);
		this.getContentPane().add("mngPanel", this.mngPanelSet.mngPanel);
		this.getContentPane().add("waiterPanel", this.waiterPanelSet.waiterPanel);
		this.getContentPane().add("waiterOrdReqPanel", this.waiterPanelSet.orderReqFramePanel);
		this.getContentPane().add("kaPanel", this.kaPanelSet.mainPanel);
		this.getContentPane().add("menuFramePanel", this.menuPanelSet.menuFramePanel);
		
		this.getContentPane().add("customerMainPanel", this.customerPanelSet.mainPanel);
		this.getContentPane().add("customerComplainPanel", this.customerPanelSet.complainPanel);
		this.getContentPane().add("customerTabNumPanel", this.customerPanelSet.tabNumPanel);
		this.getContentPane().add("customerOrderListPanel", this.customerPanelSet.orderListPanel);
		
		this.getContentPane().add("kitchenOrderList", this.kitchenPanelSet.orderListPanel);
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
		this.logInPanelSet.withoutLogInBtn.addActionListener(this);

		this.mngPanelSet.goTokitchenOrderList.addActionListener(this);
		this.mngPanelSet.goBackToLogIn.addActionListener(this);

		this.waiterPanelSet.cstmOrderReq.addActionListener(this);
		this.waiterPanelSet.submitOrdReqBtn.addActionListener(this);
		this.waiterPanelSet.goBackFromOrdReqBtn.addActionListener(this);
		this.waiterPanelSet.goBackFromWaiterMainBtn.addActionListener(this);
		this.waiterPanelSet.goToKitchenOrderList.addActionListener(this);

		this.kaPanelSet.mainOrdListBtn.addActionListener(this);
		this.kaPanelSet.mainGoBackBtn.addActionListener(this);

		this.menuPanelSet.sendOrderBtn.addActionListener(this);
		this.menuPanelSet.goBackBtn.addActionListener(this);

		this.customerPanelSet.menuTableBtn.addActionListener(this);
		this.customerPanelSet.complainBtn.addActionListener(this);
		this.customerPanelSet.goBackBtn.addActionListener(this);
		this.customerPanelSet.complainSubmitBtn.addActionListener(this);
		this.customerPanelSet.complainGoBackBtn.addActionListener(this);
		this.customerPanelSet.submitBtn.addActionListener(this);
		this.customerPanelSet.ordListBtn.addActionListener(this);

		this.kitchenPanelSet.goBackFromOrderListPanelBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == this.logInPanelSet.logInBtn){
			String tempId = new String(this.logInPanelSet.idTxt.getText());
			String tempPass = new String(this.logInPanelSet.passPass.getPassword());
			this.logInPanelSet.idTxt.setText("");
			this.logInPanelSet.passPass.setText("");
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
		else if(ae.getSource() == this.logInPanelSet.withoutLogInBtn){
			this.contents.show(this.getContentPane(), "customerMainPanel");
		}
		else if(ae.getSource() == this.waiterPanelSet.cstmOrderReq){
			KitchenStatus.loadSelectedMenuList();
			this.waiterPanelSet.orderReqListPanel.removeAll();
			this.waiterPanelSet.orderReqListPanel.revalidate();
			this.waiterPanelSet.orderReqFramePanel.removeAll();
			this.waiterPanelSet.orderReqFramePanel.revalidate();
			this.waiterPanelSet.setOrderReqFramePanel();
			this.contents.show(this.getContentPane(), "waiterOrdReqPanel");

		}
		else if(	ae.getSource() == this.kaPanelSet.mainOrdListBtn ||
					ae.getSource() == this.mngPanelSet.goTokitchenOrderList ||
					ae.getSource() == this.waiterPanelSet.goToKitchenOrderList){
					
			if(ae.getSource() == this.kaPanelSet.mainOrdListBtn){
				log = "from ka";
			}
			else if(ae.getSource() == this.mngPanelSet.goTokitchenOrderList){
				log = "from mng";
			}
			else if(ae.getSource() == this.waiterPanelSet.goToKitchenOrderList){
				log = "from waiter";
			}
			KitchenStatus.loadOrderList();
			this.kitchenPanelSet.orderListBody.removeAll();
			this.kitchenPanelSet.orderListBody.revalidate();
			this.kitchenPanelSet.orderListPanel.removeAll();
			this.kitchenPanelSet.orderListPanel.revalidate();
			this.kitchenPanelSet.setOrderListPanel();

			Iterator<KitchenBtn> tempKitchenBtn = this.kitchenPanelSet.statusBtnList.iterator();
			while(tempKitchenBtn.hasNext()){
				tempKitchenBtn.next().addActionListener(this);
			}

			tempKitchenBtn = this.kitchenPanelSet.submitBtnList.iterator();
			while(tempKitchenBtn.hasNext()){
				tempKitchenBtn.next().addActionListener(this);
			}



			this.contents.show(this.getContentPane(), "kitchenOrderList");
		}
		else if(this.kitchenPanelSet.statusBtnList.contains(ae.getSource())){
			KitchenBtn tempStatusBtn = (KitchenBtn)ae.getSource();
			tempStatusBtn.toggleStatus();
			KitchenStatus.getFromOrderList(tempStatusBtn.getOrderIdx()).setStatus(tempStatusBtn.getStatus());

			KitchenStatus.printOrderList();
		}
		else if(this.kitchenPanelSet.submitBtnList.contains(ae.getSource())){
			KitchenBtn tempSubmitBtn = (KitchenBtn)ae.getSource();


			OrderControlObject orderCO = new OrderControlObject();
			orderCO.serveOrder(KitchenStatus.getFromOrderList(tempSubmitBtn.getOrderIdx()).getTabNum(), tempSubmitBtn.getOrderIdx());

			HallStatus.printTableInfoAll();
			KitchenStatus.printOrderList();

			this.kitchenPanelSet.orderListBody.removeAll();
			this.kitchenPanelSet.orderListBody.revalidate();
			this.kitchenPanelSet.orderListPanel.removeAll();
			this.kitchenPanelSet.orderListPanel.revalidate();
			this.kitchenPanelSet.setOrderListPanel();
		}
		else if(	ae.getSource() == this.kaPanelSet.mainGoBackBtn || 
					ae.getSource() == this.mngPanelSet.goBackToLogIn){
			this.contents.show(this.getContentPane(), "logInPanel");
		}
		else if(ae.getSource() == this.waiterPanelSet.goBackFromWaiterMainBtn){
			this.contents.show(this.getContentPane(), "logInPanel");
		}
		else if(ae.getSource() == this.waiterPanelSet.goBackFromOrdReqBtn){
			this.contents.show(this.getContentPane(), "waiterPanel");
		}
		else if(ae.getSource() == this.waiterPanelSet.submitOrdReqBtn){
			Iterator<MyTextField> tempTabNumTfIdxItr = this.waiterPanelSet.ordReqTabNumTFList.iterator();
			while(tempTabNumTfIdxItr.hasNext()){
				MyTextField tempTabNumTf = tempTabNumTfIdxItr.next();
				System.out.println(tempTabNumTf.getIdx());
				if(!tempTabNumTf.getText().equals("")){
					new OrderControlObject().confirmOrder(Integer.parseInt(tempTabNumTf.getText()), tempTabNumTf.getIdx());
					tempTabNumTfIdxItr.remove();
					KitchenStatus.printSelectedMenuList();
					KitchenStatus.printOrderList();
				}
			}
			KitchenStatus.loadSelectedMenuList();
			this.waiterPanelSet.orderReqListPanel.removeAll();
			this.waiterPanelSet.orderReqListPanel.revalidate();
			this.waiterPanelSet.orderReqFramePanel.removeAll();
			this.waiterPanelSet.orderReqFramePanel.revalidate();
			this.waiterPanelSet.setOrderReqFramePanel();
		}
		else if(ae.getSource() == this.customerPanelSet.menuTableBtn){
			KitchenStatus.loadIngredientList();
			KitchenStatus.printIngredientList();
			MenuTable.updateMenuListAvailability();
			this.menuPanelSet.menuListPanel.removeAll();
			this.menuPanelSet.menuListPanel.revalidate();
			this.menuPanelSet.menuFramePanel.removeAll();
			this.menuPanelSet.menuFramePanel.revalidate();
			this.menuPanelSet.setMenuListPanel();
			this.contents.show(this.getContentPane(), "menuFramePanel");
		}
		else if(ae.getSource() == this.customerPanelSet.goBackBtn){
			this.contents.show(this.getContentPane(), "logInPanel");
		}
		else if(ae.getSource() == this.customerPanelSet.complainBtn){
			this.contents.show(this.getContentPane(), "customerComplainPanel");
		}
		else if(ae.getSource() == this.customerPanelSet.complainSubmitBtn){
			ComplainList.addComplain(	Integer.parseInt(this.customerPanelSet.complainTabNumTxt.getText()), 
										this.customerPanelSet.complainiContentsTxt.getText());
			ComplainList.printComplainList();

			this.customerPanelSet.complainTabNumTxt.setText("");
			this.customerPanelSet.complainiContentsTxt.setText("");

		
		}
		else if(ae.getSource() == this.customerPanelSet.complainGoBackBtn){
			this.contents.show(this.getContentPane(), "customerMainPanel");
		}
		else if(ae.getSource() == this.customerPanelSet.ordListBtn){
			this.contents.show(this.getContentPane(), "customerTabNumPanel");
			
		}
		else if(ae.getSource() == this.customerPanelSet.submitBtn){
			this.contents.show(this.getContentPane(), "customerOrderList");
			
		}
		else if(ae.getSource() == this.menuPanelSet.sendOrderBtn){
			Iterator<JCheckBox> tempMenuBtnItr = this.menuPanelSet.menuBtnList.iterator();			
			while(tempMenuBtnItr.hasNext()){
				JCheckBox tempMenuBtn = tempMenuBtnItr.next();
				if(tempMenuBtn.isSelected()){
					tempMenuBtn.setSelected(false);
					int tempMenuIdx = Integer.parseInt(tempMenuBtn.getText());
					System.out.println(tempMenuIdx);
					
					new OrderControlObject().sendOrderToWaiter(100, tempMenuIdx);
				}
			}

			KitchenStatus.printSelectedMenuList();

			//this.waiterPanelSet.setOrderReqFramePanel();
		}
		else if(ae.getSource() == this.menuPanelSet.goBackBtn){
			this.contents.show(this.getContentPane(), "customerMainPanel");
		}
		else if(ae.getSource() == this.kitchenPanelSet.goBackFromOrderListPanelBtn){
			if(log.equals("from ka")){
				this.contents.show(this.getContentPane(), "kaPanel");
			}
			else if(log.equals("from mng")){
				this.contents.show(this.getContentPane(), "mngPanel");
			}
			else if(log.equals("from waiter")){
				this.contents.show(this.getContentPane(), "waiterPanel");
			}
		}
	
	}
}
class MngPanelSet{
	//mngPanel
	public JPanel mngPanel = new JPanel();

	public JButton goTokitchenOrderList = new JButton("Order List");
	public JButton goBackToLogIn = new JButton("Go Back");

	public void setMngPanel(){
		this.mngPanel.setLayout(null);

		this.goTokitchenOrderList.setBounds(50, 50, 400, 50);
		this.mngPanel.add(this.goTokitchenOrderList);

		this.goBackToLogIn.setBounds(50, 150, 400, 50);
		this.mngPanel.add(this.goBackToLogIn);
	}

	//mngPanel
	
	public MngPanelSet(){
		this.setMngPanel();
	}
}
class MyTextField extends JTextField{
	private int idx;

	/**
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * @param idx the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}
}

class WaiterPanelSet{
	//waiterPanel
	public JPanel waiterPanel = new JPanel();

	public JButton cstmOrderReq = new JButton("Customer Order Request");
	public JButton goBackFromWaiterMainBtn = new JButton("Go Back");
	public JButton goToKitchenOrderList = new JButton("Order List");



	public void setWaiterPanel(){
		this.cstmOrderReq.setBounds(50, 50, 400, 100);
		this.waiterPanel.add(this.cstmOrderReq);

		this.goBackFromWaiterMainBtn.setBounds(50, 200, 400, 100);
		this.waiterPanel.add(this.goBackFromWaiterMainBtn);

		this.goToKitchenOrderList.setBounds(50, 350, 400, 100);
		this.waiterPanel.add(this.goToKitchenOrderList);
	}
	//waiterPanel end
	
	//orderReqPanel start
	public JPanel orderReqFramePanel = new JPanel();
	
	public JPanel orderReqFrameTitlePanel = new JPanel();

	public JPanel orderReqListPanel = new JPanel();
	public JScrollPane orderReqListScroll = new JScrollPane(orderReqListPanel);

	public LinkedList<MyTextField> ordReqTabNumTFList = new LinkedList<MyTextField>();
	public JButton submitOrdReqBtn = new JButton("Submit");
	public JButton goBackFromOrdReqBtn = new JButton("Go Back");

	
	public void setOrderReqFrameTitlePanel(){
		JLabel tempOrderReqLb = new JLabel("Order Request", JLabel.LEFT);
		tempOrderReqLb.setFont(new Font("", Font.BOLD, 25));
	
		tempOrderReqLb.setBounds(25, 25, 275, 100);
		this.orderReqFrameTitlePanel.add(tempOrderReqLb);

		this.submitOrdReqBtn.setBounds(325, 25, 150, 25);
		this.orderReqFrameTitlePanel.add(this.submitOrdReqBtn);

		this.goBackFromOrdReqBtn.setBounds(325, 75, 150, 25);
		this.orderReqFrameTitlePanel.add(this.goBackFromOrdReqBtn);
	

		this.orderReqFrameTitlePanel.setBounds(0, 0, 500, 150);
		this.orderReqFramePanel.add(this.orderReqFrameTitlePanel);
	}
	public void setOrderReqListPanel(){
		this.orderReqListScroll.setPreferredSize(new Dimension(500, 600));
		this.orderReqListScroll.setWheelScrollingEnabled(true);
		this.orderReqListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.orderReqListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.orderReqListPanel.setLayout(new GridLayout(0, 2, 5, 5));

		Iterator<OrderedMenuItem> tempSelectedMenuListItr = KitchenStatus.selectedMenuList.iterator();

		int tempTabNumTfIdx = 0;
		while(tempSelectedMenuListItr.hasNext()){
			System.out.println("update");
			OrderedMenuItem tempOrderedMenuItem = tempSelectedMenuListItr.next();

			//test
			tempOrderedMenuItem.printOrderedMenuItem();

			JPanel tempOrderedMenuItemInfo = new JPanel(new GridLayout(3, 1, 5, 5));
			tempOrderedMenuItemInfo.add(new JLabel("" + tempOrderedMenuItem.getIdx()));
			tempOrderedMenuItemInfo.add(new JLabel(tempOrderedMenuItem.getTitle()));
			tempOrderedMenuItemInfo.add(new JLabel(tempOrderedMenuItem.getStatus()));
			this.orderReqListPanel.add(tempOrderedMenuItemInfo);
			//this.orderReqListPanel.add(new JLabel("tempTA"));
			
			this.ordReqTabNumTFList.add(new MyTextField());
			this.ordReqTabNumTFList.get(tempTabNumTfIdx).setIdx(tempOrderedMenuItem.getOrderIdx()); 
			this.ordReqTabNumTFList.get(tempTabNumTfIdx).setPreferredSize(new Dimension(25, 25));
			this.orderReqListPanel.add(this.ordReqTabNumTFList.get(tempTabNumTfIdx));

			tempTabNumTfIdx++;

		}
		this.orderReqListPanel.add(new JLabel(""));
		this.orderReqListPanel.add(new JLabel(""));

		this.orderReqListScroll.setBounds(0, 150, 500, 600);
		this.orderReqFramePanel.add(this.orderReqListScroll);
	}
	public void setOrderReqFramePanel(){
		this.ordReqTabNumTFList.clear();
		this.orderReqFramePanel.setLayout(null);
		this.setOrderReqListPanel();
		this.orderReqFrameTitlePanel.setLayout(null);
		this.setOrderReqFrameTitlePanel();

	}
	//orderReqPanel end
	public WaiterPanelSet(){
		this.waiterPanel.setLayout(null);
		this.setWaiterPanel();
		//this.setOrderReqFramePanel();
	}

}
class KAPanelSet{
	//mainPanel begin
	public JPanel mainPanel = new JPanel();
	
	public JButton mainOrdListBtn = new JButton("Order List");
	public JButton mainGoBackBtn = new JButton("Go Back");

	public void setMainPanel(){
		this.mainPanel.setLayout(null);

		this.mainOrdListBtn.setBounds(50, 50, 400, 100);
		this.mainPanel.add(this.mainOrdListBtn);

		this.mainGoBackBtn.setBounds(50, 200, 400, 100);
		this.mainPanel.add(this.mainGoBackBtn);
	}
	//mainPanel end
	
	//Constructor begin
	public KAPanelSet(){
		this.setMainPanel();
	}
	//Constructor end
}
class CustomerPanelSet{
	//mainPanel start
	public JPanel mainPanel = new JPanel();	
	public JButton menuTableBtn = new JButton("Menu Table");
	public JButton complainBtn = new JButton("Complain");
	public JButton goBackBtn = new JButton("Go Back");
	public JButton ordListBtn = new JButton("Order List");

	public void setMainPanel(){
		this.mainPanel.setLayout(null);

		this.menuTableBtn.setBounds(50, 50, 400, 50);
		this.mainPanel.add(this.menuTableBtn);

		this.complainBtn.setBounds(50, 150, 400, 50);
		this.mainPanel.add(this.complainBtn);	

		this.goBackBtn.setBounds(50, 250, 400, 50);
		this.mainPanel.add(this.goBackBtn);
		
		this.ordListBtn.setBounds(50, 350, 400, 50);
		this.mainPanel.add(this.ordListBtn);
	}
	//mainPanel end
	
	//complainPanel start
	public JPanel complainPanel = new JPanel();

	public JLabel complainTabNumLb = new JLabel("Table number: ", JLabel.LEFT);
	public JTextArea complainTabNumTxt = new JTextArea();

	public JLabel complainContentsLb = new JLabel("Contents: ", JLabel.LEFT);
	public JTextArea complainiContentsTxt = new JTextArea();

	public JButton complainSubmitBtn = new JButton("Submit");
	public JButton complainGoBackBtn = new JButton("Go Back");

	public void setComplainPanel(){
		this.complainPanel.setLayout(null);

		this.complainTabNumLb.setBounds(50, 50, 150, 50);
		this.complainTabNumTxt.setBounds(200, 50, 250, 50); 
		this.complainContentsLb.setBounds(50, 100, 400, 50);
		this.complainiContentsTxt.setBounds(50, 150, 400, 450);	
		this.complainSubmitBtn.setBounds(50, 650, 175, 50);
	   	this.complainGoBackBtn.setBounds(275, 650, 175, 50); 	

		this.complainPanel.add(this.complainTabNumLb);
		this.complainPanel.add(this.complainTabNumTxt);
		this.complainPanel.add(this.complainContentsLb);
		this.complainPanel.add(this.complainiContentsTxt);
		this.complainPanel.add(this.complainSubmitBtn);
		this.complainPanel.add(this.complainGoBackBtn);
	}
	//complainPanel end

	//tabNumPanel start
	public JPanel tabNumPanel = new JPanel();
	public JTextArea tabNumTA = new JTextArea();
	public JButton submitBtn = new JButton("Submit");
	
	public void setTabNumPanel(){
		this.tabNumPanel.setLayout(null);
		
		this.tabNumTA.setBounds(50, 300, 400, 50);
		this.tabNumPanel.add(this.tabNumTA);
		
		this.submitBtn.setBounds(50, 400, 400, 50);
		this.tabNumPanel.add(this.submitBtn);
		
	}
	//tabNumPanel end
	
	//ordListPanel start
	public JPanel orderListPanel = new JPanel();
	
	public JPanel orderListTitle = new JPanel();
	
	public JPanel orderListBody = new JPanel();
	public JScrollPane orderListScroll = new JScrollPane(orderListBody);
	
	public LinkedList<KitchenBtn> cancelBtnList = new LinkedList<KitchenBtn>();
	public JButton goBackFromOrdListBtn = new JButton();
	
	public void setOrderListTitle(){
		this.orderListTitle.setLayout(null);
		
		JLabel tempOrderListTitleLb = new JLabel("Order List", JLabel.LEFT);
		tempOrderListTitleLb.setFont(new Font("", Font.BOLD, 25));
		
		tempOrderListTitleLb.setBounds(25, 25, 275, 100);
		this.orderListTitle.add(tempOrderListTitleLb);
		
		this.goBackFromOrdListBtn.setBounds(325, 75, 150, 25);
		this.orderListTitle.add(this.goBackFromOrdListBtn);
		
		this.orderListTitle.setBounds(0, 0, 500, 150);
		this.orderListPanel.add(this.orderListTitle);		
	}
	public void setOrderListBody(int tabNum){
		this.orderListScroll.setPreferredSize(new Dimension(500, 600));
		this.orderListScroll.setWheelScrollingEnabled(true);
		this.orderListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.orderListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.orderListBody.setLayout(new GridLayout(0, 3, 5, 5));
		
		Iterator<OrderedMenuItem> tempOrderListItr = KitchenStatus.orderList.iterator();
		
		
		while(tempOrderListItr.hasNext()){
			OrderedMenuItem tempOrderedMenuItem = tempOrderListItr.next();
			if(tempOrderedMenuItem.getTabNum() != tabNum)
				continue;
			
			JPanel tempOrderedMenuItemInfo = new JPanel(new GridLayout(3, 1));
			tempOrderedMenuItemInfo.add(new JLabel("orderIndex: " + tempOrderedMenuItem.getIdx()));
			tempOrderedMenuItemInfo.add(new JLabel(tempOrderedMenuItem.getTitle()));
			tempOrderedMenuItemInfo.add(new JLabel("tabNum: " + tempOrderedMenuItem.getTabNum()));
			this.orderListBody.add(tempOrderedMenuItemInfo);
			
			this.orderListBody.add(new JLabel(tempOrderedMenuItem.getStatus()));
			
			KitchenBtn tempCancelBtn = new KitchenBtn("Cancel");
			tempCancelBtn.setOrderIdx(tempOrderedMenuItem.getOrderIdx());
			if(tempOrderedMenuItem.getStatus().equals("being cooked"))
				tempCancelBtn.setEnabled(false);
			this.cancelBtnList.add(tempCancelBtn);
			this.orderListBody.add(tempCancelBtn);
		}
		this.orderListBody.add(new JLabel(""));
		this.orderListBody.add(new JLabel(""));
		
		this.orderListScroll.setBounds(0, 150, 500, 600);
		this.orderListPanel.add(this.orderListScroll);
	}
	public void setOrderListPanel(int tabNum){
		this.orderListPanel.setLayout(null);;
		this.cancelBtnList.clear();
		this.setOrderListTitle();
		this.setOrderListBody(tabNum);
		
	}
	//ordListPanel end
	public CustomerPanelSet(){
		this.setMainPanel();
		this.setComplainPanel();
		this.setTabNumPanel();
	}
}

class MenuPanelSet{
	//menuPanel
	public JPanel menuFramePanel = new JPanel();
	
	public JPanel menuTitlePanel = new JPanel();
	public JButton sendOrderBtn = new JButton("Send Order");
	public LinkedList<JCheckBox> menuBtnList = new LinkedList<JCheckBox>();
	public JButton goBackBtn = new JButton("Go Back");

	public JPanel menuListPanel = new JPanel();
	public JScrollPane menuListScroll = new JScrollPane(menuListPanel);

	public void setMenuTitlePanel(){
		this.menuTitlePanel.setLayout(null);

		JLabel tempMenuTableLb = new JLabel("Menu Table", JLabel.LEFT);
		tempMenuTableLb.setFont(new Font("", Font.BOLD, 25));
		tempMenuTableLb.setBounds(25, 25, 275, 100);
		this.menuTitlePanel.add(tempMenuTableLb);

		this.sendOrderBtn.setBounds(325, 25, 150, 25);
		this.menuTitlePanel.add(this.sendOrderBtn);

		this.goBackBtn.setBounds(325, 75, 150, 25);
		this.menuTitlePanel.add(this.goBackBtn);
	
		this.menuTitlePanel.setBounds(0, 0, 500, 150);
		this.menuFramePanel.add(this.menuTitlePanel);
	}
	public void setMenuListPanel(){
		this.setMenuTitlePanel();
		this.menuListScroll.setPreferredSize(new Dimension(500, 600));
		this.menuListScroll.setWheelScrollingEnabled(true);
		this.menuListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.menuListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.menuListPanel.setLayout(new GridLayout(MenuTable.menuNum, 2, 5, 5));

		Iterator<MenuItem> tempMenuListIterator = MenuTable.menuList.iterator();
		int tempMenuIdx = 1;
		while(tempMenuListIterator.hasNext()){
			MenuItem tempMenuItem = tempMenuListIterator.next();

			JLabel tempMenuImgLb = new JLabel(new ImageIcon(tempMenuItem.getImgRef()));
			tempMenuImgLb.setPreferredSize(new Dimension(250, 250));
			this.menuListPanel.add(tempMenuImgLb);

			JPanel tempMenuInfoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
			tempMenuInfoPanel.add(new JLabel(tempMenuItem.getTitle()));
			tempMenuInfoPanel.add(new JLabel("" + tempMenuItem.getPrice()));
			tempMenuInfoPanel.add(new JLabel(tempMenuItem.getStatus()));
			//tempMenuInfoPanel.add(new JCheckBox("" + tempMenuIdx++));
			this.menuBtnList.add(new JCheckBox("" + tempMenuIdx--));
			tempMenuInfoPanel.add(this.menuBtnList.get(tempMenuIdx));
			if(tempMenuItem.getStatus().equals("unavailable"))
				this.menuBtnList.get(tempMenuIdx).setEnabled(false);
			else
				this.menuBtnList.get(tempMenuIdx).setEnabled(true);
			tempMenuInfoPanel.setPreferredSize(new Dimension(250, 250));
			this.menuListPanel.add(tempMenuInfoPanel);

			tempMenuIdx = tempMenuIdx + 2;
		}


		this.menuListScroll.setBounds(0, 150, 500, 600);
		this.menuFramePanel.add(this.menuListScroll);
	}

	public MenuPanelSet(){
		this.menuFramePanel.setLayout(null);

		//this.setMenuTitlePanel();

		//this.setMenuListPanel();
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

class KitchenBtn extends JButton{
	private int orderIdx;

	private String status;
	public KitchenBtn(){
		
	}
	public KitchenBtn(String name){
		super(name);
		
	}
	public void toggleStatus(){
		if(this.status == "being waited"){
			this.status = "being cooked";
			this.setBackground(Color.RED);
		}
		else{
			this.status = "being waited";
			this.setBackground(null);
		}
	}

	/**
	 * @return the orderIdx
	 */
	public int getOrderIdx() {
		return orderIdx;
	}

	/**
	 * @param orderIdx the orderIdx to set
	 */
	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}

class KitchenPanelSet{
	public KitchenPanelSet(){
	}
	//orderListPanel begin
	public JPanel orderListPanel = new JPanel();

	public JPanel orderListTitle = new JPanel();

	public JPanel orderListBody = new JPanel();
	public JScrollPane orderListScroll = new JScrollPane(orderListBody);

	public LinkedList<KitchenBtn> statusBtnList = new LinkedList<KitchenBtn>();
	public LinkedList<KitchenBtn> submitBtnList = new LinkedList<KitchenBtn>();
	public JButton goBackFromOrderListPanelBtn = new JButton("Go Back");

	public void setOrderListTitle(){
		this.orderListTitle.setLayout(null);

		JLabel tempOrderListTitleLb = new JLabel("Order List", JLabel.LEFT);
		tempOrderListTitleLb.setFont(new Font("", Font.BOLD, 25));
		
		tempOrderListTitleLb.setBounds(25, 25, 275, 100);
		this.orderListTitle.add(tempOrderListTitleLb);

		this.goBackFromOrderListPanelBtn.setBounds(325, 75, 150, 25);
		this.orderListTitle.add(this.goBackFromOrderListPanelBtn);

		this.orderListTitle.setBounds(0, 0, 500, 150);
		this.orderListPanel.add(this.orderListTitle);
	}
	public void setOrderListBody(){
		this.orderListScroll.setPreferredSize(new Dimension(500, 600));
		this.orderListScroll.setWheelScrollingEnabled(true);
		this.orderListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.orderListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
		this.orderListBody.setLayout(new GridLayout(0, 3, 5, 5));

		Iterator<OrderedMenuItem> tempOrderListItr = KitchenStatus.orderList.iterator();

		while(tempOrderListItr.hasNext()){
			OrderedMenuItem tempOrderedMenuItem = tempOrderListItr.next();

			JPanel tempOrderedMenuItemInfo = new JPanel(new GridLayout(3, 1));
			tempOrderedMenuItemInfo.add(new JLabel("orderIndex: " + tempOrderedMenuItem.getIdx()));
			tempOrderedMenuItemInfo.add(new JLabel(tempOrderedMenuItem.getTitle()));
			tempOrderedMenuItemInfo.add(new JLabel("tabNum: " + tempOrderedMenuItem.getTabNum()));
			this.orderListBody.add(tempOrderedMenuItemInfo);

			KitchenBtn tempKitchenBtn = new KitchenBtn("Status");
			tempKitchenBtn.setOrderIdx(tempOrderedMenuItem.getOrderIdx());
			tempKitchenBtn.setStatus(tempOrderedMenuItem.getStatus());
			this.statusBtnList.add(tempKitchenBtn);
			this.orderListBody.add(tempKitchenBtn);

			KitchenBtn tempSubmitBtn = new KitchenBtn("Submit");
			tempSubmitBtn.setOrderIdx(tempOrderedMenuItem.getOrderIdx());
			this.submitBtnList.add(tempSubmitBtn);
			this.orderListBody.add(tempSubmitBtn);
		}
		this.orderListBody.add(new JLabel(""));
		this.orderListBody.add(new JLabel(""));

		this.orderListScroll.setBounds(0, 150, 500, 600);
		this.orderListPanel.add(this.orderListScroll);
	}
	public void setOrderListPanel(){
		this.orderListPanel.setLayout(null);
		this.submitBtnList.clear();
		this.statusBtnList.clear();
		this.setOrderListTitle();
		this.setOrderListBody();
	}
	//orderListPanel end
}
