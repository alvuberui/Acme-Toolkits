package acme.features.inventor.patronages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronagesShowService implements AbstractShowService<Inventor, Patronages> {
	
	// Internal state ---------------------------------------------------------

	

			@Autowired
			protected ExchangeService exchangeService ;
			@Autowired
			protected InventorPatronagesRepository repository;
				
			@Override
			public boolean authorise(final Request<Patronages> request) {
				assert request != null;

				final boolean result;
				int id;
				final Integer inventorId;
				final Principal principal;
				final Patronages patronage;
				
				id = request.getModel().getInteger("id");
				patronage = this.repository.findPatronagesById(id);
				inventorId = patronage.getInventor().getId();
				principal = request.getPrincipal();
				result = (principal.getActiveRoleId() == inventorId);

				return result;
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
				final Inventor inventor = this.repository.findInventorByPatronageId(request.getModel().getInteger("id"));
				
				model.setAttribute("username", inventor.getUserAccount().getUsername());
				model.setAttribute("company", inventor.getCompany());

				request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationTime", "initPeriod", "finalPeriod", "link");
				model.setAttribute("moneyExchange", exchangeService.exchangeMoney(entity.getBudget()));
				
			}
}
