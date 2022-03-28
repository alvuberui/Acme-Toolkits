package acme.features.patron.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronChirpController extends AbstractController<Patron, Chirp>{

	
	//Internal state ------------------------------------------------
	
	@Autowired
	protected PatronChirpListRecentService chirpListAllService;
	
	// Constructors -------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.chirpListAllService);
	}
	
}
