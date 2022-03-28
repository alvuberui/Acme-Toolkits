package acme.features.administrator.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorChirpControler extends AbstractController<Administrator, Chirp>{

	//Internal state ------------------------------------------------
	
		@Autowired
		protected AdministratorChirpListAllService chirpListAllService;
		
		// Constructors -------------------------------------------------
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.chirpListAllService);
		}
	
}
