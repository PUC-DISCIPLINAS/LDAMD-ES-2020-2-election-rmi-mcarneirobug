import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ElectionServant implements Election {

	private Map<String, Integer> candidates;
	private Map<String, Boolean> voters;

	public ElectionServant() throws RemoteException {
		candidates = new HashMap<String, Integer>();
		voters = new HashMap<String, Boolean>();

		candidates.put("Matheus", 0);
		candidates.put("Raissa", 0);
		candidates.put("Vitor", 0);
	}

	@Override
	public Vector<String> candidates() throws RemoteException {
		Vector<String> candidateName = new Vector<String>();
		for (String c : candidates.keySet()) {
			candidateName.add(c);
		}
		return candidateName;
	}

	@Override
	public synchronized Boolean vote(String candidateName, String hashVoter) throws RemoteException {
		String hashMD5 = hashVoter(hashVoter);
		if (voters.containsKey(hashMD5)) {
			return false;
		} else if (!candidates.containsKey(candidateName)) {
			System.err.println("We didn't find this candidate.");
		} else {
			candidates.put(candidateName, candidates.get(candidateName) + 1);
		}
		voters.put(hashMD5, true);
		return true;
	}

	@Override
	public synchronized Candidate result(String candidate) throws RemoteException {
		if (!candidates.containsKey(candidate)) {
			System.err.println("We didn't find this candidate.");
			return null;
		} else {
			Candidate c = new Candidate(candidate, candidates.get(candidate));
			return c;
		}
	}

	public String hashVoter(String voter) {
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

}
