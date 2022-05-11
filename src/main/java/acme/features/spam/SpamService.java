package acme.features.spam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {

	@Autowired
	protected SpamRepository repository;

	
	public boolean spamWeakTerms(final String words) {
		final List<String> weakWords = new ArrayList<String>();

		final String weakWordsByLenguaje[] = this.repository.findSystemConfuration().getWeakTerms().split(",");
		for(int p = 0; p < weakWordsByLenguaje.length; p++) {
			weakWords.add(weakWordsByLenguaje[p]);
		}
		boolean res;
		final String wordsSplits[] = words.split("[ ,.:;/()]");


		
		//-----------------------------------------------------------------------------
		int weakTearms = 0;
		for(int i= 0; i < weakWords.size();i++) {
			for(int j = 0; j < wordsSplits.length; j++) {
				if(weakWords.get(i).contains(" ")) {
					final String wordsWithSpace[] = weakWords.get(i).split(" ");
					for(int l = 0; l < wordsWithSpace.length; l++) {
						if(j+l < wordsSplits.length) {
							if(wordsWithSpace[l].toLowerCase().equals(wordsSplits[j+l].toLowerCase())) {
								if(l == wordsWithSpace.length-1) {
									weakTearms++;
								}
							} else {
								l = wordsWithSpace.length -1;
							}
						} 
					}
				} else if(weakWords.get(i).toLowerCase().equals(wordsSplits[j].toLowerCase())){
						weakTearms++;
				
				}
				
			}

		}
		final double porcentageWeakTerms = (weakTearms*100)/weakWords.size();
		if(porcentageWeakTerms >= this.repository.findSystemConfuration().getWeakThreshold()) {
			res = false;
		} else {
			res = true;
		}
		
		return res;
	}
	
	public boolean spamStrongTerms(final String words) {
		final List<String> strongWords = new ArrayList<String>();

		final String strongWordsByLenguaje[] = this.repository.findSystemConfuration().getStrongTerms().split(",");
		for(int m = 0; m < strongWordsByLenguaje.length; m++) {
			strongWords.add(strongWordsByLenguaje[m]);
		}
		
		boolean res;
		final String wordsSplits[] = words.split("[ ,.:;/()]");

		
		//--------------------------------------------
		int strongTearms = 0;
		for(int i= 0; i < strongWords.size();i++) {
			for(int j = 0; j < wordsSplits.length; j++) {
				if(strongWords.get(i).contains(" ")) {
					final String wordsWithSpace[] = strongWords.get(i).split(" ");
					for(int l = 0; l < wordsWithSpace.length; l++) {
						if(j+l < wordsSplits.length) {
							if(wordsWithSpace[l].toLowerCase().equals(wordsSplits[j+l].toLowerCase())) {
								if(l == wordsWithSpace.length-1) {
									strongTearms++;
								}
							} else {
								l = wordsWithSpace.length -1;
							}
						} 
					}
				} else if(strongWords.get(i).toLowerCase().equals(wordsSplits[j].toLowerCase())){
					strongTearms++;
				
				}
				
			}

		}
		final double porcentageStrongTerms = (strongTearms*100)/strongWords.size();
		if(porcentageStrongTerms >= this.repository.findSystemConfuration().getStrongThreshold()) {
			res = false;
		} else {
			res = true;
		}
		return res;
	}
	
}
