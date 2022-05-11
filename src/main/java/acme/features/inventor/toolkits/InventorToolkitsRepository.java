package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.Quantity;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitsRepository extends AbstractRepository{

	
	@Query("select distinct t from Toolkit t join Quantity q on q.toolkit.id = t.id join Artefact a on a.id = q.artefact.id where a.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("select t from Toolkit t")
	Collection<Toolkit>  findAllToolkits();
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select a.inventor.id from Toolkit t join Quantity q on q.toolkit.id = t.id join Artefact a on a.id = q.artefact.id where t.id = :id")
	Integer findInventorIdByToolkitId(int id);
	
	@Query("select distinct a from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id")
	Collection<Artefact> artefactByToolkitId(int id);
	
	
	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfuration();
	
	@Query("Select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);

	@Query ("select a from Artefact a where a.inventor.id = :inventorId and a.published = true")
	Collection<Artefact> findArtefactsFromInventor(int inventorId);
	
	@Query("select distinct a from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :id")
	Collection<Artefact> findComponentsAndToolsByToolkitId(int id);
	
	@Query("select distinct q from Toolkit t join Quantity q on q.toolkit.id = t.id where t.id = :id")
	Collection<Quantity> findQuantityByToolkit(int id);
	
	@Query("select distinct q from Toolkit t join Quantity q on q.toolkit.id  = t.id join Artefact a on a.id = q.artefact.id where t.id  = :toolkitId and a.id = :artefactId")
	Quantity findQuantityByToolkitAndArtefact(int toolkitId, int artefactId);
}
