package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitsRepository extends AbstractRepository{

	
	@Query("select t from Toolkit t join Quantity q on q.toolkit.id = t.id join Artefact a on a.id = q.artefact.id where a.inventor.id = :id")
	Collection<Toolkit> findToolkits(int id);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select a.retailPrice from Toolkit t join Quantity q on q.toolkit.id = t.id join Artefact a on a.id = q.artefact.id where t.id = :id")
	Money findPrice(int id);
	
	@Query("select a.inventor.id from Toolkit t join Quantity q on q.toolkit.id = t.id join Artefact a on a.id = q.artefact.id where t.id = :id")
	Integer findInventorIdByToolkitId(int id);
	

}
