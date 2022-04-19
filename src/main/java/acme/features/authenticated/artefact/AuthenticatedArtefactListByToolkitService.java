package acme.features.authenticated.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.features.inventor.artefact.InventorArtefactRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedArtefactListByToolkitService implements AbstractListService<Authenticated, Artefact>{

	@Autowired
	protected AuthenticatedArtefactRepository repository;

	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		
			
		return true;
	}


	@Override
	public Collection<Artefact> findMany(final Request<Artefact> request) {
		assert request != null;
		
		Collection<Artefact> result;
		final int artefactTypeToParse;
		final int masterId;
		
	
		masterId = request.getModel().getInteger("masterId");
		artefactTypeToParse = request.getModel().getInteger("artefactType");
		
		if (artefactTypeToParse == 0 || artefactTypeToParse < 0 || artefactTypeToParse >1) {
			result = this.repository.findComponentsByToolkitId(masterId); //
		} else {
			result = this.repository.findToolsByToolkitId(masterId); //	
		}
		

		
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
