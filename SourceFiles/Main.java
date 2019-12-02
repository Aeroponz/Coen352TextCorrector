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
            
            //Check Dictionary
//            dictionary.BSTIterator();
//            while(dictionary.hasNext())
//            {
//            	System.out.println(dictionary.next());
//            }
            
            //PART A
            for(int i = 0; i < FilesToCorrect; i++)
            {
            	System.out.println("\nStarting Word Frequency Analysis ( " + (i+1) + "/" + FilesToCorrect + " )");
            	long start = System.nanoTime();
            	WordFrequency File1 = new WordFrequency(path + "\\InputFiles\\CleanText\\" + i + ".txt", dictionary);
            	File1.AnalyseFile();
            	long finish = System.nanoTime();
            	System.out.println("Word Frequency Analysis Completed.\nRuntime: " + (finish - start) + " ns");
            }
            
            //PART B
            for(int i = 0; i < FilesToCorrect; i++)
            {
            	System.out.println("\nStarting Whitespace Correction ( " + (i+1) + "/" + FilesToCorrect + " )");
            	long start = System.nanoTime();
            	WhitespaceCorrection File1 = new WhitespaceCorrection(path + "\\InputFiles\\RemovedSpaces\\" + i + ".txt", dictionary);
            	File1.CorrectWhiteSpaces();
            	long finish = System.nanoTime();
            	System.out.println("Whitespace Correction Completed.\nRuntime: " + (finish - start) + " ns");
            	System.out.println("\nStarting Word Frequency Analysis ( " + (i+1) + "/" + FilesToCorrect + " )");
            	start = System.nanoTime();
            	WordFrequency File2 = new WordFrequency(path + "\\OutputFiles\\corrected_text" + i + ".txt", dictionary);
            	File2.AnalyseFile();
            	finish = System.nanoTime();
            	System.out.println("Word Frequency Analysis Completed.\nRuntime: " + (finish - start) + " ns");
            }
            
          //PART C
            for(int i = 0; i < FilesToCorrect; i++)
            {
            	System.out.println("\nStarting Mutated Character Correction ( " + (i+1) + "/" + FilesToCorrect + " )");
            	long start = System.nanoTime();
            	MutatedCharCorrection File1 = new MutatedCharCorrection(path + "\\InputFiles\\MutatedChars\\" + i + ".txt", dictionary);
            	File1.CorrectMutatedChars();
            	long finish = System.nanoTime();
            	System.out.println("Mutated Character Correction Completed.\nRuntime: " + (finish - start) + " ns");
            	System.out.println("\nStarting Word Frequency Analysis ( " + (i+1) + "/" + FilesToCorrect + " )");
            	start = System.nanoTime();
            	WordFrequency File2 = new WordFrequency(path + "\\OutputFiles\\mutatedcorrected_text" + i + ".txt", dictionary);
            	File2.AnalyseFile();
            	finish = System.nanoTime();
            	System.out.println("Word Frequency Analysis Completed.\nRuntime: " + (finish - start) + " ns");
            }
       } 
   } 