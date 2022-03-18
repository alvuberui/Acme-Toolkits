package acme.entities.patronageReport;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.entities.patonages.Patronages;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;





@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date					creationMoment;
	
	
	@Max(256)
	@NotBlank
	protected String				memorandum;
	
	
	@URL
	protected String				link;

	// Derived attributes -----------------------------------------------------
	
	public String getSequenceNumber() {
		final NumberFormat serialNumber = new DecimalFormat("0000");
		serialNumber.format(this.id);
		return this.patronage.getCode() + ":" + serialNumber;
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
	
	
	
	@Valid
	@NotNull
	@OneToOne
	private Patronages patronage;

}
