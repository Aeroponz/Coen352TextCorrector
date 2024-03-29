package sourceCode;
import java.io.BufferedReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;


public class WordFrequency
 {

	String FilePath;
	RedBlackBST<String, Integer> Dictionary;
	
	public WordFrequency(String iFilePath, RedBlackBST<String, Integer> iDictionary)
	{
		FilePath = iFilePath;
		Dictionary = iDictionary;
	}
	
	public void AnalyseFile() throws Exception
	{
		File file = new File(FilePath);
		String[] test = FilePath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
		String FileName = test[test.length-1];
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sts;
        HashMap<String, Integer> FoundWords = new HashMap<String, Integer>(); 
        
        while ((sts = br.readLine()) != null) 
        {
    		sts = sts.replaceAll("--", " ");
    		sts = sts.replaceAll("[\\.|,|;|:|'|_|-|\"]", " ");
        	String[] splitLine = sts.split(" ");
        	for(String word : splitLine)
        	{
        		String wWord = word.toLowerCase();
        		if(Dictionary.contains(wWord))
    			{
    			 	if(!FoundWords.containsKey(wWord)) FoundWords.put(wWord, 1);
    			 	else FoundWords.put(wWord, FoundWords.get(wWord) + 1);
    			}
        	}
        }
        br.close();
        PrintOutputFile(FoundWords, FileName);
	}
	public void PrintOutputFile(HashMap<String, Integer> Hits, String FileName) throws IOException
	{
		PrintWriter outFreq = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\frequencies" + FileName));
		PrintWriter outRepeated = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\repeated" + FileName));
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(Hits.entrySet()); 
		Collections.sort(list, new ValueThenKeyComparator<String, Integer>());
		for(Entry<String, Integer> pair : list)
		{
			for(int i = 0; i< pair.getValue(); i++)
			{
				outRepeated.print(pair.getKey() + " ");
			}
		}
		outRepeated.close();
		String HitsToString = list.toString().replaceAll(", ", "\n");
		HitsToString = (HitsToString.replaceAll("=", " ")).substring(1, HitsToString.length() - 1);
		outFreq.print(HitsToString);
		outFreq.close();
	}
	//Method for sorting the TreeMap based on values
	public class ValueThenKeyComparator<K extends Comparable<? super K>, V extends Comparable<? super V>> implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
			int cmp1 = a.getValue().compareTo(b.getValue());
			if (cmp1 != 0) 
			{
				return cmp1;
			} 
			else 
			{
				return a.getKey().compareTo(b.getKey());
			}
		}
	}
}

