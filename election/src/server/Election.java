package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Election extends Remote {
	
	Boolean vote(String candidateName, String hashVoter) throws RemoteException;
	
	int result(String candidateName, int voteAmount) throws RemoteException;
	
	Vector<String> candidates() throws RemoteException;

}
