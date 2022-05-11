/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Provider;

@Service
public class AuthenticatedProviderCreateService implements AbstractCreateService<Authenticated, Provider> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedProviderRepository repository;

	// AbstractCreateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Provider> request) {
		assert request != null;

		boolean result;
		
		result = !request.getPrincipal().hasRole(Provider.class); 

		return result;
	}

	@Override
	public void bind(final Request<Provider> request, final Provider entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "sector");
	}

	@Override
	public void unbind(final Request<Provider> request, final Provider entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Provider instantiate(final Request<Provider> request) {
		assert request != null;

		Provider result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Provider();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Provider> request, final Provider entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if (!errors.hasErrors("sector")) {
			errors.state(request, SpamDetector.spamWeakTerms(entity.getSector(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.sector.title.form.weakSpam");
			
			errors.state(request, SpamDetector.spamStrongTerms(entity.getSector(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.sector.title.form.weakSpam");
		}
		
		
		if (!errors.hasErrors("company")) {
			errors.state(request, SpamDetector.spamWeakTerms(entity.getCompany(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.error.company.form.weakSpam");
			
			errors.state(request, SpamDetector.spamStrongTerms(entity.getCompany(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.error.company.form.weakSpam");
		}
	}

	@Override
	public void create(final Request<Provider> request, final Provider entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Provider> request, final Response<Provider> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
