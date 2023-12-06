import java.io.*;
public class Pro2_zhanjasm {
	
	static BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
	static final double _IN_TO_CM_=2.54;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		boolean running=true;
		
		while (running) {
			int command = 0;
			
			displayMenu();
			
			command = getInteger("Enter choice: ", 0, 2); // reading menu input is done in getInteger function
			System.out.print("\n");
			
			
			if (command==0) {// checks for exit program and breaks to just stop
				running = false;
				System.out.print("Goodbye!");
				break;
			}
			
			if (command==1) {// feet to cm
				feetInchesToCm();
			}
			
			if (command==2) {
				cmToFeetInches();// cm to feet, executes the function
			}		
		}		
	}
	
	public static void displayMenu() {// displays menu
		// reading menu input is done in getInteger function
		System.out.println("   JAVA CONVERSION PROGRAM\n"
				+ "0 - Quit\n"
				+ "1 - Convert feet and inches to centimeters\n"
				+ "2 - Convert centimeters to feet and inches\n");
		
		//System.out.print("Enter choice: ");
		//System.out.print("\n");
	}
	
	public static void feetInchesToCm() throws IOException {
		
		//double _IN_TO_CM_=2.54;
		
		double feet=-1, inchesOG=-1, cm=0;
		boolean calculated=false;
		//System.out.print("Enter feet: ");
		
		
		while (!calculated) {// keeps looping while calculation has not occurred, ensures all entries are valid numbers
			
			feet=getDouble("Enter feet: ", 0, Double.MAX_VALUE);
			inchesOG=getDouble("Enter inches: ", 0, Double.MAX_VALUE);
			
			// if gets here then passed getDouble functions and therefore valid numbers
			System.out.print("\n");
			double inchesNew=inchesOG + (feet*12);
			cm=inchesNew * _IN_TO_CM_;
			System.out.format("%.2f feet %.2f inches equals %.2f centimeters.\n\n", feet, inchesOG, cm);
			calculated=true;
			}
	}
	
	public static void cmToFeetInches() throws IOException {
		
		double inches1=0, cmOG=-1, inches2=0;
		int feet=0;
		
		cmOG=getDouble("Enter centimeters: ", 0, Double.MAX_VALUE + 1);	
		
		inches1=cmOG/_IN_TO_CM_;// total inches from cm entered
		
		feet=(int) (inches1/12);//gets the number of feet, (int) rounds down
		inches2=inches1-(feet*12);// inches2 is remainder of inches after feet has been found, print this value out
		
		System.out.format("\n%.2f centimeters equals %.2f feet %.2f inches.\n\n", cmOG, (double) feet, inches2);
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
					System.out.format("ERROR: Input must be a real number in [%.2f, infinity]!\n", LB);
					System.out.print("\n");
				}
			} catch (NumberFormatException e) {
				System.out.format("ERROR: Input must be a real number in [%.2f, infinity]!\n", LB);
				System.out.print("\n");
			}
			
		} while (numEntered<LB || numEntered>UB);
		
		return numEntered;
		
	}
	
	
}
	
