package ie.gmit.sw;

public class Compare {
	private String s = null, t = null, algo = null;
	private Resultator r;
	
	public Compare() {

	}

	public Compare(String s, String t, String algo, Resultator r) throws Exception {
		super();
		this.s = s;
		this.t = t;
		this.algo = algo;
		this.r = r;
		compareStrings();
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

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public Resultator getR() {
		return r;
	}

	public void setR(Resultator r) {
		this.r = r;
	}

	public void compareStrings() throws Exception{
		
		Facade f = new Facade();
		int x;
		
		switch(algo){
		case "Levenshtein Distance":
			x = f.levenshtein(s, t);
			//System.out.println("Levenshtein Distance: " + x);
			r.setResult("Levenshtein Distance: " + x);
			r.setProcessed();
			break;
			
		case "Damerau-Levenshtein Distance":
			x = f.Damerau(s, t);
			//System.out.println("Levenshtein Distance: " + x);
			r.setResult("Damerau-Levenshtein Distance: " + x);
			r.setProcessed();
			break;
		}
	}



}
