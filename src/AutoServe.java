public class AutoServe{
	public static void main(String[] args){
		//generate tables and set empty
		HallStatus.init();	

		//load menu list from database
		MenuTable.loadMenuList();
		//load ingredient information from databse
		KitchenStatus.loadSelectedMenuList();
		KitchenStatus.loadOrderList();
		KitchenStatus.loadIngredientList();


		//check menu availability
		MenuTable.updateMenuListAvailability();	
		


		new UserInterface();
		
	/*	
		//Customer sends order(s) to Waiter
		Customer customer = new Customer();
		customer.sendOrderToWaiter(5, 3);	//Table number:5 
											//Menu 3: Chicken
											//It will be indexed as 1 (the first order)
		customer.sendOrderToWaiter(5, 4);	//Table number: 5
											//Menu 4: Steak
											//It will be indexed as 2 (the second order)

		//KitchenStatus has two lists
		//	KitchenStatus.selecteMenuList
		//		List of menu items Customer wants
		//	KitchenStatus.orderList
		//		List of menu wating to be cooked
		KitchenStatus.printSelectedMenuList();
		
		//HallStatus.printTableInfoAll();
		
		HallStatus.getTable(5).printTableInfo();

		Waiter waiter = new Waiter();
		waiter.confirmOrder(5,1);	//Associate 1st order to table 5
		waiter.confirmOrder(5,2);	//Associate 2nd order to table 5

		KitchenStatus.printSelectedMenuList();
		KitchenStatus.printOrderList();

		//HallStatus.printTableInfoAll();
		
		HallStatus.getTable(5).printTableInfo();

		customer.cancelOrder(5, 1);
	
		HallStatus.getTable(5).printTableInfo();
		
		customer.cancelOrder(5, 2);

		HallStatus.getTable(5).printTableInfo();

		customer.sendOrderToWaiter(10, 1); //-> 3

		waiter.confirmOrder(10, 3);

		HallStatus.getTable(10).printTableInfo();

		OrderControlObject orderCO = new OrderControlObject();
		orderCO.serveOrder(10, 3);

		HallStatus.getTable(10).printTableInfo();
*/

		/*
		KitchenStatus.loadIngredientList();
		KitchenStatus.printIngredientList();
		*/


		/*
		
		MenuTable.loadMenuList();
		MenuTable.printMenuListWithIngredient();

		KitchenStatus.loadIngredientList();
		MenuTable.updateMenuListAvailability();
		MenuTable.printMenuListWithIngredient();
		*/

		/*
		ComplainList.addComplain(5, "Too slow!");	//1
		ComplainList.addComplain(10, "Dirty...");	//2
		ComplainList.addComplain(1, "Where is out waiter?");	//3
		ComplainList.addComplain(2, "Hair in the food?!");	//4
		ComplainList.addComplain(3, "Bugs in the food...");	//5

		ComplainList.printComplainList();

		ComplainList.removeComplain(3);

		ComplainList.printComplainList();

		ComplainList.removeComplain(10);
		ComplainList.removeComplain(11);

		ComplainList.printComplainList();
		
		ComplainList.removeComplain(1);
		ComplainList.removeComplain(2);
		ComplainList.removeComplain(3);
		ComplainList.removeComplain(4);
		ComplainList.removeComplain(5);
		
		ComplainList.printComplainList();
		*/
	}
}

