package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount>{


	@Autowired
	protected AnyUserAccountRepository userAccountrepository;



	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.userAccountrepository.findOneUserAccountById(id);
		result.getRoles().forEach(r -> {;});

		return result;
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Collection<UserRole> roles;
		final List<String> rolesAuthorityName = new ArrayList<String>();
			
		roles = entity.getRoles();
		for (final UserRole rol : roles) {
			rolesAuthorityName.add(rol.getAuthorityName());
	}
		
		model.setAttribute("rol", rolesAuthorityName);
		request.unbind(entity, model, "roles", "username", "identity.name", "identity.surname", "identity.email");
	}

}
