package ie.gmit.sw;

public class Facade {

	private LevenshteinDistance lev;
	private DamerauLevenshteinDistance dam;
	
	public Facade() {
		lev = new LevenshteinDistance();
		dam = new DamerauLevenshteinDistance();
	}
	
	public int levenshtein(String s, String t) {
		int result = lev.computeLevenshteinDistance(s, t);
		System.out.println("Result should equal " + result);
		return result;
	}
	
	public int Damerau(String s, String t){
		return dam.getDHSimilarity(s,t);
	}
	
}
