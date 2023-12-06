import java.io.IOException;
import java.util.ArrayList;

public class Graph { // graph class that sores graph data
	private int n; //number of nodes
	private int m; // number of arcs
	private ArrayList <Node> node = new ArrayList<Node>(); //Array list (array of nodes)
	private boolean [][] A; //adjacency matrix
	private double [][] C; //cost matrix
	
	//constructors
	public Graph() {
		this.n=0;
		this.m=0;
		//ArrayList 
		this.init(this.n);
	}
	
	public Graph(int n) {
		this.n=n;
		this.m=0;
		//this.init(this.n);
	}
	
	//setters
	public void setN(int n) {
		this.n=n;
	}
	public void setM(int m) {
		this.m=m;
	}
	public void setArc(int i, int j, boolean b) {
		A[i][j]=b;
		A[j][i]=b;
	}
	public void setCost(int i, int j, double c) {
		C[i][j]=c;
		C[j][i]=c;
	}
	
	
	//getters
	public int getN () {
		return this.n;
	}
	public int getM () {
		return this.m;
	}
	public boolean getArc(int i, int j) {
		return A[i][j];
	}
	public double getCost(int i, int j) {
		return C[i][j];
	}
	public Node getNode (int i) {
		return node.get(i);
	}
	
	public void init(int n) {// initialize values and arrays
		node.ensureCapacity(n);
		C= new double [n][n];
		A=new boolean [n][n];
	}
	public void reset(){//reset the graph
		node.clear();
		this.n=0;
		this.m=0;
		C= new double [n][n];
		A= new boolean [n][n];
		
	}
	public boolean existsArc(int i, int j) {// check if arc exists (index is alr updated when add arc is called
		if (A[i][j]==true || A[j][i]==true) {// -1 since index starts at 0
			return true;
		}
		else { 
			return false;}
	}
	
	public boolean existsNode(Node t) {//checks if node exists
		for (int i=0; i<this.node.size(); i++) {
			if (this.node.get(i).getName().equals(t.getName())){
				System.out.print("ERROR: City name and/or coordinates already exist!\n\n");
				return true;
			}
			else if ((this.node.get(i).getLat() == t.getLat()) && (this.node.get(i).getLon() == t.getLon())) {
				System.out.print("ERROR: City name and/or coordinates already exist!\n\n");
				return true;
			}
		}
		return false;
	}
	
	public boolean addArc(int i, int j) { //add an arc, return T/F success
		
			
		if (existsArc(i-1,j-1)==true) {
			System.out.println("ERROR: Arc already exists!");
			return false;
		}
		if (i==j) {
			System.out.println("ERROR: A city cannot be linked to itself!");
			return false;
		}
		else {
			A[i-1][j-1]=true;//need to adjust for array indexes
			A[j-1][i-1]=true;
			m++;
			
			double cost= Node.distance(node.get(i-1), node.get(j-1));
			
			C[i-1][j-1]=cost;
			C[j-1][i-1]=cost;
			
			System.out.format("Arc %d-%d added!\n", i, j);
			return true;
		}
		
	}
	
