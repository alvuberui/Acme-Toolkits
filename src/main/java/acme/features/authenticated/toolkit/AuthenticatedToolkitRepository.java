package acme.features.authenticated.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import acme.entities.artefact.Artefact;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AuthenticatedToolkitRepository extends AbstractRepository{
	
	
	@Query("select t from Toolkit t where t.id = :id and t.published=true")
	Toolkit findOneToolkitById(int id);
	
	@Query("select t from Toolkit t where t.published=true")
	Collection<Toolkit> findAllToolkit();
	
	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and t.id = :id and t.published=true and a.published=true")
	Collection<Artefact> findArtefactByToolkitId(int id);
	
	@Query("select distinct t from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and a.name like :artefactName and t.published=true")
	Collection<Toolkit> findAllArtefactByName(String artefactName);
	
	
	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfuration();
}
