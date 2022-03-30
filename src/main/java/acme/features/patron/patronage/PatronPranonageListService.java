package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.stereotype.Service;

import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;


@Service
public class PatronPranonageListService implements AbstractListService<Patron, Patronages>{


	private PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronages> request) {
		assert request != null; 
		
		return true;
	}

	@Override
	public Collection<Patronages> findMany(final Request<Patronages> request) {
		assert request != null;
		
		Collection<Patronages> result;
		result = this.repository.findAllPatronages();
		return result;
	}

	@Override
	public void unbind(final Request<Patronages> request, final Patronages entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"status", "code", "legalStuff", "budget", "inventor");
	}

}
