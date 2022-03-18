package acme.entities.component;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.toolkit.Toolkit;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;





@Entity
@Getter
@Setter
public class ComponentToolKit extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	
	


	@Valid
	@ManyToOne(optional=false)
	private Component component;
	

	@Valid
	@ManyToOne(optional=false)
	private Toolkit toolkit;
	
}
