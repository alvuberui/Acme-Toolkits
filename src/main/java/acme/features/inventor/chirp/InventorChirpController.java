package acme.features.inventor.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChirpController extends AbstractController<Inventor, Chirp>{

	
	//Internal state ------------------------------------------------
	
			@Autowired
			protected InventorChirpListRecentService chirpListAllService;
			
			// Constructors -------------------------------------------------
			
			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.chirpListAllService);
			}
}
