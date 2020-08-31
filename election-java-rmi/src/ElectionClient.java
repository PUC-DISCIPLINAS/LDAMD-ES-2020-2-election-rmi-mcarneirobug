import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ElectionClient {

	private static Election election = null;
	private static Map<String, String> voters = new HashMap<String, String>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (connectServer()) {
			showMenu();
		}
	}

	private static boolean connectServer() {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			election = (Election) registry.lookup("Election");
			JOptionPane.showConfirmDialog(null, "Connected server", "Success", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			return true;
		} catch (RemoteException r) {
			r.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		JOptionPane.showConfirmDialog(null, "Could not connect to the server.", "Erro", JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private static void showMenu() {

		Integer option = -1;

		do {
			System.out.println("");
			System.out.println("1 - List candidates");
			System.out.println("2 - Voting");
			System.out.println("3 - Result");
			System.out.println("4 - Exit");

			System.out.print("\nEnter your option: ");
			switch (option = sc.nextInt()) {
			case 1:
				listCandidates();
				break;
			case 2:
				vote();
				break;
			case 3:
				result();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
			}
			System.out.println();
		} while (option != 0);
	}

	private static Vector<String> listCandidates() {
		try {
			Vector<String> candidateName = election.candidates();
			for (String name : candidateName) {
				JOptionPane.showConfirmDialog(null, "Candidate: " + name + "!", "Success", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				System.out.println("\nCandidate: " + name);
			}
		} catch (RemoteException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private static String hashVoter(String voter) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(voter.getBytes());
			byte[] md5 = md.digest();
			BigInteger numMd5 = new BigInteger(1, md5);
			String hashMd5 = String.format("%022x", numMd5);
			return hashMd5;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static void vote() {
		String voterName = JOptionPane.showInputDialog("Enter your name: ");
		System.out.println();
		if (voters.containsKey(voterName)) {
			JOptionPane.showConfirmDialog(null, "You are connected again!", "Connected", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		} else {
			voters.put(voterName, hashVoter(voterName));
			JOptionPane.showConfirmDialog(null,
					"Welcome " + voterName + " your code authentication is: " + voters.get(voterName), "Connected",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		}
		System.out.println("\nAll candidates: ");
		listCandidates();
		String candidateName = JOptionPane.showInputDialog("Choose your candidate: ");
		VoteThread vt = new VoteThread(candidateName, voters.get(voterName));
		vt.start();
	}

	private static String resultToString(String candidateName, Integer candidateVotes) {
		String result = ("\nCandidate: " + candidateName + " have " + candidateVotes + " vote(s).");
		return result;
	}

	private static void result() {
		try {
			Vector<String> candidates = election.candidates();
			for (String candidate : candidates) {
				Candidate c = election.result(candidate);
				JOptionPane.showConfirmDialog(null, resultToString(c.getCandidateName(), c.getCandidateVotes()),
						"Success", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				System.out.println(resultToString(c.getCandidateName(), c.getCandidateVotes()));
			}
		} catch (RemoteException r) {
			System.err.println("Error: " + r.getMessage());
			r.printStackTrace();
		}
	}

}
