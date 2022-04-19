package acme.features.any.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyChirpController extends AbstractController<Any, Chirp>{

	
	//Internal state ------------------------------------------------
	
	@Autowired
	protected AnyChirpListRecentService chirpListAllService;
	
	// Constructors -------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.chirpListAllService);
	}
	
}
