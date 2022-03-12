package acme.entities.component;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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

		@OneToMany
		@JoinTable(name= "to_work_with",
		joinColumns = {@JoinColumn(name= "fk_component1")},
		inverseJoinColumns = {@JoinColumn(name= "fk_component2")})
		protected List<Component> toWorkWith;

		@ManyToOne
		protected List<Tool> Tools;
}
