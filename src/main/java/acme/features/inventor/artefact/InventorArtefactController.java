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
		protected InventorListOwnToolsService	ownToolListAllService;
		
		@Autowired
		protected InventorArtefactShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-components", "list", this.componentListAllService);
			super.addCommand("list-own-tools", "list", this.ownToolListAllService);
			super.addCommand("show", this.showService);
		}
}
