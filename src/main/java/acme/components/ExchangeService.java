package acme.components;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.entities.systemConfiguration.ExchangeRate;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Objects;





@Service
public class ExchangeService {
	

	@Autowired
	protected ExchangeRepository repository;
	

	public Money exchangeMoneySystemConfiguration (Money money) {
		SystemConfiguration systemConfiguration;
		systemConfiguration = this.repository.findSystemConfuration();
		return exchangeMoneyService(money, systemConfiguration.getCurrency());
	}

	public Money exchangeMoneyToTarget (Money money, String target) {
		return exchangeMoneyService(money, target);
	}
	
	
	@SuppressWarnings("deprecation")
	private Money exchangeMoneyService (Money money,String target) {
		
		Money result = new Money();
		ExchangeRate exchangeRate;
		SystemConfiguration systemConfiguration;
		
		systemConfiguration = this.repository.findSystemConfuration();
		
		
		if(Objects.equals(money.getCurrency(), target)) {
			return money;
		}
		exchangeRate = this.repository.findExchangeRateByCurrency(String.format("%s,%s",target,money.getCurrency()));
		
		
		if(exchangeRate == null) {
			exchangeRate = computeMoneyExchange(money.getCurrency(),target, systemConfiguration.getCurrencies());
		}
		Date fecha = exchangeRate.getDate();
		Date creationMoment = new Date(System.currentTimeMillis());
		Period p = Period.between(LocalDate.of(creationMoment.getYear(), creationMoment.getMonth()+1, creationMoment.getDate()), 
				LocalDate.of(fecha.getYear(), fecha.getMonth()+1, fecha.getDate()));
		
		
		if(p.getMonths()<-1) {
			exchangeRate = computeMoneyExchange(target, money.getCurrency(), systemConfiguration.getCurrencies());
			if(exchangeRate != null) {
				result.setAmount(money.getAmount()*exchangeRate.getRate());
				result.setCurrency(target);
			}
			
		}else {
			result.setAmount(money.getAmount()*exchangeRate.getRate());
			result.setCurrency(target);
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
					if(!Objects.equals(c.trim(), source)) {
						ExchangeRate r;
						ExchangeRate request = this.repository.findExchangeRateByCurrency(String.format("%s,%s",record.getBase(), c.trim()));
						if(request!= null) {
							r = request;
						}else {
							r = new ExchangeRate();
							r.setBaseTarget(String.format("%s,%s",record.getBase(), c.trim()));
						}
						r.setDate(record.getDate());
						r.setRate(record.getRates().get(c.trim()));
					
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
