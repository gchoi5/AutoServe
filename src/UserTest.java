import java.util.*;
import java.math.BigInteger;
import java.security.SecureRandom;


public class UserTest{
	public static String randomStringGen(){
		return new BigInteger(130, new SecureRandom()).toString(32);
	
	}
	public static int validTabNumGen(){
	
		return (new Random().nextInt(20) + 1);
	}
	public static int invalidTabNumGen(){
		Random rand = new Random();
		
		int invalidTabNum = rand.nextInt();
		while(true){
			if(invalidTabNum < 1 || 20 < invalidTabNum){
				return invalidTabNum;	
			}
		}
	}
	public static void main(String[] args){
		/**
		 * Customer
		 */
		/**
		 * Customer.complain
		 *     input: 	int tabNum
		 *     			String content
		 */

		Customer customer = new Customer();

		if(customer.complain(validTabNumGen(),randomStringGen()) == true){
			System.out.println("Testing Customer.complain with valid table number and not null string was passed.");
		
		}
		else{
			System.out.println("Testing Customer.complain with valid table number and not null string was failed.");
		
		}

		if(customer.complain(validTabNumGen(), new String()) == false){
			System.out.println("Testing Customer.complain with valid table number and null string was passed.");
		
		}
		else{
			System.out.println("Testing Customer.complain with valid table number and null string was failed.");
		
		}

		if(customer.complain(invalidTabNumGen(), randomStringGen()) == false){
		
			System.out.println("Testing Customer.complain with invalid table number and not null string was passed.");
		}
		else{
			System.out.println("Testing Customer.complain with invalid table number and not null string was failed.");
		}

		if(customer.complain(invalidTabNumGen(), new String()) == false){
			System.out.println("Testing Customer.complain with invalid table number and null string was passed.");
		
		}
		else{
			System.out.println("Testing Customer.complain with invalid table number and null string was failed.");
		
		}











	
		/**
		 * Waiter
		 */
		/**
		 * Waiter.public void billTable(Table table)
		 *     input: Table
		 */
		HallStatus.init();	//All tables' status are set empty
							//All tables' 
		
		MenuTable.loadMenuList();

		LinkedList<Integer> servedTabNum = new LinkedList<Integer>();
		LinkedList<Integer> occupiedTabNum = new LinkedList<Integer>();
		LinkedList<Integer> emptyTabNum = new LinkedList<Integer>();

		Random rand = new Random();
		int orderedMenuItemIdx = 1;

		for(int tempTabNum = 1; tempTabNum <= 20; tempTabNum++){
			int flag = new Random().nextInt(3);
			if(flag == 0){
				HallStatus.getTable(tempTabNum).setStatus("served");

				while(true){
					int tempMenuIdx = rand.nextInt(5) + 1;
					HallStatus.getTable(tempTabNum).addToServedOrderList(
							new OrderedMenuItem(	orderedMenuItemIdx++,
													MenuTable.getMenuItem(tempMenuIdx), 
													tempTabNum));

					if(new Random().nextInt(5) == 0){
						break;
					}
				}
				servedTabNum.add(new Integer(tempTabNum));
			}
			else if(flag == 1){
				HallStatus.getTable(tempTabNum).setStatus("occupied");

				while(true){
					int tempMenuIdx = rand.nextInt(5) + 1;
					HallStatus.getTable(tempTabNum).addToSelectedOrderList(
							new OrderedMenuItem(	orderedMenuItemIdx++,
													MenuTable.getMenuItem(tempMenuIdx), 
													tempTabNum));

					if(new Random().nextInt(5) == 0){
						break;
					}
				}
				occupiedTabNum.add(new Integer(tempTabNum));
			}
			else{
				emptyTabNum.add(new Integer(tempTabNum));
			}
			
		}

		//HallStatus.printTableInfoAll();

		Waiter waiter = new Waiter();

		Collections.shuffle(servedTabNum);
		waiter.billTable(HallStatus.getTable(servedTabNum.getFirst()));
		if(	HallStatus.getTable(servedTabNum.getFirst()).getStatus().equals("empty") &&
			HallStatus.getTable(servedTabNum.getFirst()).getServedOrderList().isEmpty()){
			System.out.println("Testing Waiter.billTable with served table number was passed");
		}
		else{
			System.out.println("Testing Waiter.billTable with served table number was failed");
			
		}

		Collections.shuffle(occupiedTabNum);
		if(waiter.billTable(HallStatus.getTable(occupiedTabNum.getFirst())) == false){
			System.out.println("Testing Waiter.billTable with occupied table number was passed");
		}
		else{
			System.out.println("Testing Waiter.billTable with occupied table number was failed");
		}

		Collections.shuffle(emptyTabNum);
		if(waiter.billTable(HallStatus.getTable(emptyTabNum.getFirst())) == false){
			System.out.println("Testing Waiter.billTable with empty table number was passed");
		
		}
		else{
			System.out.println("Testing Waiter.billTable with empty table number was failed");
		
		}

		if(waiter.billTable(HallStatus.getTable(invalidTabNumGen())) == false){

			System.out.println("Testing Waiter.billTable with invalid table number was passed");
		}
		else{
			System.out.println("Testing Waiter.billTable with invalid table number was failed");
		
		}
	}
}
