package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;


@Controller
public class InventorChimpumController  extends AbstractController<Inventor, Chimpum>{
	
	@Autowired 
	protected InventorChimpumListService listAll;
	
	@Autowired 
	protected InventorChimpumShowService show;
	
	@Autowired 
	protected InventorChimpumCreateService create;
	@Autowired 
	protected InventorChimpumUpdateArtefactService updateArtefact;
	@Autowired 
	protected InventorChimpumUpdateService update;
	
	@Autowired 
	protected InventorChimpumDeleteService delete;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.show);
		super.addCommand("create", this.create);
		super.addCommand("list", this.listAll);
		super.addCommand("update-artefact","update" ,this.updateArtefact);
		super.addCommand("update" ,this.update);
		super.addCommand("delete" ,this.delete);
	}
}
