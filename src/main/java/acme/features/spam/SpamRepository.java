package acme.features.spam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamRepository extends AbstractRepository{
	
	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfuration();
}
