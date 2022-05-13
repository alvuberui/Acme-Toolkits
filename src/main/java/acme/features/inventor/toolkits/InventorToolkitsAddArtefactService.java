

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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;


@Service
public class InventorToolkitsAddArtefactService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitsRepository repository;
	
	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;
		
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		
		
		Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(request.getPrincipal().getActiveRoleId());
		boolean isMine = toolkits.stream().anyMatch(x -> x.getId() == request.getModel().getInteger("id"));
		return !result.isPublished() && isMine;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title");
		
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		Collection<Artefact> artefacts;
		artefacts = this.repository.findArtefactsPublished();
		
		request.unbind(entity, model, "code", "title");
		model.setAttribute("quantity", 1);
		model.setAttribute("artefacts", artefacts);
	}

	@Override
	public Toolkit findOne(Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		System.out.println("a");
		return result;
	}

	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;

		int artefactId;
		Artefact artefact;
		Quantity quantity;
		Integer number;
		
		artefactId = request.getModel().getInteger("artefactId");
		artefact = this.repository.findArtefactById(artefactId);
		System.out.println("a");
		quantity = this.repository.findQuantityByToolkitAndArtefact(entity.getId(), artefactId);
		
		System.out.println("a");
		if(quantity == null) {
			quantity = new Quantity();
			quantity.setArtefact(artefact);
			quantity.setToolkit(entity);
		}
		
	
		
		number = request.getModel().getInteger("quantity");
		quantity.setNumber(number);

		this.repository.save(entity);	
		this.repository.save(quantity);
	}
}


