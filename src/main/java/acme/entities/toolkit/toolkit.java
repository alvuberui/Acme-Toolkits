package acme.entities.toolkit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.tool.Tool;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class toolkit extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String code;
	
	@Length(max = 101)
	@NotBlank
	protected String title;
	
	@Length(max = 256)
	@NotBlank
	protected String description;
	
	@Length(max = 256)
	@NotBlank
	protected String assemblyNotes;
	
	@URL
	protected String link;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne(optional=false) 
	protected Tool tool;
}
