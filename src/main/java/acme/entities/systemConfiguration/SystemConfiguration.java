package acme.entities.systemConfiguration;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String currency;
	
	@NotBlank
	private String currencies;
	
	@NotBlank
	private String language;
	
	@NotBlank
	private String weakTerms;
	
	@NotBlank
	private String strongTerms;
	
	@NotNull
	@Range(min = 0, max = 100)
	@Digits(fraction = 2, integer = 2)
	private Double weakThreshold;
	
	@NotNull
	@Range(min = 0, max = 100)
	@Digits(fraction = 2, integer = 2)
	private Double strongThreshold;
	
	private HashMap<String, String> getMapLenguajes(final String terms) {
		final HashMap<String, String> m = new HashMap<String, String>();
		final String[] lenguages = terms.split(";");
		for (int i = 0; i < lenguages.length; i++) {
			final String[] parseLenguage = lenguages[i].split(":");
			final String parseTerms = parseLenguage[1];
			final String selectedLenguage = parseLenguage[0];
			m.put(selectedLenguage, parseTerms);
		}
		return m;
	}
	
	public String getWeakTerms() {
		return this.getMapLenguajes(this.weakTerms).get(this.language.trim());
	}
	
	public String getStrongTerms() {
		return this.getMapLenguajes(this.strongTerms).get(this.language.trim());
	}
	public void setStrongTerms(final String strongTerms) {
		this.strongTerms = strongTerms;
	}
	
	public void setWeakTerms(final String weakTerms) {
		this.weakTerms = weakTerms;
	}
	
	
	
}
