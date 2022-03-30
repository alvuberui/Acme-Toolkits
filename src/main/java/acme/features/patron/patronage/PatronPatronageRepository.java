package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patonages.Patronages;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("select p from Patronages p")
	Collection<Patronages> findAllPatronages();
	
	
	@Query("select p from Patronages p where p.id =?1")
	Patronages findOneById(int id);

}
