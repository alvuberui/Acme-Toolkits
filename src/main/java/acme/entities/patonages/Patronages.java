package acme.entities.patonages;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;




@Entity
@Getter
@Setter
public class Patronages extends AbstractEntity {
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	private PatronageStatus status;
	
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank
	@Column(length = 256)
	private String legalStuff;
	
	@Min(0)
	private Integer budget;
	
	@Transient
	@CreatedDate
	private LocalDate createTime;
	
	@NotNull
	private LocalDate periodOfTime;
	
	private String link;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Patron patron;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	private Inventor inventor;
	
	
}
