package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactCreateService implements AbstractCreateService<Inventor,Artefact>{
	
	@Autowired
	protected InventorArtefactRepository repository;
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		
		
		
		return true;
	}

	@Override
	public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "type", "name", "code", "technology", "description", "retailPrice", "moreInfo");
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "moreInfo", "published");
	}

	@Override
	public Artefact instantiate(final Request<Artefact> request) {
		Artefact result;
		Inventor inventor;
		inventor = this.repository.findInventorIdById(request.getPrincipal().getActiveRoleId());
		result = new Artefact();
		result.setPublished(false);
		result.setInventor(inventor);
		return result;
	}

	@Override
	public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Artefact existing;
			
			existing = this.repository.findOneByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.artefact.error.duplicated");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "salary", "inventor.artefact.form.error.negative-salary");
		}
	}

	@Override
	public void create(final Request<Artefact> request, final Artefact entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
