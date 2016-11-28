/**
 * @title 		rsvclient
 * @author 		Joseph Mitchell
 * @contact 		the(dot)joe(dot)mit[at]gmail[dot]com
 * @date		11/8/2016 @ 2:38 AM
 * @license		CREATIVE COMMONS Attribution-NonCommercial-ShareAlike 4.0 International
 * @description A remote method invocation client connected to
 * 				a passenger manager RMI server interface.
 */ 
import java.rmi.registry.LocateRegistry; //for get registry
import java.rmi.registry.Registry;	//for rmi registry
import java.util.ArrayList;	// for array lists
import java.util.List; // for list functions .size()
public class rsvclient {
	public static void main(String [] args) {
		if (args.length>5 || args.length <2) { //you need atleast 2 and no more than five args
			System.err.println("Usage: rsvclient <command> <rmi-url>"); // default usage
			System.exit(-1); //exit on error
		}
		String serverName = args[1]; // return nameserver from the args
		ServerInterface server = null;    //new ServerInterface  
		try {
			// set the local registry to bind to localhost port 1024
			Registry registry = LocateRegistry.getRegistry("localhost", 1024);
			// the server is nammed what it is specified as
			server = (ServerInterface) registry.lookup(serverName);
		} catch (Exception e) {
			// catch on the server socket lookup
			System.out.println("Caught an exception doing name lookup on "+serverName+": "+e);
			System.exit(-1); //exit on error
		}
		if("list".equals(args[0])){ // if the list command is passed
			try {	// get sales list - remote method invocation!
				List<Integer> als = new ArrayList<Integer>();//new int array list for AList
				als = server.AList(); // grab the ALint structure
				System.out.println("Business Class:");
				System.out.println(als.get(0)+" seats at $500 each"); 	//cheap business
				System.out.println(als.get(1)+" seats at $800 each"); 	//reg. business
				System.out.println("Seat numbers: "+server.BCAList()); 	//business available array
				System.out.println("Economy Class:");
				System.out.println(als.get(2)+" seats at $200 each"); 	//cheap economy
				System.out.println(als.get(3)+" seats at $300 each");	//med economy
				System.out.println(als.get(4)+" seats at $450 each");	//reg economy
				System.out.println("Seat numbers: "+server.ECAList());	//economy available array
			} catch (Exception e) { //catch on AList, BCAList, ECAList lookups.
				System.out.println("Exception caught while fetching tickets: "+e);
				System.exit(-1); // exit on error
			}
		}
		if("passengerlist".equals(args[0])){  // if the passengerlist command is passed
			try { // get passenger list - remote method invocation!
				List<Integer> psl = new ArrayList<Integer>(); //new int array for PassSeatList
				List<String> pnl = new ArrayList<String>(); //new array for PassNameList
				List<String> pcl = new ArrayList<String>(); //new array for PassClassList
				psl = server.PSList(); //populate int array for PassSeatList
				pnl = server.PNList(); //populate array for PassNameList
				pcl = server.PCList(); //populate array for PassClassList
				if(pnl.size()==pcl.size()&&pnl.size()==psl.size()){ //the array length should match
					for (int c = 0; c<pnl.size(); c++) { //for each item in the array
						System.out.println(pnl.get(c)+" "+pcl.get(c)+" "+ psl.get(c)); //concatnate
					}
				} else { // not the same size... theres an issue...
					System.out.println("The List sizes are not the name. try again");
					System.exit(-1); //exit on error
				}
			} catch (Exception e) { // catch when fetching PNList, PSLint, or PCList
				System.out.println("Exception caught while fetching passegers: "+e);
				System.exit(-1); // exit on error
			}
		}
		if("reserve".equals(args[0])){ // if the reserve command is passed
			try { 	// send reservation - remote method invocation!
				if (Integer.parseInt(args[4]) >= server.PMax() || Integer.parseInt(args[4]) < 1) {
					System.out.println("Failed to reserve: invalid seat number");
					System.exit(-1); //if seat is not between 1 to pMax the exit on error
				} // next send reservation request and receive response
				boolean returned = server.reservation(args[2], args[3], Integer.parseInt(args[4]));
				if(returned){ //if the boolean is true then
					System.out.println("Successfully reserved seat "+ Integer.parseInt(args[4])+ " for passenger "+args[3]);
				} else {  // if the boolean is flase
					System.out.println("Failed to reserve: seat not available");
				}
			} catch (Exception e) { // catch exceptions to reservation request
				System.out.println("Exception caught while reserving: "+e);
				System.exit(-1); //exit on eroor
			}
		}
		
	}
}
