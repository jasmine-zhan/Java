import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class getValidInput {
	static BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
	//private char[] menuChoices = new char[] {'G', 'g', 'C', 'c', 'A', 'a', 'D', 'd', 'R', 'r', 'P', 'p', 'Q', 'q'}; //all valid menu inputs
	//private boolean valid=false;
	
	public static char Character(String prompt, char[] charArray) throws IOException {
		boolean valid=false;
		char command='0';
		
		while (!valid) {
			System.out.print(prompt);
			command = (char)cin.read();
				
			for (int i=0; i<14; i++) {
				if (command == charArray[i]) {
					valid = true;
					break;
				}
			}
			if (valid == false) {// if it gets here the command did not match anything in array
				System.out.println("ERROR: Invalid menu choice!");
			}
		}
		
		return command;
	}
	
	public static int getInteger(String prompt, int LB, int UB) throws IOException {
			boolean valid=false;
			int choice=0;
			
			while (!valid){
				System.out.print(prompt);
			
				try {
					choice = Integer.parseInt(cin.readLine());
					
					if (choice< LB || choice > UB) {
						System.out.format("ERROR: Input must be an integer in [%d, %d]!\n\n", LB, UB);
					} else {// if here, actual number was entered so can be set to valid if in bounds
						valid=true;
					}
				}catch (NumberFormatException e) {// gets here only if input was letter, other character etc.
					System.out.format("ERROR: Input must be an integer in [%d, %d]!\n", LB, UB);
					System.out.print("\n");
				}
				}
			return choice;	
		}
	
	
	public static double getDouble(String prompt, double LB, double UB) throws IOException{
			
			boolean valid = true;
			double numEntered= -1;
					
			do {
				System.out.print(prompt);
				
				try {// tests for positive number, then checks if actually a number, or else might print error message twice for one input
					numEntered = Double.parseDouble(cin.readLine());
					if (numEntered<LB || numEntered>UB) {
						System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n", LB, UB);
						System.out.print("\n");
					}
				} catch (NumberFormatException e) {
					System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n", LB, UB);
					System.out.print("\n");
				}
			} while (numEntered<LB || numEntered>UB);
			return numEntered;
		}


}
