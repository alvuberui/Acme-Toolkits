package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;


@Service
public class InventorChimpumUpdateArtefactService  implements AbstractUpdateService<Inventor,Chimpum>{

	
	@Autowired
	protected InventorChimpumRepository repository;
	
	
	@Override
	public boolean authorise(Request<Chimpum> request) {
		return true;
	}

	@Override
	public void bind(Request<Chimpum> request, Chimpum entity, Errors errors) {	
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "artefactId");
		
		
		Integer componentId = request.getModel().getInteger("artefactId");
		
		
		entity.setArtefact(this.repository.findComponentById(componentId));
	}

	@Override
	public void unbind(Request<Chimpum> request, Chimpum entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		
		request.unbind(entity, model);
		
		Collection<Artefact> artefacts;
		
		artefacts = this.repository.findAllComponent();
		
		model.setAttribute("artefacts", artefacts.stream().filter(x->!Objects.equal(x, entity.getArtefact())).collect(Collectors.toList()));
		if( entity.getArtefact() != null) {
			model.setAttribute("artefactName", entity.getArtefact().getName());
		}
	}

	@Override
	public Chimpum findOne(Request<Chimpum> request) {
		
		int id;
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		
		return chimpum;
	}

	@Override
	public void validate(Request<Chimpum> request, Chimpum entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(Request<Chimpum> request, Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
		
	}

}
