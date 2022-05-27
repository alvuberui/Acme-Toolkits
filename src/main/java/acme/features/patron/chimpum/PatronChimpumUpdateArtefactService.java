package acme.features.patron.chimpum;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;


@Service
public class PatronChimpumUpdateArtefactService  implements AbstractUpdateService<Patron,Chimpum>{

	
	@Autowired
	protected PatronChimpumRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {	
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "artefactId");
		
		
		final Integer componentId = request.getModel().getInteger("artefactId");
		
		
		entity.setArtefact(this.repository.findComponentById(componentId));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
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
	public Chimpum findOne(final Request<Chimpum> request) {
		
		int id;
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		
		return chimpum;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
		
	}

}
