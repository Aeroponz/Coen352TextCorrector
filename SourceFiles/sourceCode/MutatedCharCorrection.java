package sourceCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;

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
		CommonWords.put("to", 6);
		CommonWords.put("as", 6);
		CommonWords.put("is", 6);
		CommonWords.put("he", 6);
		CommonWords.put("do", 6);
		CommonWords.put("go", 6);
		CommonWords.put("if", 6);
		CommonWords.put("of", 6);
		CommonWords.put("so", 6);
		CommonWords.put("it", 6);
		CommonWords.put("or", 6);
		CommonWords.put("me", 6);
		CommonWords.put("my", 6);
		CommonWords.put("in", 6);
		CommonWords.put("on", 6);
		CommonWords.put("i", 4);
		CommonWords.put("i", 4);
		CommonWords.put("the", 8);
		CommonWords.put("for", 8);
	}
	
	public void CorrectMutatedChars() throws IOException
	//This function only support one mutated char per word
	{
		File file = new File(FilePath);
		String[] test = FilePath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
		String FileName = test[test.length-1];
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sts;
        String CorrectedWord = "";
        
        PrintWriter outSuggestions = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\mutated_words_detected" + FileName));
        PrintWriter outCorrectedText = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\mutatedcorrected_text" + FileName));
        
        while ((sts = br.readLine()) != null) 
        {
    		sts = sts.replaceAll("--", " ");
    		sts = sts.replaceAll("[\\.|; |: |, |\" ]", " ");
        	String[] splitLine = sts.split(" ");
        	for(String word : splitLine)
        	{
        		String wWord = word.toLowerCase();
        		if(!Dictionary.contains(word) && !Dictionary.contains(wWord) && !CommonWords.containsKey(wWord))
    			{
        			Dictionary.BSTIterator();
        			while(Dictionary.hasNext())
        			{
        				String wKey = Dictionary.next();
        				if(wKey.length() == wWord.length() && wKey.length() > 1)
        		    		{
        		    			if(LevenshteinDistance.getLevenshteinDistance(wKey, wWord) == 1)
        		    			{
        		    				CorrectedWord = wKey;
        		    				break;
        		    			}
        		    		}
        			}
        			//Debug
        			//System.out.println(wWord + "/" + CorrectedWord);
        			
        			if(CorrectedWord != "")
        			{
        				
        				outSuggestions.println(word +", " + CorrectedWord);
        				outCorrectedText.append(CorrectedWord + " ");
        			}
        			else outCorrectedText.append(word + " ");
    			}
        		else outCorrectedText.append(word + " ");
        		CorrectedWord = "";
        	}
        }
        br.close();
        outCorrectedText.close();
        outSuggestions.close();

	}	
}
