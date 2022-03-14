package acme.entities.chirp;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date           	creationMoment;
	
	
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
