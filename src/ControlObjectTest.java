
import java.util.*;
import java.math.BigInteger;
import java.security.SecureRandom;


public class ControlObjectTest{
	public static String randomStringGen(){
		return new BigInteger(130, new SecureRandom()).toString(32);
	
	}
	public static String[] validIdPassGen(){
		String[][] validIdPass = { 	{"admin","1234"},
									{"waiter01","1234"},
									{"ka01","1234"},
									{"temp01","1234"}};
	
		return validIdPass[new Random().nextInt(4)];

	}
	public static int tabNumGen(String cond){
		Random rand = new Random();

		if(cond.equals("A")){
			return (rand.nextInt(20) + 1);
		}

		int tempTabNum = rand.nextInt();
		while(1 <= tempTabNum && tempTabNum <= 20){
			tempTabNum = rand.nextInt();
		}

		return tempTabNum;
	}
	public static int menuIdxGen(String cond){
		Random rand = new Random();

		if(cond.equals("A")){
			return (rand.nextInt(5) + 1);
		}

		int tempMenuIdx = rand.nextInt();
		while(1 <= tempMenuIdx && tempMenuIdx <= 5){
			tempMenuIdx = rand.nextInt();
		}

		return tempMenuIdx;
	
	}
	public static void main(String[] args){
		/**
		 * OrderControlObject
		 */
		/**
		 * sendOrderToWaiter
		 * 	input: (int tabNum, int menuItemIdx)
		 * 	output: boolean and updated KitchenStatus.selectedMenuList
		 */
		OrderControlObject orderCO = new OrderControlObject();

		MenuTable.loadMenuList();
		KitchenStatus.getSelectedMenuList().clear();
		if(	orderCO.sendOrderToWaiter(tabNumGen("A"), menuIdxGen("A")) == true &&
			KitchenStatus.getSelectedMenuList().isEmpty() == false){
			
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with valid table number and valid menu index was passed.");
		}	
		else{
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with valid table number and valid menu index was failed.");
		}

		
		KitchenStatus.getSelectedMenuList().clear();
		if( orderCO.sendOrderToWaiter(tabNumGen("A"), menuIdxGen("B")) == false &&
			KitchenStatus.getSelectedMenuList().isEmpty() == true){
			
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with valid table number and invalid menu index was passed.");
		}
		else{
		
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with valid table number and invalid menu index was failed.");
		}

		KitchenStatus.getSelectedMenuList().clear();
		if( orderCO.sendOrderToWaiter(tabNumGen("B"), menuIdxGen("A")) == false &&
			KitchenStatus.getSelectedMenuList().isEmpty() == true){
			
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with invalid table number and valid menu index was passed.");
		}
		else{
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with invalid table number and valid menu index was failed.");
		
		}

		KitchenStatus.getSelectedMenuList().clear();
		if( orderCO.sendOrderToWaiter(tabNumGen("B"), menuIdxGen("B")) == false ){
			//&&
			//KitchenStatus.getSelectedMenuList().isEmpty() == true){
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with invalid table number and invalid menu index was passed.");
			
		}
		else{
			System.out.println("Testing OrderControlObject.sendOrderToWaiter with invalid table number and invalid menu index was failed.");
		
		}

		System.out.println();
		/**
		 * cancelOrderToWaiter
		 * 	input: (int tabNum, int orderListIdx)
		 * 	output: boolean and updated KitchenStatus.selectedMenuList
		 */
/*
		KitchenStatus.getSelectedMenuList().clear();
		KitchenStatus.nextSelectedMenuListIndex = 1;
		LinkedList<Integer> validOccupiedTabNum = new LinkedList<Integer>();
		LinkedList<Integer> invalidOccupiedTabNum = new LinkedList<Integer>();
		while(true){
			int tempTabNum = tabNumGen("A");
			orderCO.sendOrderToWaiter(tempTabNum, menuIdxGen("A"));
			validOccupiedTabNum.add(tempTabNum);

			if(new Random().nextInt(10) == 0){
				break;
			}
		
		}
*/
		System.out.println();
		/**
		 * LogInControlObject
		 */
		/**
		 * logIn
		 * 		input: (String id, String pass)
		 * 		output: Employee instance | null
		 * 		
		 */
		String[] tempIdPass = validIdPassGen();
		if(LogInControlObject.logIn(tempIdPass[0], tempIdPass[1]) != null){
			System.out.println("Testing LogInControlObject.logIn with valid id and valid password was passed.");
		
		}
		else{
		
			System.out.println("Testing LogInControlObject.logIn with valid id and valid password was failed.");
		}

		tempIdPass = validIdPassGen();
		if(LogInControlObject.logIn(tempIdPass[0], randomStringGen()) == null){

			System.out.println("Testing LogInControlObject.logIn with valid id and invalid password was passed.");
		
		}
		else{
			System.out.println("Testing LogInControlObject.logIn with valid id and invalid password was failed.");
		
		}
	
		tempIdPass = validIdPassGen();
		if(LogInControlObject.logIn(randomStringGen(), tempIdPass[1]) == null){
			System.out.println("Testing LogInControlObject.logIn with invalid id and valid password was passed.");
		}
		else{
			System.out.println("Testing LogInControlObject.logIn with invalid id and valid password was failed.");
		}


		if(LogInControlObject.logIn(randomStringGen(), randomStringGen()) == null){
			System.out.println("Testing LogInControlObject.logIn with invalid id and invalid password was passed.");
		
		}
		else{
			System.out.println("Testing LogInControlObject.logIn with invalid id and invalid password was failed.");
		
		}
	}
}
