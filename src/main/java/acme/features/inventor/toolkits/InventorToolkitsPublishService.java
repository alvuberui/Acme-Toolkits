package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitsPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitsRepository repository;

	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;
		
		
		
		Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(request.getPrincipal().getActiveRoleId());
		boolean isMine = toolkits.stream().anyMatch(x -> x.getId() == request.getModel().getInteger("id"));
	
		
		return isMine;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");
		
		
	}

	@Override
	public Toolkit findOne(Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		return result;
	}

	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Collection<Artefact> tools = this.repository.findToolsByToolkit(entity.getId());
		
		if(tools == null || tools.isEmpty()){
			errors.state(request, tools != null && !tools.isEmpty() , "*", "inventor.toolkit.published.error.artefacts");
		}
	}

	@Override
	public void update(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;
	
		
		entity.setPublished(true);
		this.repository.save(entity);
	}
	
	

}