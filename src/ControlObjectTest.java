import java.util.Random;
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
	public static int tabNumGen(){
	
	}
	public static void main(String[] args){
		/**
		 * OrderControlObject
		 */
		/**
		 * sendOrderToWaiter
		 * 	input: (int tabNum, int menuItemIdx)
		 * 	output: 
		 */

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
