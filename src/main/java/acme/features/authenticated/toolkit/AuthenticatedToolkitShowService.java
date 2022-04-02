package acme.features.authenticated.toolkit;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
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
		Money price = new Money();
		
		id = request.getModel().getInteger("id");
	
		
		tool = repository.findToolByToolkitId(id);
		
		if(tool != null) {
			model.setAttribute("toolId", tool.getId());
			price.setAmount(tool.getRetailPrice().getAmount());
			price.setCurrency(tool.getRetailPrice().getCurrency());
		}
		
		
		components = repository.findComponentsByToolkitId(id);
		if(components != null && !components.isEmpty()) {
			price.setAmount(components.stream().map(x->x.getRetailPrice().getAmount()).reduce(0.0, (a, b) -> a + b) + price.getAmount());;
			model.setAttribute("toolkitId", entity.getId());
		}
		
		
	
		request.unbind(entity, model, "code", "title", "description", " assemblyNotes", "link");
		model.setAttribute("price", price);
	}

}
