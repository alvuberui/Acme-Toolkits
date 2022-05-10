package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patonages.PatronageStatus;
import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronages> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronages> request) {
		assert request != null;
		
		boolean result;
		result = request.getPrincipal().hasRole(Patron.class);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronages> request, final Patronages entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code","status", "legalStuff", "budget", "initPeriod", "finalPeriod", "link");

	}

	@Override
	public void unbind(final Request<Patronages> request, final Patronages entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "status", "legalStuff", "budget", "initPeriod", "finalPeriod", "link");
		
	}
	
	@Override
	public Patronages instantiate(final Request<Patronages> request) {
		
		Patronages result;
		result = new Patronages();
		
		Patron patron;
		patron = this.repository.findPatronByUserAccountId(request.getPrincipal().getActiveRoleId());
		result.setPatron(patron);
		
		Date creationMoment;
		creationMoment = new Date(System.currentTimeMillis()-1);
		result.setCreationTime(creationMoment);
		
		result.setStatus(PatronageStatus.PROPOSED);
		result.setPublished(false);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronages> request, final Patronages entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronages exists;
			
			exists = this.repository.findPatronageByCode(entity.getCode());
			errors.state(request, exists == null, "code", "patron.patronages.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("budget")) {
			Double budget;
			
			budget = entity.getBudget().getAmount();
			errors.state(request, budget != null && budget > 0, "code", "patron.patronages.form.error.budget-negative");
		}
		
		if(!errors.hasErrors("initPeriod")) {
			Calendar actualDate;
			Date prueba;

			actualDate = new GregorianCalendar();
			actualDate.add(Calendar.MONTH, 1);
			prueba = actualDate.getTime();
			
			errors.state(request, entity.getInitPeriod() != null && entity.getInitPeriod().after(prueba), "initPeriod", "patron.patronages.form.error.initPeriod-too-close");
		}
		
		if (!errors.hasErrors("finalPeriod")) {
			Date finalPeriod;
			Date initialPeriod;
			Calendar monthDate;
			Date prueba;
			
			initialPeriod = entity.getInitPeriod();
			finalPeriod = entity.getFinalPeriod();
			monthDate = new GregorianCalendar();
			monthDate.setTime(initialPeriod);
			monthDate.add(Calendar.MONTH, 1);
			
			prueba = monthDate.getTime();
			
			errors.state(request, finalPeriod != null && finalPeriod.after(prueba), "finalPeriod", "patron.patronages.form.error.finalPeriod-too-close");
		}
		
		if (!errors.hasErrors("inventor")) {
			String userName;
			Inventor inventor;
			
			userName = request.getModel().getString("inventor");
			inventor = this.repository.findInventorByUserName(userName);
			errors.state(request, inventor!=null && !userName.equals(""), "inventor", "patron.patronages.form.error.invalid.inventor-username");
		}
		
	}

	@Override
	public void create(final Request<Patronages> request, final Patronages entity) {
		assert request != null;
		assert entity != null;
		
		final String userName = request.getModel().getString("inventor");
		
		final Inventor inventor = this.repository.findInventorByUserName(userName);
		
		final Patron patron = this.repository.findPatronByUserAccountId(request.getPrincipal().getActiveRoleId());
		
		
		entity.setInventor(inventor);
		this.repository.save(entity);
		
		entity.setPatron(patron);
		this.repository.save(entity);
		
		entity.setStatus(PatronageStatus.PROPOSED);
		this.repository.save(entity);
		
		entity.setPublished(false);
		this.repository.save(entity);
		
		this.repository.save(entity);
		
	}

}
