package acme.features.authenticated.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;


@Controller
public class AuthenticatedToolkitController extends AbstractController<Authenticated, Toolkit>{
	
	@Autowired
	protected AuthenticatedToolkitListAllService listAllService;
	
	@Autowired AuthenticatedToolkitShowService showService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-all","list", this.listAllService);
		super.addCommand("show", this.showService);
	}
}
