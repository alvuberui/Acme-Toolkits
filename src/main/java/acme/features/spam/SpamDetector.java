package acme.features.spam;
import java.util.List;

import acme.entities.systemConfiguration.SystemConfiguration;

public class SpamDetector {
	
	  private SpamDetector() {
	    throw new IllegalStateException("Utility class");
	  }
	  private static Integer findWordCRecursive(int i, int j, String[] targetWords, String[] words) {
		  if(i == targetWords.length) {
			  return 1;
		  }
		  if(j == words.length) {
			  return 0;
		  }
		  if(targetWords[i].equalsIgnoreCase(words[j])) {
			 return findWordCRecursive(i+1, j+1, targetWords, words);
		  }
		 return 1;
	  }
	
	  private static Integer findWordsRecursive(int i, int j, String[] targetWords, String[] words) {
		  if(i >= targetWords.length) {
			  j++;
			  i=0;
		  }
		  if(j >= words.length) {
			  return 0;
		  }

		  if(targetWords[i].contains(" ") && targetWords[i].split(" ")[0].equalsIgnoreCase(words[j])) {
			 return findWordCRecursive(1, j+1, targetWords[i].split(" "), words) + findWordsRecursive(i+1, j, targetWords, words);
		  }else if(targetWords[i].equalsIgnoreCase(words[j])) {
			  return 1 + findWordsRecursive(i+1, j, targetWords, words);
		  }
		  return findWordsRecursive(i+1, j, targetWords, words);
	  }
	 
	  
	  private static boolean spamTerms(final String words, final String terms, Double Threshold) {
		  final String weakWordsByLenguaje[] = terms.split(",");
		  final String wordsSplits[] = words.split("[ ,.:;/()]");
		  int k = findWordsRecursive(0, 0, weakWordsByLenguaje, wordsSplits);
		  final double porcentageWeakTerms = (k*100)/weakWordsByLenguaje.length;
		  if(porcentageWeakTerms >= Threshold)
			  return false;
		  return true;
		}
	  public static boolean spamWeakTerms(final String words, SystemConfiguration systemConfiguration) {
		  return spamTerms(words, systemConfiguration.getWeakTerms(), systemConfiguration.getWeakThreshold());
		}
	  
	  public static boolean spamStrongTerms(final String words, SystemConfiguration systemConfiguration) {
		  return spamTerms(words, systemConfiguration.getStrongTerms(), systemConfiguration.getStrongThreshold());
		} 
}