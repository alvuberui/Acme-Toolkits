package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;


@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum> {

	
	@Autowired
	protected InventorChimpumRepository repository;

	
	@Override
	public boolean authorise(Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Chimpum> findMany(Request<Chimpum> request) {
		assert request != null;
		Collection<Chimpum> chimpums;
		
		chimpums = this.repository.findAllChimpum();
		
		return chimpums;
	}

	@Override
	public void unbind(Request<Chimpum> request, Chimpum entity, Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "budget", "initPeriod", "finalPeriod", "description", "link");
	}
	
	

}
