import java.io.*;
public class Pro3_zhanjasm {
	
	static BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
	
	//start main
	public static void main(String[] args) throws IOException {
		boolean running=true;
		Graph g = new Graph();
		
		while(running) {
			
			displayMenu();
			String commandInput = getString("Enter choice: ");
			//System.out.print("\n");
			
			if ("Q".equals(commandInput)==true || ("q".equals(commandInput)==true)){ // quit
				System.out.print("\nCiao!\n");
				running = false;
				break;
			}
			
			if ("G".equals(commandInput)==true || ("g".equals(commandInput)==true)){	// creat graph
				System.out.print("\n");
				g.reset();// need to reset graph to create new one
				boolean graphExists = createGraph(g);
				
			}
			
			else if ("C".equals(commandInput)==true || ("c".equals(commandInput)==true)){// edit city
				System.out.print("\n");
				if (g.getN() == 0) {
					System.out.print("ERROR: No graph has been loaded!\n");
				} else {
					editCities(g);
				}
				System.out.print("\n");
			}
			
			else if ("A".equals(commandInput)==true || ("a".equals(commandInput)==true)){ // add/remove arcs
				System.out.print("\n");
				if (g.getN() == 0) {
					System.out.println("ERROR: No graph has been loaded!\n");
				} else {
					//System.out.print("\n");
					boolean editing=true;
					
					while(editing) { // loop that shows arc menu
						g.printArcs();
						displayArcMenu();
						String input = getString("Enter choice: ");
						System.out.print("\n");
						
						if ("A".equals(input)==true || ("a".equals(input)==true)) {
							addArcs(g);
						}
						else if ("R".equals(input)==true || ("r".equals(input)==true)) {
							removeArc(g);
						}
						else if ("Q".equals(input)==true || ("q".equals(input)==true)) {
							editing = false;
							//System.out.print("\n");
						}
						else {
							System.out.println("ERROR: Invalid menu choice!\n");
						}
					}
				}
			}
			
			else if ("D".equals(commandInput)==true || ("d".equals(commandInput)==true)){ // display everything
				if (g.getN() == 0) {
					System.out.print("\n");
					System.out.print("ERROR: No graph has been loaded!\n");
					System.out.print("\n");
				} else {
					g.print();
					
				}
				
			}
			
			else if ("R".equals(commandInput)==true || ("r".equals(commandInput)==true)){ // resets graph
				System.out.print("\n");
				if (g.getN() == 0) {
					System.out.println("ERROR: No graph has been loaded!\n");
				} else {
					g.reset();
				}
			}
			else if ("P".equals(commandInput)==true || ("p".equals(commandInput)==true)){ // checks a given path
				System.out.print("\n");
				if (g.getN() == 0) {
					System.out.print("ERROR: No graph has been loaded!\n");
				} else {
					tryPath(g);
			
				}
				System.out.print("\n");
			}
			else {
				System.out.println("\nERROR: Invalid menu choice!\n");
			}
		}
	}
	
	//end main
	
	
	//below are functions
	
	public static void displayMenu() {// displays menu
		// reading menu input is done in getInteger function
		System.out.println("   JAVA TRAVELING SALESMAN PROBLEM V1\n"
				+ "G - Create graph\n"
				+ "C - Edit cities\n"
				+ "A - Edit arcs\n"
				+ "D - Display graph info\n"
				+ "R - Reset graph\n"
				+ "P - Enter salesman's path\n"
				+ "Q - Quit\n");
	}
	
	public static void displayArcMenu() {
		System.out.println("EDIT ARCS\n"
				+ "A - Add arc\n"
				+ "R - Remove arc\n"
				+ "Q - Quit\n");
	}
	
