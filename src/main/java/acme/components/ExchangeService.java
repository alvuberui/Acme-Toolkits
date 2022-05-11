package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.entities.systemConfiguration.ExchangeRate;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import java.util.Objects;




@Service
public class ExchangeService {
	

	@Autowired
	protected ExchangeRepository repository;
	
	
	public Money exchangeMoney (Money money) {
		
		
		
		Money result = new Money();
		ExchangeRate exchangeRate;
		SystemConfiguration systemConfiguration;
		
		systemConfiguration = this.repository.findSystemConfuration();
		
		if(Objects.equals(money.getCurrency(), systemConfiguration.getCurrency())) {
			return money;
		}

		exchangeRate = this.repository.findExchangeRateByCurrency(money.getCurrency(), systemConfiguration.getCurrency());
		
		
		if(exchangeRate == null || !Objects.equals(exchangeRate.getBase(), systemConfiguration.getCurrency())) {
			exchangeRate = computeMoneyExchange(systemConfiguration.getCurrency(), money.getCurrency(), systemConfiguration.getCurrencies());
			System.out.println("if");
			if(exchangeRate != null) {
				System.out.println("if2");
				result.setAmount(money.getAmount()*exchangeRate.getRate());
				result.setCurrency(systemConfiguration.getCurrency());
			}
		}else {
			System.out.println("else");
			result.setAmount(money.getAmount()*exchangeRate.getRate());
			System.out.println("else");
			result.setCurrency(systemConfiguration.getCurrency());
		}
		
		return result;
	}
	
	private ExchangeRate computeMoneyExchange(final String source, final String targetCurrency, String currencies) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		ExchangeRate result = new ExchangeRate();;
		RestTemplate api;
		ExchangeRateRequest record;
		String sourceCurrency;

		try {
			api = new RestTemplate();

			sourceCurrency = source;

			record = api.getForObject( //
					"https://api.exchangerate.host/latest?base={0}", //
					ExchangeRateRequest.class, //
					sourceCurrency

			);
			if(record != null) {
				for(String c : currencies.split(" ")) {
					System.out.println(currencies.split(" "));
					if(!Objects.equals(c.trim(), source)) {
						ExchangeRate r = new ExchangeRate();
						System.out.println(source);
						System.out.println(c.trim());
						System.out.println(record.getRates());
						r.setBase(record.getBase());
						r.setRate(record.getRates().get(c.trim()));
						r.setDate(record.getDate());
						r.setTarget(c.trim());
						this.repository.save(r);
						if(Objects.equals(c.trim(), targetCurrency)) {
							result = r;
						}
					}
				}
			}else {
				result = null;
			}
	
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}
}
