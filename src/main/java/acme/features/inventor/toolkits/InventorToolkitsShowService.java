package acme.features.inventor.toolkits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitsShowService implements AbstractShowService<Inventor, Toolkit>{

	// Internal state --------------------------------------------------------------------
	
	@Autowired
	protected InventorToolkitsRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final boolean result;
		int masterId;
		Integer inventorId;
		final Principal principal;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(masterId);
		inventorId = this.repository.findInventorIdByToolkitId(toolkit.getId());
		principal = request.getPrincipal();
		result = (principal.getActiveRoleId() == inventorId);
		return result;
	}

	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code","title","description","assemblyNotes","link","published");

	}
}
