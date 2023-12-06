import java.io.IOException;

public class Node {
	private String name; // node name
	private double lat; //latitude coordinate
	private double lon; //longitude coordinate
	
	// constructors
	public Node() {
		this.name = "";
		this.lat = 0;
		this.lon = 0;
	}
	
	public Node(String name, double lat, double lon) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}
	
	
	// setters
	public void setName(String name) {
		this.name = name;
	}
	public void setLat(double lat) {
		this.lat=lat;
	}
	public void setLon(double lon) {
		this.lon=lon;
	}
	
	
	// getters
	public String getName () {
		return this.name;
	}
	public double getLat () {
		return this.lat;
	}
	public double getLon () {
		return this.lon;
	}
	
	public void userEdit() throws IOException { // get user info and edit node
		String newName=Pro3_zhanjasm.getString("   Name: ");
		//Pro3_zhanjasm.cin.readLine();
		setName(newName);
		
		double newLat = Pro3_zhanjasm.getDouble("   latitude: ", -90, 90);// lat bounds: -90,90
		setLat(newLat);
		
		double newLon = Pro3_zhanjasm.getDouble("   longitude: ", -180, 180);//lon bounds: -90,90
		setLon(newLon);
		
	}
	public void print() { // print node info as a table row
		String alignedNodeName = String.format("%19s", this.name);//just printing name and coordinates	
		String Coord = String.format("(" + this.lat + "," + this.lon +")");
		System.out.format("%s%19s\n", alignedNodeName,Coord); //alinging name to right
	}
	
	public static double distance(Node i, Node j) { // calc distance between 2 nodes
		double R=6371;//radius of earth	
		//double degToRad=(Math.PI)/180;
		double lati = Math.toRadians(i.getLat());// values given in degrees, need to convert to radians
		double loni = Math.toRadians(i.getLon());
		
		double latj = Math.toRadians(j.getLat());
		double lonj = Math.toRadians(j.getLon());
		
		double changeX= latj - lati; // change in lon 
		double changeY= lonj - loni;
		
		double a = Math.pow(Math.sin(changeX/2),2) + 
				((Math.cos(lati) * Math.cos(latj) * Math.pow(Math.sin(changeY/2),2) ));
		double b = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double cost= R*b; //cij in project instructions
		
		return cost;	
	}
}
