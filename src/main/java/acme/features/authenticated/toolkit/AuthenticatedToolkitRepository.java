package acme.features.authenticated.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AuthenticatedToolkitRepository extends AbstractRepository{
	
	
	@Query("select t from Toolkit t where t.id = :id and t.published=true")
	Toolkit findOneToolkitById(int id);
	
	@Query("select t from Toolkit t where t.published=true")
	Collection<Toolkit> findAllToolkit();
	
	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and a.type = acme.entities.artefact.ArtefactType.COMPONENT and t.id = :id  and t.published=true")
	Collection<Artefact> findComponentsByToolkitId(int id);
	
	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and a.type = acme.entities.artefact.ArtefactType.TOOL and t.id = :id  and t.published=true")
	Artefact findToolByToolkitId(int id);
	
	@Query("select distinct t from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and a.name like :artefactName and t.published=true")
	Collection<Toolkit> findAllArtefactByName(String artefactName);
	
}
