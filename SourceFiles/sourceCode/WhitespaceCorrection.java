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

public class WhitespaceCorrection {
	String FilePath;
	RedBlackBST<String, Integer> Dictionary;
	HashMap<String, Integer> TwoLetterWords;
	
	public WhitespaceCorrection(String iFilePath, RedBlackBST<String, Integer> iDictionary)
	{
		FilePath = iFilePath;
		Dictionary = iDictionary;
		TwoLetterWords = new HashMap<String, Integer>();
		TwoLetterWords.put("to", 6);
		TwoLetterWords.put("as", 6);
		TwoLetterWords.put("is", 6);
		TwoLetterWords.put("he", 6);
		TwoLetterWords.put("do", 6);
		TwoLetterWords.put("go", 6);
		TwoLetterWords.put("if", 6);
		TwoLetterWords.put("of", 6);
		TwoLetterWords.put("so", 6);
		TwoLetterWords.put("it", 6);
		TwoLetterWords.put("or", 6);
		TwoLetterWords.put("me", 6);
		TwoLetterWords.put("my", 6);
		TwoLetterWords.put("in", 6);
		TwoLetterWords.put("i", 6);
		TwoLetterWords.put("a", 6);
	}
	
	public void CorrectWhiteSpaces() throws IOException
	//This function supports only the insertion of one whitespace character. E.g. "Nightfallto" will be split into "nightfall to" and not "night fall to"
	{
		File file = new File(FilePath);
		String[] test = FilePath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
		String FileName = test[test.length-1];
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sts;
        String[] CorrectedWords = new String[2];
        
        PrintWriter outSuggestions = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\corrected_words_detected" + FileName));
        PrintWriter outCorrectedText = new PrintWriter(new FileWriter(Paths.get("").toAbsolutePath().toString() + "\\OutputFiles\\corrected_text" + FileName));
        
        while ((sts = br.readLine()) != null) 
        {
        	String[] splitLine = sts.split(" ");
        	for(String word : splitLine)
        	{
        		word = word.replaceAll("[\\.|,|;|'|\"]", "");
        		if(!Dictionary.contains(word))
    			{
        			CorrectedWords = AnalyseWord(word);
        			
        			if(CorrectedWords[0] != "")
        			{
        				if (CorrectedWords[1] != "") 
        				{
        					outSuggestions.println(word +"," + CorrectedWords[0] + " " + CorrectedWords[1]);
        					outCorrectedText.append(CorrectedWords[0] + " " + CorrectedWords[1] + " ");
        				}
        				else outCorrectedText.append(CorrectedWords[0] + " ");
        			}
        			else outCorrectedText.append(word + " ");
    			}
        		else outCorrectedText.append(word + " ");
        	}
        }
        br.close();
        outCorrectedText.close();
        outSuggestions.close();
	}
	

	public String[] AnalyseWord(String iWord)
	{
		int wLength = iWord.length();
		String wWord = iWord.toLowerCase();
        HashMap<String, Integer> PossibleSplits = new HashMap<String, Integer>();
		String[] wOutput = {"",""};

		
		int midSplit = (int)Math.round(wLength/2.0);
		int j = midSplit;
		for(int i = midSplit; i < wLength; i++)//Due to low chances of having a single letter word at the end, case will be skipped.
		{
			String leftSS, rightSS;
			boolean leftHit, rightHit;

			leftSS = wWord.substring(0, i);
			rightSS = wWord.substring(i);
			
			leftHit = Dictionary.contains(leftSS);
			rightHit = Dictionary.contains(rightSS);
			
			if(leftHit && rightHit)
			{
				//BINGO -- Best Solution
				wOutput[0] = leftSS;
				wOutput[1] = rightSS;
				return wOutput;
			}
			else if (leftHit && i < wLength - 1)
			{
				PossibleSplits.put(leftSS, i);
			}
			else if (rightHit && i < wLength - 1) 
			{
				PossibleSplits.put(rightSS, i);
			}
			
			if(j>1) //Due to low chances of having a single letter word at the end, case will be skipped.
			{
				--j;
				leftSS = wWord.substring(0, j);
				rightSS = wWord.substring(j);
				
				leftHit = Dictionary.contains(leftSS);
				rightHit = Dictionary.contains(rightSS);
				
				if(leftHit && rightHit)
				{
					//BINGO -- Best Case
					wOutput[0] = leftSS;
					wOutput[1] = rightSS;
					return wOutput;
				}
				else if (leftHit && j > 1)
				{
					PossibleSplits.put(leftSS, j);
				}
				else if (rightHit && j > 1) 
				{
					PossibleSplits.put(rightSS, j);
				}
				}
		}
		if(!PossibleSplits.isEmpty()) wOutput = PickBestSplit(PossibleSplits, wWord);
		return wOutput;
	}
	
	public String[] PickBestSplit(HashMap<String, Integer> iOptions, String iWord)
	{
		String[] wOutput = new String[2];
		int MaxWeight = 0;
		int tempWeight, tempSplit;
		int SplitIndex = iWord.length();
		String SS1, SS2;
		boolean SS1Hit;
		//Weight will be equal to length, with the exception of 2 letter words having a weight of 3 times their size. 
		//This is due to the frequency of these words in the English language. e.g. To, Is , As, He...
		
		for( Map.Entry<String, Integer> entry : iOptions.entrySet())
		{
			tempSplit = entry.getValue();
			SS1 = iWord.substring(0, tempSplit);
			SS2 = iWord.substring(tempSplit);
			SS1Hit = (Dictionary.contains(SS1));
			if(SS1Hit) 
				{
					tempWeight = SS1.length() - 2;
					if (TwoLetterWords.containsKey(SS1) && (!(SS2.length() < 6) || TwoLetterWords.containsKey(SS2))) 
						{
							tempWeight +=6;
						}
					if (SS2.length() < 4 && !TwoLetterWords.containsKey(SS2)) 
						{
							tempWeight -=6;
						}
				}
			else 
				{
					tempWeight = SS2.length() - 2;
					if (TwoLetterWords.containsKey(SS2) && !((SS1.length() < 6) || TwoLetterWords.containsKey(SS1))) 
						{
							tempWeight +=6;
						}
					if (SS1.length() < 4 && !TwoLetterWords.containsKey(SS1)) 
						{
							tempWeight -=6;
						}
				}
			System.out.println(iWord + ": " + SS1 + "/" + SS2 + " || SS1Hit: " + SS1Hit + " tempWeight: " + tempWeight);
			
			if(tempWeight > MaxWeight) 
				{
					MaxWeight = tempWeight;
					SplitIndex = tempSplit;
				}
		}
		
		wOutput[0] = iWord.substring(0, SplitIndex);
		wOutput[1] = iWord.substring(SplitIndex);
		
		if(MaxWeight <= 0 || wOutput[0].lastIndexOf('-') == (wOutput[0].length() - 1) || wOutput[1].indexOf('-') == 0) //Probably bad split, just skip it
		{
			wOutput[0] = iWord;
			wOutput[1] = "";
		}
		
		return wOutput;
	}
}
