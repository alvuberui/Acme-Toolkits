package acme.features.anonymous.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Anonymous;

@Controller
public class AnonymousArtefactController extends AbstractController<Anonymous, Artefact> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousComponentListAllService	componentListAllService;
		
		@Autowired
		protected AnonymousArtefactShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-components", "list", this.componentListAllService);
			super.addCommand("show", this.showService);
		}
}
