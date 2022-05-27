package acme.features.inventor.chimpum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;


@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor,Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;

	
	@Override
	public boolean authorise(Request<Chimpum> request) {
		return true;
	}

	@Override
	public Chimpum findOne(Request<Chimpum> request) {
		assert request != null;
		
		int id;
		
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		return chimpum;
	}


	@Override
	public void unbind(Request<Chimpum> request, Chimpum entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link", "creationMoment");
		model.setAttribute("chimpumId", entity.getId());
	}

}
