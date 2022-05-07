package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patonages.Patronages;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronages> {

	
	@Autowired
	protected PatronPatronageRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Patronages> request) {
		assert request != null;
		
		boolean result;
		int patronageId;
		Patronages patronage;
		Patron patron;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOneById(patronageId);
		patron = patronage.getPatron();
		result = !patronage.isPublished() && request.isPrincipal(patron);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronages> request, final Patronages entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status","code","legalStuff","budget","creationTime","initPeriod","finalPeriod","link","published");
		
	}

	@Override
	public void unbind(final Request<Patronages> request, final Patronages entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status","code","legalStuff","budget","creationTime","initPeriod","finalPeriod","link","published");
		
	}

	@Override
	public Patronages findOne(final Request<Patronages> request) {
		assert request != null;
		
		Patronages result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
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
			System.out.println(exists);
			System.out.println(exists==null);
			errors.state(request, exists == null, "code", "patron.patronages.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("budget")) {
			Double budget;
			
			budget = entity.getBudget().getAmount();
			errors.state(request, budget != null && budget > 0, "code", "patron.patronages.form.error.budget-negative");
		}
		
		if(!errors.hasErrors("initPeriod")) {
			Date initPeriod;
			Calendar actualDate;
			Date prueba;
			
			initPeriod = entity.getInitPeriod();
			actualDate = new GregorianCalendar();
			actualDate.add(Calendar.MONTH, 1);
			prueba = actualDate.getTime();
			
			errors.state(request, initPeriod != null && entity.getInitPeriod().after(prueba), "initPeriod", "patron.patronages.form.error.initPeriod-too-close");
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
		
		
	}

	@Override
	public void update(final Request<Patronages> request, final Patronages entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}
}
