package acme.entities;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chirp extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	
	protected LocalDateTime            	creationMoment;
	
	
	@Max(101)
	@NotBlank
	protected String			title;
	
	@Max(101)
	@NotBlank
	protected String			author;
	
	
	@Max(256)
	@NotBlank
	protected String			body;
	
	
	@Email
	protected String			email;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
