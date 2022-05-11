package acme.components;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.ExchangeRate;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ExchangeRepository extends AbstractRepository {
	
	@Query("Select er from ExchangeRate er where er.target like :currency and er.base like :base")
	ExchangeRate findExchangeRateByCurrency(String currency, String base);
	
	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfuration();
}
