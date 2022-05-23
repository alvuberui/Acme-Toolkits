package acme.features.spam;

import acme.entities.systemConfiguration.SystemConfiguration;

public class SpamDetector {
	
	  private SpamDetector() {
	    throw new IllegalStateException("Utility class");
	  }
	  private static Integer findWordCRecursive(final int i, final int j, final String[] targetWords, final String[] words) {
		  if(i == targetWords.length) {
			  return 1;
		  }
		  if(j == words.length) {
			  return 0;
		  }
		  if(targetWords[i].trim().equalsIgnoreCase(words[j].trim())) {
			 return SpamDetector.findWordCRecursive(i+1, j+1, targetWords, words);
		  }
		 return 1;
	  }
	
	  private static Integer findWordsRecursive(int i, int j, final String[] targetWords, final String[] words) {
		 
		  if(i >= targetWords.length) {
			  j++;
			  i=0;
		  }
		  if(j >= words.length) {
			  return 0;
		  }

		  if(targetWords[i].contains(" ") && targetWords[i].split(" ")[0].equalsIgnoreCase(words[j])) {
			 return SpamDetector.findWordCRecursive(1, j+1, targetWords[i].split(" "), words) + SpamDetector.findWordsRecursive(i+1, j, targetWords, words);
		  }else if(targetWords[i].trim().equalsIgnoreCase(words[j].trim())) {
			  return 1 + SpamDetector.findWordsRecursive(i+1, j, targetWords, words);
		  }
		  return SpamDetector.findWordsRecursive(i+1, j, targetWords, words);
	  }
	 
	  
	  private static boolean spamTerms(final String words, final String terms, final Double Threshold) {
		  final String[] wordsSplit = terms.split(",");
		  final String[] inputWordsSplits = words.split("[ ,.:;/()]");
		  final int k = SpamDetector.findWordsRecursive(0, 0, wordsSplit, inputWordsSplits);
		  final double porcentageWeakTerms = (double) (k*100)/wordsSplit.length;
		  if(porcentageWeakTerms >= Threshold)
			  return false;
		  return true;
		}
	  public static boolean spamWeakTerms(final String words, final SystemConfiguration systemConfiguration) {
		 
		  return SpamDetector.spamTerms(words, systemConfiguration.getWeakTerms(), systemConfiguration.getWeakThreshold());
		}
	  
	  public static boolean spamStrongTerms(final String words, final SystemConfiguration systemConfiguration) {
		  return SpamDetector.spamTerms(words, systemConfiguration.getStrongTerms(), systemConfiguration.getStrongThreshold());
		} 

	  public static boolean error(final String words, final SystemConfiguration systemConfiguration) {
		  return SpamDetector.spamTerms(words.trim(), systemConfiguration.getAllStrongTerms(), systemConfiguration.getStrongThreshold()) 
				  && SpamDetector.spamTerms(words.trim(), systemConfiguration.getAllWeakTerms(), systemConfiguration.getWeakThreshold());
		} 
}