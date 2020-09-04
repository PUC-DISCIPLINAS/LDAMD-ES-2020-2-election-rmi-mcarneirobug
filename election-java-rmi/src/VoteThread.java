import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class VoteThread extends Thread {

	private String candidateName;
	private String hashVoter;

	public VoteThread(String candidateName, String hashVoter) {
		this.candidateName = candidateName;
		this.hashVoter = hashVoter;
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < 5; i++) { // retry 5 times in case of failure
				try {
					Registry registry = LocateRegistry.getRegistry("localhost");
					Election election = (Election) registry.lookup("Election");

					boolean isSuccess = election.vote(candidateName, hashVoter);
					if (isSuccess) {
						JOptionPane.showConfirmDialog(null, "Registered vote for candidate: " + candidateName + "!",
								"Sucesso", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showConfirmDialog(null, "This cliend already has a registered vote..", "Erro",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					break;
				} catch (RemoteException | NotBoundException e) {
					try {
						sleep(5000);
					} catch (InterruptedException in) {
						System.out.println("Error: " + in.getMessage());
						in.printStackTrace();
					}
				}
			}
		}
	}

}
