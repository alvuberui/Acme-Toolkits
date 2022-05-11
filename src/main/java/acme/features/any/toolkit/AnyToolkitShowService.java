package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.entities.artefact.Artefact;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;


@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{
	

	@Autowired
	protected AnyToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		Collection<Artefact> artefacts;
		int id;
		SystemConfiguration systemConfiguration;
		id = request.getModel().getInteger("id");
		artefacts = this.repository.findArtefactByToolkitId(id);
		
		systemConfiguration = this.repository.findSystemConfuration();
	
		
		
		// Model attributes
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		model.setAttribute("toolkitId", entity.getId());
		if(systemConfiguration != null && systemConfiguration.getCurrency() != null) {
			if(!artefacts.isEmpty()) {
				final Double price = artefacts.stream().map(x -> this.computeMoneyExchange(x.getRetailPrice(), systemConfiguration.getCurrency()).getTarget().getAmount()).reduce(0.0, (a, b) -> a + b);
				final Money money =  new Money();
				money.setAmount(price);
				money.setCurrency(systemConfiguration.getCurrency());
				model.setAttribute("price",money);
			}else {
				final Money money =  new Money();
				money.setAmount(0.0);
				money.setCurrency(systemConfiguration.getCurrency());
				model.setAttribute("price",money);
			}
			
		}
		
		
	}
	
	
	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange result;
		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		Money target;

		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				sourceCurrency, //
				targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			target = new Money();
			target.setAmount(targetAmount);
			target.setCurrency(targetCurrency);

			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);
			result.setDate(record.getDate());
			result.setTarget(target);
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}
}
