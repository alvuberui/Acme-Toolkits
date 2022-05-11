

package acme.features.inventor.toolkits;

import static org.mockito.ArgumentMatchers.booleanThat;

import java.util.Collection;
import java.util.stream.Collector;
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
public class InventorToolkitsDeleteArtefactService implements AbstractUpdateService<Inventor, Toolkit>{

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
		Collection<Artefact> artefactsOfToolkit;
		artefacts = this.repository.findArtefactsFromInventor(request.getPrincipal().getActiveRoleId());
		
		artefactsOfToolkit = this.repository.findComponentsAndToolsByToolkitId(entity.getId());
		
	
		request.unbind(entity, model, "code", "title");
		model.setAttribute("quantity", 0);
		model.setAttribute("artefacts", artefacts.stream().filter(artefactsOfToolkit::contains).collect(Collectors.toList()));
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
		
	}

	@Override
	public void update(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;

		int artefactId;
		Quantity quantity;
		Integer number;
		
		artefactId = request.getModel().getInteger("artefactId");
		number = request.getModel().getInteger("quantity");
		
		quantity = this.repository.findQuantityByToolkitAndArtefact(entity.getId(), artefactId);
		
		if(quantity.getNumber()  - number <= 0) {
			System.out.println(quantity);
			this.repository.delete(quantity);
			if(this.repository.findQuantityByToolkit(entity.getId()).isEmpty()) {
				this.repository.delete(entity);
			}
		}else {
			quantity.setNumber(quantity.getNumber()  - number);
			this.repository.save(quantity);
		}

		
	}
}


