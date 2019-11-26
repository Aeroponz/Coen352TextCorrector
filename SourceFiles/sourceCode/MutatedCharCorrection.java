package sourceCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import sourceCode.LevenshteinDistance;

public class MutatedCharCorrection 
{

	String FilePath;
	RedBlackBST<String, Integer> Dictionary;
	HashMap<String, Integer> CommonWords;
	
	public MutatedCharCorrection(String iFilePath, RedBlackBST<String, Integer> iDictionary)
	{
		FilePath = iFilePath;
		Dictionary = iDictionary;
		CommonWords = new HashMap<String, Integer>();
	}
	
	public void CorrectMutatedChars() throws IOException
	//This function only support one mutated char per word
	{
//		File file = new File(FilePath);
//		String[] test = FilePath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
//		String FileName = test[test.length-1];
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String sts;
//        String CorrectedWord = "";
//        
//        PrintWriter outSuggestions = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\corrected_words_detected" + FileName));
//        PrintWriter outCorrectedText = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\corrected_text" + FileName));
//        
//        while ((sts = br.readLine()) != null) 
//        {
//    		sts = sts.replaceAll("--", " ");
//    		sts = sts.replaceAll("[\\.|,|;|:|'|_|-|\"]", " ");
//        	String[] splitLine = sts.split(" ");
//        	for(String word : splitLine)
//        	{
//        		String wWord = word.toLowerCase();
//        		if(!Dictionary.contains(word) && !Dictionary.contains(wWord))
//    			{
//        			CorrectedWord = AnalyseWord(wWord);
//        			
//        			if(CorrectedWord != "")
//        			{
//        				
//        				outSuggestions.println(word +", " + CorrectedWord);
//        				outCorrectedText.append(CorrectedWord + " ");
//        			}
//        			else outCorrectedText.append(word + " ");
//    			}
//        		else outCorrectedText.append(word + " ");
//        	}
//        }
//        br.close();
//        outCorrectedText.close();
//        outSuggestions.close();
		
		System.out.println(LevenshteinDistance.getLevenshteinDistance(" ", "a"));
	}
	

	public String AnalyseWord(String iWord)
	{
		String wOutput = "";
		char[] WordtoChar = iWord.toCharArray();
		RedBlackBST<String, Integer> wDict = Dictionary;
		//if(WordtoChar[0].)
	
		return wOutput;
	}
	
}
