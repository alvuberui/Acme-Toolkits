package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{

	
	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "currency", "currencies", "languageSystem","weakTermsSpanish","weakTermsEnglish", "strongTermsEnglish","strongTermsSpanish","weakThreshold","strongThreshold");
		
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		

		request.unbind(entity, model, "currency", "currencies", "languageSystem","weakTermsSpanish","weakTermsEnglish", "strongTermsEnglish","strongTermsSpanish","weakThreshold","strongThreshold");
		
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;
		result = this.repository.getSystemConfiguration();

		return result;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;
		
		final String weakTerms;
		final String strongTerms;
		
		
		final String weakTermsSpanish = "SPANISH:" +request.getModel().getString("weakTermsSpanish");
		final String weakTermsEnglish = "ENGLISH:" + request.getModel().getString("weakTermsEnglish");
		final String strongTermsSpanish ="SPANISH:"+request.getModel().getString("strongTermsSpanish");
		final String strongTermsEnglish = "ENGLISH:" +request.getModel().getString("strongTermsEnglish");
		
		if(weakTermsSpanish.equals("SPANISH:")){
			weakTerms= weakTermsEnglish;
		}else if(weakTermsEnglish.equals("ENGLISH:")) {
			weakTerms= weakTermsSpanish;
		}else {
			weakTerms= weakTermsSpanish + ";"+  weakTermsEnglish;
		}
		
		if(strongTermsSpanish.equals("SPANISH:")){
			strongTerms= strongTermsEnglish;
		}else if(weakTermsEnglish.equals("ENGLISH:")) {
			strongTerms= strongTermsSpanish;
		}else {
			strongTerms= strongTermsSpanish + ";"+  strongTermsEnglish;
		}
		
		
		entity.setStrongTerms(strongTerms);
		entity.setWeakTerms(weakTerms);
		
		this.repository.save(entity);
		
	}

}
