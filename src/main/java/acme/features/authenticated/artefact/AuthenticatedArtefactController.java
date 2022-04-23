package acme.features.authenticated.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedArtefactController extends AbstractController<Authenticated, Artefact> {
	// Internal state ---------------------------------------------------------

		@Autowired 
		protected AuthenticatedArtefactListByToolkitService authenticatedArtefactListByToolkitService;
		
		@Autowired 
		protected AuthenticatedArtefactShowService showService;
		

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-artefact-toolkit","list", this.authenticatedArtefactListByToolkitService);
			super.addCommand("show", this.showService);
		}
}
