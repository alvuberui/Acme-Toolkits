package acme.entities.tool;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends Artefact{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	@ManyToOne(optional=false) 
	protected Toolkit toolkit;
	
}
