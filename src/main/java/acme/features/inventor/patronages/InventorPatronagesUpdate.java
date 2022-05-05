package acme.features.inventor.patronages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patonages.PatronageStatus;
import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronagesUpdate implements AbstractUpdateService<Inventor, Patronages>{

	@Autowired
	protected InventorPatronagesRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Patronages> request) {
		final int id = request.getModel().getInteger("id");
		final Patronages patronage = this.repository.findPatronagesById(id);
		System.out.println(request.getModel().getAttribute("status"));
		return patronage.getInventor().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<Patronages> request, final Patronages entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "code", "legalStuff", 
			"budget", "initPeriod", "finalPeriod", "link", "username", "company");
		
	}

	@Override
	public void unbind(final Request<Patronages> request, final Patronages entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "code", "legalStuff", 
			"budget", "initPeriod", "finalPeriod", "link", "username", "company");
		
	}

	@Override
	public Patronages findOne(final Request<Patronages> request) {
		assert request != null;
		return this.repository.findPatronagesById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Patronages> request, final Patronages entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("status")) {
			errors.state(request,request.getModel().getString("status").equals("ACCEPTED") || request.getModel().getString("status").equals("DENIED"), "status", "inventor.patronage.form.error.status");
		}
		
		
	}

	@Override
	public void update(final Request<Patronages> request, final Patronages entity) {
		assert request != null;
		assert entity != null;
		final PatronageStatus status = PatronageStatus.valueOf(request.getModel().getString("status"));
		System.out.println(status);
		entity.setStatus(status);
		this.repository.save(entity);
		
	}

}
