package sourceCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;

public class WhitespaceCorrection {
	String FilePath;
	RedBlackBST<String, Integer> Dictionary;
	
	public WhitespaceCorrection(String iFilePath, RedBlackBST<String, Integer> iDictionary)
	{
		FilePath = iFilePath;
		Dictionary = iDictionary;
	}
	
	public void CorrectWhiteSpaces() throws IOException
	//This function supports only the insertion of one whitespace character. E.g. "Nightfallto" will be split into "nightfall to" and not "night fall to"
	{
		File file = new File(FilePath);
		String[] test = FilePath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
		String FileName = test[test.length-1];
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sts;
        HashMap<String, Integer> PossibleSplits = new HashMap<String, Integer>();
        
        while ((sts = br.readLine()) != null) 
        {
        	String[] splitLine = sts.split(" ");
        	for(String word : splitLine)
        	{
        		int wLength = word.length();
        		if(!Dictionary.contains(word))
    			{
        			for(int i = wLength/2; i < wLength - 1; i++)//Due to low chances of having a single letter word at the end, case will be skipped.
        			{
        				String leftSS = word.substring(0, i);
        				String rightSS = word.substring(i);
        				
//        				boolean leftHit = Dictionary
//        				boolean rightHit =;
        				
        				
        			}
    			}
        	}
        }
        br.close();
	}
	

	public String AnalyseWord(String iWord)
	{
		return "";
	}
}
