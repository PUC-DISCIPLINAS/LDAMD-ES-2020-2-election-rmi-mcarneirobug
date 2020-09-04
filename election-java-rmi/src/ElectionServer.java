import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServer {

	public static void main(String[] args) {
		
		try {
			Election election = new ElectionServant();
			Election stub = (Election) UnicastRemoteObject.exportObject(election, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Election", stub);
			System.out.println("Election server running...");
		} catch(Exception e) {
			System.out.println("Error: Election server: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
