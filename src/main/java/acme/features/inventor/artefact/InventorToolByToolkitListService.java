package acme.features.inventor.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolByToolkitListService implements AbstractListService<Inventor, Artefact>{

	@Autowired
	protected InventorArtefactRepository repository;

	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;

		final boolean result;
		int masterId;
		Integer inventorId;
		Principal principal;
		Integer artefactId;
		
		masterId = request.getModel().getInteger("masterId");
		artefactId = this.repository.findArtefactIdByToolkitId(masterId);
		inventorId = this.repository.findInventorIdByArtefactId(artefactId);
		principal = request.getPrincipal();
		result = principal.getActiveRoleId() == inventorId;
			
		return result;
	}
	

	@Override
	public Collection<Artefact> findMany(final Request<Artefact> request) {
		assert request != null;

		Collection<Artefact> result;

		int id;
		
		id = request.getModel().getInteger("masterId");
		result = this.repository.findToolsByToolkitId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "technology",
			"description","retailPrice", "moreInfo");
		
	}
	
}
