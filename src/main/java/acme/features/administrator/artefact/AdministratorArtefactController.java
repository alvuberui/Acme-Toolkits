package acme.features.administrator.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artefact.Artefact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorArtefactController extends AbstractController<Administrator, Artefact> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorComponentListAllService	componentListAllService;
		
		@Autowired
		protected AdministratorArtefactShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-components", "list", this.componentListAllService);
			super.addCommand("show", this.showService);
		}
}
