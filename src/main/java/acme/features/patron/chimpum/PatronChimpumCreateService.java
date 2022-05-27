package acme.features.patron.chimpum;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronChimpumCreateService implements AbstractCreateService<Patron, Chimpum>{

	@Autowired
	protected PatronChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
		
		entity.setCreationMoment(Date.from(Instant.now()));
		
	
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
		
	}

	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0 , "budget", "inventor.toolkit.form.label.budget.positive.error");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, SpamDetector.error(entity.getDescription(),  this.repository.findSystemConfiguration()), "description", "any.form.error.spam");
		}
		
	
		if(!errors.hasErrors("initPeriod") && !errors.hasErrors("finalPeriod")) {			
			final Period p2 = Period.between(LocalDate.of(entity.getCreationMoment().getYear(), entity.getCreationMoment().getMonth()+1, entity.getCreationMoment().getDate()), 
					LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()));
			System.out.println(p2.getMonths());
			
			final long p = ChronoUnit.DAYS.between(LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()), 
					LocalDate.of(entity.getFinalPeriod().getYear(), entity.getFinalPeriod().getMonth()+1, entity.getFinalPeriod().getDate()));
			System.out.println(p);
			errors.state(request, p2.getMonths() >= 1, "initPeriod", "inventor.toolkit.form.label.period.month.error");
			errors.state(request, p >= 7, "finalPeriod", "inventor.toolkit.form.label.period.week.error");
		}
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		Chimpum result;
		
		result = new Chimpum();
		
		
		return result;
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		final String pattern = "MM/dd/yy";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		entity.setCode(String.format("%s-%s",entity.getCode() ,simpleDateFormat.format(Date.from(Instant.now()))));
		this.repository.save(entity);
		
	}

}
