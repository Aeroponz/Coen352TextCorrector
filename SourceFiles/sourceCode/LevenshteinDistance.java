package sourceCode;
//https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm
public class LevenshteinDistance {
	
	public int[][] LevenshteinDist(String a, String b)
	{
		int ALength = a.length();
		int BLength = b.length();
		int[][] d = new int[ALength][BLength];
		
		for(int i = 1; i <= ALength; i++)
		{
			d[i][0]=i;
		}
		for(int j = 1; j <= BLength; j++)
		{
			d[0][j]=j;
		}
		
		return d;
	}

}
