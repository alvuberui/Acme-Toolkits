package acme.entities.component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.toolkit.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Component extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
		
	// Attributes -------------------------------------------------------------	
		
		@NotBlank
		@Length(max=101)
		protected String name;
		
		@NotBlank
		@Length(max=101)
		protected String technology;
		
		@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
		@Column(unique =true)
		protected String code;
		
		@NotBlank
		@Length(max=256)
		protected String description;

		@Valid
		protected Money retailPrice;
		
		@URL
		protected String moreInfo;
		
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		protected Toolkit toolkit;
}	
