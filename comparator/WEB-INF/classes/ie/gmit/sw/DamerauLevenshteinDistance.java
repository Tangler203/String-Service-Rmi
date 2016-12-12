package ie.gmit.sw;

public class DamerauLevenshteinDistance {
	

    private int[][] matrix;
    
    public int getDHSimilarity(String s, String t)
	{
	        int INF = s.length() + t.length();
	 
	        matrix = new int[s.length()+1][t.length()+1];
	 
	        for (int i = 0; i < s.length(); i++)
	        {
	            matrix[i+1][1] = i;
	            matrix[i+1][0] = INF;
	        }
	 
	        for (int i = 0; i < t.length(); i++)
	        {
	            matrix[1][i+1] = i;
	            matrix[0][i+1] = INF;
	        }
	 
	        int[] DA = new int[24];
	 
	        for (int i = 0; i < 24; i++)
	        {
	            DA[i] = 0;
	        }
	 
	        for (int i = 1; i < s.length(); i++)
	        {
	            int db = 0;
	 
	            for (int j = 1; j < t.length(); j++)
	            {
	 
	                int i1 = DA[t.indexOf(t.charAt(j-1))];
	                int j1 = db;
	                int d = ((s.charAt(i-1)==t.charAt(j-1))?0:1);
	                if (d == 0) db = j;
	 
	                matrix[i+1][j+1] = Math.min(Math.min(matrix[i][j]+d, matrix[i+1][j]+1),Math.min(matrix[i][j+1]+1,matrix[i1][j1]+(i - i1-1)+1+(j-j1-1)));
	            }
	            DA[s.indexOf(s.charAt(i-1))] = i;
	        }
	         
	        return matrix[s.length()][t.length()];
	}

}
