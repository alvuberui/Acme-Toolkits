package acme.features.inventor.patronages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patonages.Patronages;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronagesRepository  extends AbstractRepository {
		
	@Query("select p from Patronages p where p.inventor.id  = :inventorId")
	Collection<Patronages> findManyPatronagesByInventorId(int inventorId);
	
	
	@Query("Select p from Patronages p where p.id = :patronageId")
	Patronages findPatronagesById(int patronageId);

}
