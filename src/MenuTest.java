import java.util.Random;

public class MenuTest{
	//A (valid test case): 1~5
	//B (invalid test): not 1~5
	public static int menuItemIdxGen(String cond){
		//Random rand = new Random(System.currentTimeMillis());
		Random rand = new Random();
		int tempMenuIdx = rand.nextInt();

		if(cond.equals("A")){
			tempMenuIdx = (tempMenuIdx % 5) + 1;
			return Math.abs(tempMenuIdx);
		}

		//else if(cond.equals("B")){
		while(1 <= tempMenuIdx && tempMenuIdx <= 5){
			tempMenuIdx = rand.nextInt();
		}

		return tempMenuIdx;
	}

	//A (valid test case): 1, 5
	//B (invalid test): 2, 3, 5
	public static int menuItemIdxGen2(String cond){
		int[] valid = {2,3,5};
	
		Random rand = new Random();
		int tempMenuIdx = rand.nextInt();

		if(cond.equals("A"))
			return valid[Math.abs(tempMenuIdx) % 3];

		while(tempMenuIdx == 2 && tempMenuIdx == 3 && tempMenuIdx == 5){
			tempMenuIdx = rand.nextInt();
		}

		return tempMenuIdx;
	}

	public static void main(String[] args){
		MenuTable.loadMenuList();
		/**
		 * public static MenuItem getMenuItem(int menuItemIdx)
		 */
		//getMenuItem with a valid index returns proper MenuItem which is not null
		if(MenuTable.getMenuItem(menuItemIdxGen("A")) != null){
			System.out.println("Testing MenuTable getMenuItem with invalid index was passed");
		}
		else{
			System.out.println("Testing MenuTable getMenuItem with invalid index was failed");
		}
		if(MenuTable.getMenuItem(menuItemIdxGen("B")) == null){
			System.out.println("Testing getMenuItem with invalid index was passed");
		}
		else{
			System.out.println("Testing MenuTable getMenuItem with invalid index was failed");
		
		}
		/**
		 * public static boolean checkMenuAvailability(MenuItem menuItem)
		 */
		//
		KitchenStatus.loadIngredientList();
		if(MenuTable.checkMenuAvailability(MenuTable.getMenuItem(menuItemIdxGen2("A"))) == true)

			System.out.println("Testing checkMenuAvailability with valid index was passed");
		else
			System.out.println("Testing checkMenuAvailability with valid index was failed");
		if(MenuTable.checkMenuAvailability(MenuTable.getMenuItem(menuItemIdxGen2("B"))) == false)
			System.out.println("Testing checkMenuAvailability with invalid index was passed");
		else
			System.out.println("Testing checkMenuAvailability with invalid index was failed");

		/**
		 * public static void updateMenuListAvailability()
		 * it will just call checkMenuAvailability for every MenuItem
		 * If testing checkMenuAvailability was passed, it must work fine.
		 *  
		 */
	}
}
