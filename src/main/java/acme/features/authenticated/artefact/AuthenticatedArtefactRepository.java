package acme.features.authenticated.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedArtefactRepository extends AbstractRepository{
	
	@Query("select a from Artefact a where a.type = 0")
	Collection<Artefact> findManyComponents();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);

	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and a.type = acme.entities.artefact.ArtefactType.COMPONENT and t.id = :toolkitId")
	Collection<Artefact> findComponentsByToolkitId(int toolkitId);

}
