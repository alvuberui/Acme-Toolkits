package acme.features.authenticated.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;


@Service
public class AuthenticatedToolkitShowService implements AbstractShowService<Authenticated, Toolkit>{

	@Autowired
	protected AuthenticatedToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		Collection<Artefact> components;
		Artefact tool;
		int id;
		
		
		id = request.getModel().getInteger("id");
		components = repository.findComponentsByToolkitId(id);
		tool = repository.findToolByToolkitId(id);
	
	
		request.unbind(entity, model, "code", "title", "description", " assemblyNotes", "link");
		model.setAttribute("components", components);
		model.setAttribute("tool", tool);
	}

}
