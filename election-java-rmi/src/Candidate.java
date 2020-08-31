import java.io.Serializable;

public class Candidate implements Serializable {

	private static final long serialVersionUID = 1L;
	private String candidateName;
	private Integer candidateVotes;
	
	public Candidate(String candidateName, Integer candidateVotes) {
		this.candidateName = candidateName;
		this.candidateVotes = candidateVotes;
	}

	public String getCandidateName() {
		return this.candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public Integer getCandidateVotes() {
		return this.candidateVotes;
	}
	
	public void setCandidateVotes(Integer candidateVotes) {
		this.candidateVotes = candidateVotes;
	}

	@Override
	public String toString() {
		return "Candidate [candidateName=" + candidateName + ", candidateVotes=" + candidateVotes + "]";
	}	

}
