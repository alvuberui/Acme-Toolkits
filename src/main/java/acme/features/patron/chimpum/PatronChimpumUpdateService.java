package acme.features.patron.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;


@Service
public class PatronChimpumUpdateService  implements AbstractUpdateService<Patron,Chimpum>{

	
	@Autowired
	protected PatronChimpumRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {	
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors,"code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,"code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
	
	
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		
		int id;
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		
		return chimpum;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
		
	}

}
