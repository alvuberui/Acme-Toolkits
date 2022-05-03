package acme.features.inventor.toolkits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitsCreateService implements AbstractCreateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitsRepository repository;
	
	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;
		
		int masterId;
		Artefact artefact;
		Inventor inventor;
		
		masterId = request.getModel().getInteger("masterId");
		artefact = this.repository.findArtefactById(masterId);
		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		
	
		return artefact != null && artefact.getInventor() == inventor;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link", "published", "quantity");

	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int masterId;

		
		masterId = request.getModel().getInteger("masterId");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");
		model.setAttribute("quantity", 1);
		model.setAttribute("masterId", masterId);
		
		
	}

	
	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//Sin terminar
	}

	@Override
	public Toolkit instantiate(Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
	
		result = new Toolkit();
		result.setPublished(false);
	
		
		return result;
	}

	@Override
	public void create(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;

		int masterId;
		Artefact artefact;
		Quantity quantity;
		Integer number;
		
		masterId = request.getModel().getInteger("masterId");
		artefact = this.repository.findArtefactById(masterId);
		
		quantity = new Quantity();
		quantity.setArtefact(artefact);
		
		number = request.getModel().getInteger("quantity");
		
		quantity.setNumber(number);
		
	

		this.repository.save(entity);
		quantity.setToolkit(entity);
		this.repository.save(quantity);
		
	}

}
