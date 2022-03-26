package acme.features.patron.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronArtefactController extends AbstractController<Patron, Artefact> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronComponentListAllService	componentListAllService;
		
		@Autowired
		protected PatronArtefactShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-components", "list", this.componentListAllService);
			super.addCommand("show", this.showService);
		}
}
