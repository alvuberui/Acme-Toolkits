package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patonages.Patronages;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	
	@Query("select p from Patronages p where p.id = :id")
	Patronages findOneById(int id);

	@Query("select p from Patronages p where p.patron.id = :id")
	Collection<Patronages> findOwnPatronages(int id);

	@Query("Select p from Patron p where p.userAccount.id = :id")
	Patron findPatronByUserAccountId(int id);

}
