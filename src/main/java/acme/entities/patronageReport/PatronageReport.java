package acme.entities.patronageReport;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;



@SequenceGenerator(name= "serialNumberSeq", initialValue = 0001, allocationSize = 1)


@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	
	@NotNull
	protected Integer				patronageCode;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serialNumberSeq")
	@NotNull
	protected Integer				serialNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date			creationMoment;
	
	
	@Max(256)
	@NotBlank
	protected String				memorandum;
	
	
	@URL
	protected String				link;

	// Derived attributes -----------------------------------------------------
	
	public String getSequenceNumber() {
		return this.patronageCode + ":" + this.serialNumber;
	}

	// Relationships ----------------------------------------------------------

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Patron patron;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Inventor inventor;

}
