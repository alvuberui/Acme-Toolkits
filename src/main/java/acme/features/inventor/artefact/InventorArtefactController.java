package acme.features.inventor.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtefactController extends AbstractController<Inventor, Artefact> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorComponentListAllService	componentListAllService;
		
		@Autowired
		protected InventorToolListAllService	toolListAllService;
		
		@Autowired
		protected InventorArtefactShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-components", "list", this.componentListAllService);
			super.addCommand("list-all-tools", "list", this.toolListAllService);
			super.addCommand("show", this.showService);
		}
}
