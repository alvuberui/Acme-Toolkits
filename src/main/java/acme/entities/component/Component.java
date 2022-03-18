package acme.entities.component;

import javax.persistence.Entity;

import acme.entities.artefact.Artefact;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Component extends Artefact{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

}	

