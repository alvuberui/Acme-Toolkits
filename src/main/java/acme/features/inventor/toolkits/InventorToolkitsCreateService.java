package acme.features.inventor.toolkits;

import java.util.Collection;
import java.util.stream.Collectors;

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
		
		return true;
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
		Collection<Artefact> artefacts;
		artefacts = this.repository.findArtefactsFromInventor(request.getPrincipal().getActiveRoleId());
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");
		model.setAttribute("quantity", 1);
		model.setAttribute("artefacts", artefacts);
		
	}

	
	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	
		boolean isDuplicatedCode, isValidQuantity;
		if(!errors.hasErrors("code")) {
			isDuplicatedCode = this.repository.findAllToolkits().stream().noneMatch(x-> x.getCode().equals(entity.getCode()));
			errors.state(request, isDuplicatedCode , "code", "inventor.toolkit.form.label.code.duplicate.error");
		}
		
		if(!errors.hasErrors("quantity")) {
			isValidQuantity = request.getModel().hasAttribute("quantity") && !"".equals(request.getModel().getString("quantity").trim()) && request.getModel().getInteger("quantity") > 0;
			errors.state(request, isValidQuantity , "quantity", "inventor.toolkit.form.label.quantity.error");
		}

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


		int artefactId;
		Artefact artefact;
		Quantity quantity;
		Integer number;
		
		artefactId = request.getModel().getInteger("artefactId");
		artefact = this.repository.findArtefactById(artefactId);
		
		quantity = new Quantity();
		quantity.setArtefact(artefact);
		
		number = request.getModel().getInteger("quantity");
		
		quantity.setNumber(number);

		this.repository.save(entity);
		quantity.setToolkit(entity);
		this.repository.save(quantity);
		this.repository.save(quantity);
		
	}

}
