package acme.features.inventor.toolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/toolkit")
public class InventorToolkitsController extends AbstractController<Inventor, Toolkit>{

	// Internal state ----------------------------------------------------------------
	
	@Autowired
	protected InventorToolkitsListMineService listMineService;
	
	@Autowired
	protected InventorToolkitsShowService showService;
	
	// Constructors -------------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine", "list", this.listMineService);
		super.addCommand("show", this.showService);
	}
	
}
