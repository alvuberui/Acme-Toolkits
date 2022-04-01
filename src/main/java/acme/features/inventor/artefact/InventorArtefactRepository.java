package acme.features.inventor.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorArtefactRepository extends AbstractRepository{
	
	@Query("select a from Artefact a where a.type = 0")
	Collection<Artefact> findManyComponents();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);

	@Query("select distinct a from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id and a.type = 0")
	Collection<Artefact> findComponentsByToolkitId(int id);
	
	@Query("select distinct a from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id and a.type = 1")
	Collection<Artefact> findToolsByToolkitId(int id);
}
