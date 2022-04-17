package acme.features.authenticated.toolkit;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedToolkitListAllService implements AbstractListService<Authenticated, Toolkit>{

	
	@Autowired
	protected AuthenticatedToolkitRepository repository;
	
	
	
	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;
		
		
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		
		
		String artefactName;
		
		if(request.getModel().hasAttribute("artefactName") && !Objects.equal(request.getModel().getString("artefactName") , "")) {
			artefactName = "%" + request.getModel().getString("artefactName") + "%";
			result = this.repository.findAllArtefactByName(artefactName);
		}else {
			result = this.repository.findAllToolkit();
		}
	
		
		return result;
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", " assemblyNotes", "link");

	}
	
	

}