	public void addArcFull() throws IOException {
		this.printNodes();
		
		boolean adding =true;
		while (adding) {
			int arc1=0;
			int arc2=0;
			
			arc1=Pro3_zhanjasm.getInteger("Enter first city index (0 to quit): ", 0, this.n);
			if(arc1==0) {
				//System.out.print("\n");
				break;
			}
			arc2=Pro3_zhanjasm.getInteger("Enter second city index (0 to quit): ", 0, this.n);
			if(arc2==0) {
				
				break;
			}
			System.out.print("\n");
			addArc(arc1, arc2);// arc number entered by user is passed in
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void removeArc(int k) {// remove an arc
		
		int a = k; //creating a copy to print out original arc value in list
		
		for (int i=0; i<n; i++) {
			for (int j=i; j<n; j++) {
				if (A[i][j]==true) {
					k--;
				}
				if (k==0) {
					A[i][j]=false;
					A[j][i]=false;
					C[i][j]=0;
					this.m--;
					System.out.format("Arc %d removed!\n", a);
					return;
				}
			}
		}	
	}
	
	public boolean addNode(Node t) {// add a node
		if (this.existsNode(t)==false) {
			node.add(t);
			return true;
		}
		return false;
	}
	
	public void editNode(Node t, int index) throws IOException {
		t.userEdit();//get new node info
		for (int i=0; i<this.n; i++) {// update cost matrix (distances may have changed)
			if( A[index][i] == true) {// looping thru to find every arc that includes the updated city
				C[index][i]=t.distance(t, getNode(i));
				C[i][index]=t.distance(t, getNode(i));
			}
		}
	}
	
	public void print() {// print all graph info
		System.out.format("Number of nodes: %d\n", this.n);
		System.out.format("Number of arcs: %d\n", this.m);
		System.out.print("\n");
		printNodes();
		printArcs();
	}
	public void printNodes() {// print node list
		System.out.print("NODE LIST\n"
				+ "No.               Name        Coordinates\n"
				+ "-----------------------------------------\n");
		for(int i=1; i<= node.size(); i++) {
			System.out.format("%3d", i);//city 1 is index 0 of arraylist
			node.get(i-1).print();
		}
		System.out.print("\n");
	}
	public void printArcs() {// print arc list
		System.out.print("ARC LIST\n"
				+ "No.    Cities       Distance\n"
				+ "----------------------------\n");
		int count=0;
		for (int i=0; i<n; i++) {
			for (int j=i; j<n; j++) {
				if (A[i][j]==true) {
					count++;
					String arcsString = String.format("%d-%d", i+1, j+1);
					String costString = String.format("%.2f", C[i][j]);
					System.out.format("%3d %9s %14s\n", count, arcsString, costString); // turned everything into strings
				}
			}
		}
		System.out.print("\n");
	}
	
	public boolean checkPath(int[] P) {// check feasibility of path P
		//int current=0;
		boolean result =true;
		int length = P.length;//total number of items entered
		
		//checking first and last cities
				if (P[0] != P[length-1]) {
					System.out.print("ERROR: Start and end cities must be the same!\n");
					result =false;
					return result;
				}
		
		//checking if city is visited more than one (for every element, loops though the ones after to see if 
		for (int cur=0; cur<length-1; cur++) {
			for (int checking=cur+1; checking<length-1; checking++) {//checks from one after cur until second last since last city should be equal to first city
				if (P[cur] == P[checking]) {
					System.out.print("ERROR: Cities cannot be visited more than once!\n");
					//System.out.print("ERROR: Not all cities are visited!");
					result=false;
				}
			}
		}
		
		// checking if cities are visited more than once
		boolean visited[]=new boolean[n];//create array with same length as num nodes
		for (int j = 0; j<n; j++) {
			visited[j]=false;
		}
		for (int index=0; index<P.length; index++) {//if node is entered in path, spot in visited array becomes true
			visited[(P[index])-1]=true;
		}
		for (int i=0; i<n; i++) {
			if (visited[i]==false) {//if any spot in array visited is false, that means they did not visit
				result =false;
				System.out.print("ERROR: Not all cities are visited!\n");
				return result;
			}
		}
		
		
		//current is current city, current+1 is next city going to, checking is arc exists
		for (int current=0; current<length-1; current ++) {// does not have current as last city
			if (A [(P[current])-1] [(P[current+1])-1] == false) {
				System.out.format("ERROR: Arc %d-%d does not exist!\n", P[current], P[current+1]);
				result=false;
				return result;
			}
		}
		
		return result;
	}
	
	public double pathCost(int[] P) { // calculate cost of path P
		double totalCost=0;
		for (int current=0; current < (P.length)-1; current++) { // -1 since current wont be the last one
			totalCost=totalCost+(Node.distance(node.get(P[current]-1),node.get(P[current+1]-1)));
		}
		return totalCost;
	}	
	
}


