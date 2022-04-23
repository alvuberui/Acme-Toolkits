package acme.features.inventor.artefact;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorArtefactRepository extends AbstractRepository{
	
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);
	
	@Query ("select a from Artefact a where a.inventor.id = :inventorId")
	Collection<Artefact> findArtefactsFromInventor(int inventorId);

	@Query("select distinct a from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id")
	Collection<Artefact> findComponentsAndToolsByToolkitId(int id);
	
	@Query("select a.inventor.id from Artefact a where a.id = :id")
	Integer findInventorIdByArtefactId(int id);
	
	@Query("select distinct a.id from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id")
	List<Integer> findArtefactIdByToolkitId(int id);
}
