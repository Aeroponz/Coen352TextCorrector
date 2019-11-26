import java.nio.file.Paths;

import sourceCode.RedBlackBST;
import sourceCode.WordFrequency;
import sourceCode.WhitespaceCorrection;
import sourceCode.LevenshteinDistance;
import sourceCode.MutatedCharCorrection;

import java.nio.file.Path;
import java.io.*;

   class Main
   { 
       public static void main(String args[]) throws Exception 
       { 

            Path currentRelativePath = Paths.get("");
            final String os = System.getProperty("os.name");
            String path = "";
            int FilesToCorrect = 20;

            if(os.contains("Windows")){
                path = currentRelativePath.toAbsolutePath().toString();
            }
            else {
                path = currentRelativePath.toAbsolutePath().toString() + "/InputFiles/dictionary.txt";
            }
            System.out.println("Using dictionary found at: " + path + "\\InputFiles\\dictionary.txt");

            RedBlackBST<String, Integer> dictionary = new RedBlackBST<String, Integer>();
            File file = new File(path + "\\InputFiles\\dictionary.txt"); 
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String sts;
            while ((sts = br.readLine()) != null) 
            {
            	String[] splitDict = sts.split(" ");
            	for(String word : splitDict)
            	{
            		dictionary.put(word.toLowerCase(), word.length());
            	}
            }
            br.close();
            System.out.println("Found " + dictionary.size() + " words.");
            System.out.println("BST is healthy: " + dictionary.check());
            System.out.println("");
            
            //PART A
            for(int i = 0; i < FilesToCorrect; i++)
            {
            	System.out.println("\nStarting Word Frequency Analysis ( " + (i+1) + "/" + FilesToCorrect + " )");
            	System.out.println("Start Time: " + System.currentTimeMillis());
            	long start = System.currentTimeMillis();
            	WordFrequency File1 = new WordFrequency(path + "\\InputFiles\\CleanText\\" + i + ".txt", dictionary);
            	File1.AnalyseFile();
            	long finish = System.currentTimeMillis();
            	System.out.println("Word Frequency Analysis Completed.\nEnd Time: " + finish + "\nRuntime: " + (finish - start));
            }
            
            //PART B
            for(int i = 0; i < FilesToCorrect; i++)
            {
            	System.out.println("\nStarting Whitespace Correction ( " + (i+1) + "/" + FilesToCorrect + " )");
            	System.out.println("Start Time: " + System.currentTimeMillis());
            	long start = System.currentTimeMillis();
            	WhitespaceCorrection File1 = new WhitespaceCorrection(path + "\\InputFiles\\RemovedSpaces\\" + i + ".txt", dictionary);
            	File1.CorrectWhiteSpaces();
            	long finish = System.currentTimeMillis();
            	System.out.println("Whitespace Correction Completed.\nEnd Time: " + finish + "\nRuntime: " + (finish - start));
            	System.out.println("\nStarting Word Frequency Analysis ( " + (i+1) + "/" + FilesToCorrect + " )");
            	System.out.println("Start Time: " + System.currentTimeMillis());
            	start = System.currentTimeMillis();
            	WordFrequency File2 = new WordFrequency(path + "\\OutputFiles\\corrected_text" + i + ".txt", dictionary);
            	File2.AnalyseFile();
            	finish = System.currentTimeMillis();
            	System.out.println("Word Frequency Analysis Completed.\nEnd Time: " + finish + "\nRuntime: " + (finish - start));
            }
            
            
            dictionary.visitNode("en%ering");
       } 
   } 