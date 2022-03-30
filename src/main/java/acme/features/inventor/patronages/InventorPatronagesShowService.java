package acme.features.inventor.patronages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronagesShowService implements AbstractShowService<Inventor, Patronages> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected InventorPatronagesRepository repository;
				
			@Override
			public boolean authorise(final Request<Patronages> request) {
				assert request != null;

				return true;
			}

			@Override
			public Patronages findOne(final Request<Patronages> request) {
				assert request != null;

				Patronages result;
				int id;

				id = request.getModel().getInteger("id");
				result = this.repository.findPatronagesById(id);

				return result;
			}

			@Override
			public void unbind(final Request<Patronages> request, final Patronages entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationTime", "initPeriod", "finalPeriod", "link");
				
			}
}