	public static boolean createGraph(Graph G) throws IOException {
		// adding cities
		G.setN(getInteger("Enter number of cities (0 to quit): "));
		System.out.print("\n");
		G.init(G.getN());
		
		if (G.getN() !=0) {
		
			for(int i=0; i<G.getN(); i++) {//index starts at 0, city at index 0 is city 1
				boolean added = false;
				
				while (!added) {// adding one node per iteration
					System.out.format("City %d:\n", i+1);
					String name = getString("   Name: ");
					double lati = getDouble("   latitude: ", -90, 90);
					double longi = getDouble("   longitude: ", -180, 180);
					Node newNode = new Node(name, lati, longi);
					added = G.addNode(newNode);
				}
				System.out.print("\n");
			}
			
			//add arcs
			System.out.println("Now add arcs:\n");
			G.addArcFull();
			return true;
		}
		return false;
	}
	
	
	public static void editCities(Graph G) throws IOException {
		G.printNodes();
		int entered = getInteger("Enter city to edit (0 to quit): ", 0, G.getN());
		if (entered == 0) {
			System.out.print("\n");
		} else {
			G.editNode(G.getNode(entered-1), (entered-1));//entered-1 adjusts to correct index
		}	
		
	}
	
	public static void addArcs(Graph G) throws IOException {
		G.addArcFull();
	}
	public static void removeArc(Graph G) throws IOException {
		G.printArcs();
		boolean removing =true;
		while (removing) {
			int entered = getInteger("Enter arc to remove (0 to quit): ", 0, G.getM());
			System.out.print("\n");
			if (entered==0) {
				removing = false;
				break;
			}
			else {
				G.removeArc(entered); // calls remove arc function
				System.out.print("\n");
				System.out.print("\n");
				G.printArcs();
			}
		}
	}

	public static void tryPath(Graph G) throws IOException {
		G.printNodes();
		
		int plannedPath[]=new int[G.getN()+1 ];// need space for n+1 cities
		System.out.format("Enter the %d cities in the route in order: \n", G.getN()+1);
		
		for (int i=1; i<=G.getN()+1; i++) {
			plannedPath[i-1]= getInteger(String.format("City %d: ", i), 1, G.getN());
		}
		System.out.print("\n");
		
		if (G.checkPath(plannedPath)==true) {// if code gets here, path is valid
			double pathcost = G.pathCost(plannedPath);
			//System.out.print("\n");
			System.out.format("The total distance traveled is %.2f km.\n", pathcost);
		}
	}
	
	public static int getInteger(String prompt) throws IOException {
		boolean valid=false;
		int choice=0;
		
		while (!valid){
			System.out.print(prompt);
			try {
				choice = Integer.parseInt(cin.readLine());
				if (choice<0) {
					System.out.format("ERROR: Input must be an integer in [%d, infinity]!\n\n", 0);
				} else {// if here, actual number was entered so can be set to valid if in bounds
					valid=true;
				}
			}catch (NumberFormatException e) {// gets here only if input was letter, other character etc.
				System.out.format("ERROR: Input must be an integer in [%d, infinity]!\n", 0);
				System.out.print("\n");
			}
			}
		return choice;	
	}
	
	public static int getInteger(String prompt, int LB, int UB) throws IOException { //for when integer bounds are known
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
			
			boolean valid = false;
			double numEntered= 0;
					
			do {
				System.out.print(prompt);
				try {// tests for positive number, then checks if actually a number, or else might print error message twice for one input
					numEntered = Double.parseDouble(cin.readLine());
					if (numEntered<LB || numEntered>UB) {
						System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n", LB, UB);
						System.out.print("\n");
					}
					else {
						valid=true;
					}
				} catch (NumberFormatException e) {
					System.out.format("ERROR: Input must be a real number in [%.2f, %.2f]!\n", LB, UB);
					System.out.print("\n");
				}
			} while (!valid);
			return numEntered;
		}
	
	public static String getString(String prompt) throws IOException{ //takes any input and stores as string
		System.out.print(prompt);
		String Input = cin.readLine();
		return Input;
	}

}
