package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;


@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	@Autowired
	protected AnyChirpRepository repository;
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "title", "author", "body", "email");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "author", "body", "email");
		
	}
	

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Chirp();
		result.setCreationMoment(moment);
		result.setTitle("");
		result.setAuthor("");
		result.setBody("");
		result.setEmail("");

		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		if (!errors.hasErrors("body")) {
			errors.state(request, SpamDetector.spamWeakTerms(entity.getBody(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.error.body.form.weakSpam");
			
			errors.state(request, SpamDetector.spamStrongTerms(entity.getBody(), this.repository.getSystemConfiguration()), "body", "inventor.patronage-report.error.body.form.weakSpam");
		}
		
		if (!errors.hasErrors("author")) {
			errors.state(request, SpamDetector.spamWeakTerms(entity.getAuthor(), this.repository.getSystemConfiguration()), "author", "inventor.patronage-report.error.author.form.weakSpam");
			
			errors.state(request, SpamDetector.spamStrongTerms(entity.getAuthor(), this.repository.getSystemConfiguration()), "author", "inventor.patronage-report.error.author.form.weakSpam");
		}
		
		if (!errors.hasErrors("title")) {
			errors.state(request, SpamDetector.spamWeakTerms(entity.getTitle(), this.repository.getSystemConfiguration()), "title", "inventor.patronage-report.error.title.form.weakSpam");
			
			errors.state(request, SpamDetector.spamStrongTerms(entity.getTitle(), this.repository.getSystemConfiguration()), "title", "inventor.patronage-report.error.title.form.weakSpam");
		}
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		
		this.repository.save(entity);
		
	}

	
}
