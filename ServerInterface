/**
 * @title 		ServerInterface
 * @author 		Joseph Mitchell (100806582)
 * @contact 	the.joe.mit@gmail.com
 * @date		11/8/2016 @ 3:16 AM
 * @license		CREATIVE COMMONS Attribution-NonCommercial-ShareAlike 4.0 International
 * @description A remote method invocation interface which details 
 * 				a passenger manager for an airliner.
 */ 
import java.rmi.Remote; //rmi remote interface
import java.rmi.RemoteException; //rmi remote exceptions
import java.util.List; //lists
public interface ServerInterface extends Remote { //the interface extends the remote rmi package
	public List<Integer> BCAList() throws RemoteException; //business available array
	public List<Integer> ECAList() throws RemoteException; //economy available array
	public List<Integer> AList() throws RemoteException; //availability distributions
	public List<String> PNList() throws RemoteException; //Passenger Name List
	public List<Integer> PSList() throws RemoteException; //Passenger Seat List
	public List<String> PCList() throws RemoteException; //Passenger Class List
	public int PMax() throws RemoteException;  //Passenger Maximum
	public boolean reservation(String passengerclass, String name, int seatnum) throws RemoteException;
} // ^ makes a reservation 
