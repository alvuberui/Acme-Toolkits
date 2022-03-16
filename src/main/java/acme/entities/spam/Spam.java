package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;




@Entity
public class Spam extends AbstractEntity{


	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	private SpamType type;
	
	
	@NotNull
	private Language language;
	
	@NotBlank
	private String term;
	
	
	
	public Double threshold() {
		if(this.type.equals(SpamType.WEAK)) {
			return 0.25;
		}
		return 0.1;	
	}
	
	
	

}
