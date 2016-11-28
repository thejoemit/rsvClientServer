/**
 * @title 		rsvserver
 * @author 		Joseph Mitchell (100806582)
 * @contact 	the.joe.mit@gmail.com
 * @date		11/8/2016 @ 3:16 AM
 * @license		CREATIVE COMMONS Attribution-NonCommercial-ShareAlike 4.0 International
 * @description A remote method invocation server which is built 
 * 				around passenger manager functions for an airliner.
 */ 
import java.rmi.RemoteException; //rmi remote exceptions
import java.rmi.registry.LocateRegistry; //for get registry
import java.rmi.registry.Registry; //for rmi registry
import java.rmi.server.UnicastRemoteObject; // for rmi rebind
import java.util.ArrayList; // for array lists
import java.util.List; // for list functions .size()
@SuppressWarnings("serial")// for eclipse warning
public class rsvserver extends UnicastRemoteObject implements ServerInterface {
	public static int passengers = 30; // passenger maximum
	public static List<String> Passengers = new ArrayList<String>(); //Passegner List
	public static List<Integer> Seats = new ArrayList<Integer>(); //Seat List
	public static List<Boolean> Avalibility = new ArrayList<Boolean>(); //Avalibility List
	rsvserver() throws RemoteException { }; //self class constructor
	public List<Integer> AList() throws RemoteException { //availability distributions list
		List<Integer> AL = new ArrayList<Integer>(); // allocate new integer list space
		int bctl = 3, bctr = 2, ectf = 10, ects = 10, ectl = 5; // default seat/price mappings
		for (int c = 0; c<passengers; c++) {
			if(c<6&&c>0) { // if you are in the business class
				if(!Avalibility.get(c)){ // and the seat is not available
					if(bctl>0){ // if the cheap seats are not sold
						bctl--; // remove one cheap seat
					} else { //the cheap seats are sold
						bctr--;	//remove one reg seat
					}
				}
			} else { //if you are in the economy class
				if(!Avalibility.get(c)){ // and the seat is not available
					if(ectf>0){ // if the cheap seats are not sold
						ectf--; // remove one cheap seat
					} else if (ects>0){ // else if the medium seats are not sold
						ects--; // remove one medium seat	
					} else { // else if the medium and cheap seats are sold
						ectl--; // remove one regular seat
					}
				}
			}
		}
		AL.add(bctl); //stack business cheap seat on AL (0)
		AL.add(bctr); //stack business regular seat on AL (1)
		AL.add(ectf); //stack economy cheap seat on AL (2)
		AL.add(ects); //stack economy medium seat on AL (3)
		AL.add(ectl); //stack economy regular seat on AL (4)
		return AL; //return the integer array
	}
	public List<Integer> ECAList() throws RemoteException {
		List<Integer> ECA = new ArrayList<Integer>(); // new int list
		for (int c = 6; c<passengers; c++) { //for all economy class
			if(Avalibility.get(c)){ // if seat is available
				ECA.add(c); //stack the seat number on ECA
			}
		}
		return ECA; //return new int array
	}
	@Override
	public List<Integer> BCAList() throws RemoteException {
		List<Integer> BCA = new ArrayList<Integer>(); // new int list
		for (int c = 1; c<=5; c++) { //for all business class
			if(Avalibility.get(c)){ // if seat is available
				BCA.add(c); //stack the seat number on BCA
			}
		}
		return BCA;  //return new int array
	}
	@Override
	public List<String> PNList() throws RemoteException {
		List<String> PNL = new ArrayList<String>(); // new string list
		for (int c = 1; c<passengers; c++) { //for all business class
			if(!"na".equals(Passengers.get(c))){ //if passenger name is not na
				PNL.add(Passengers.get(c)); //stack the name on PNL
			}
		}
		return PNL; //return  PNL
	}
	@Override
	public List<Integer> PSList() throws RemoteException {
		List<Integer> PSL = new ArrayList<Integer>(); // new string list
		for (int c = 1; c<passengers; c++) { // for all passengers
			if(!"na".equals(Passengers.get(c))){ // while the paggengers name is not na
				PSL.add(Seats.get(c-1)); // stack thet seat id on PSL
			}
		}
		return PSL; // return PSL
	}
	@Override
	public List<String> PCList() throws RemoteException {
		List<String> PCL = new ArrayList<String>(); // new string list
		for (int c = 1; c<passengers; c++) { // for all passengers
			if(!"na".equals(Passengers.get(c))){ //if passenger name is not na
				if(Seats.get(c)<6&&Seats.get(c)>0) //if the seat is in business class
					PCL.add("business"); // stack business on the parallel class array
				else 
					PCL.add("economy"); // stack economy on the parallel class array
			}
		}
		return PCL; //return PCL
	}
	@Override
	public int PMax() throws RemoteException {
		return passengers; //return the integer value for passengers
	}
	@Override
	public boolean reservation(String passengerclass, String name, int seatnum) throws RemoteException {
		if(Avalibility.get(seatnum)&&"na".equals(Passengers.get(seatnum))){ //if the seat is avalible and the name is na
			Passengers.set(seatnum,name); // set the passenger name for a given seat number 
			Avalibility.set(seatnum, false); //set the availability of the given seat to false
			return true; //return true that reservation was made
		}
		return false; //else reurn false if booking failed
	}
	public static void main(String [] args) {
		if (args.length!=1) { // if arguments length does not equal one
			System.err.println("Usage: rsvserver <rmi-url>"); // print proper usage
			System.exit(-1); // exit on error
		}
		try {  
			Registry registry = LocateRegistry.createRegistry(1024); //define reg with socket 
			String name = args[0]; // the server name is the first argument
			//create new instance
			rsvserver server = new rsvserver(); // define a new server instance
			registry.rebind(name, server); // register name with server
			System.out.println("Started rsvserver, registered as " + name); //server started message
			for (int c=1; c<=passengers; c++) { // for every seat on the plane
				Seats.add(c); //stack an incrementing id
				Avalibility.add(true); //stack that they are avalible to start
				Passengers.add("na"); //stack the default name na
				System.out.println("Added Seat: " + c); //successfully added seat message
			}
		}
	    catch(Exception ex) { // catch on execption
	    	System.out.println("Caught exception while registering: " + ex);
	    	System.exit(-1); //exit on error
	    }
	}
}
