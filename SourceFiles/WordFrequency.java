import java.io.BufferedReader;
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
	
	public String AnalyseFile() throws Exception
	{
		File file = new File(FilePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sts;
        RedBlackBST<String, Integer> FoundWords = new RedBlackBST<String, Integer>();
        
        while ((sts = br.readLine()) != null) 
        {
        	String[] splitLine = sts.split(" ");
        	for(String word : splitLine)
        	{
        		if(Dictionary.contains(word))
    			{
    			 	if(!FoundWords.contains(word)) FoundWords.put(word, 0);
    			 	//else FoundWords.SetValue(word, FoundWords.get(word) + 1);
        			System.out.println(word + " found!");
    			}
        	}
        }
        br.close();
        
		return PrintOutputFile(FoundWords);
	}
	public String PrintOutputFile(RedBlackBST<String, Integer> Hits) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\frequencies.txt"));
		out.print("Hello ");
		out.println("world");
		out.close();
		return "";
	}
}
