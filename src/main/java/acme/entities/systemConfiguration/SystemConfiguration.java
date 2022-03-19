package acme.entities.systemConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

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
	private String lenguage;
	
	@NotBlank
	private String weakTerms;
	
	@NotBlank
	private String strongTerms;
	
	@Range(min = 0, max = 100)
	@Digits(fraction = 2, integer = 2)
	private Double weakThreshold;
	
	
	@Range(min = 0, max = 100)
	@Digits(fraction = 2, integer = 2)
	private Double strongThreshold;
	
	private HashMap<String, String> getMapLenguajes(String terms) {
		HashMap<String, String> m = new HashMap<String, String>();
		String[] lenguages = terms.split(";");
		for (int i = 0; i < lenguages.length; i++) {
			String[] parseLenguage = lenguages[i].split(":");
			String parseTerms = parseLenguage[1].replace(",", " ");
			String lenguage = parseLenguage[0];
			m.put(lenguage, parseTerms);
		}
		return m;
	}
	
	private String getWeakTerms() {
		return getMapLenguajes(this.weakTerms).get(this.lenguage);
	}
	
	private String getStrongTerms() {
		return getMapLenguajes(this.strongTerms).get(this.lenguage);
	}
	private void setStrongTerms(String strongTerms) {
		this.strongTerms = strongTerms;
	}
	
	private void setWeakTerms(String weakTerms) {
		this.weakTerms = weakTerms;
	}

	
	
}
