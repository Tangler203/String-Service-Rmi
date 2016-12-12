package ie.gmit.sw;

public class Job {

	private String algo;
    private String s;
    private String t;
    private long jobNumber;
    
    
	public Job(String s, String t, String algo, long jobNumber) {
		super();
		this.algo = algo;
		this.s = s;
		this.t = t;
		this.jobNumber = jobNumber;
	}


	public String getAlgo() {
		return algo;
	}


	public void setAlgo(String algo) {
		this.algo = algo;
	}


	public String getS() {
		return s;
	}


	public void setS(String s) {
		this.s = s;
	}


	public String getT() {
		return t;
	}


	public void setT(String t) {
		this.t = t;
	}


	public long getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(long jobNumber) {
		this.jobNumber = jobNumber;
	}
	
    
}
