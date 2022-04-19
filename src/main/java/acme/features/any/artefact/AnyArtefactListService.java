package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;


@Service
public class AnyArtefactListService implements AbstractListService<Any, Artefact>{

	@Autowired
	protected AnyArtefactRepository repository;
	
	
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
		ArtefactType artefactType;
		
		artefactTypeToParse = request.getModel().getInteger("artefactType");
		
		if (artefactTypeToParse < 0 || artefactTypeToParse >1) {
			artefactType = ArtefactType.COMPONENT;
		} else if ( artefactTypeToParse == 0) {
			artefactType = ArtefactType.COMPONENT;
		} else {
			artefactType = ArtefactType.TOOL;
		}
		
		result = this.repository.findManyArtifacts(artefactType); //
		
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
