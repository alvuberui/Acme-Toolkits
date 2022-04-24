package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtefactRepository extends AbstractRepository {
	
	
	@Query("select a from Artefact a where a.published = true")
	Collection<Artefact> findManyArtifacts();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);
	

	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and t.id = :toolkitId and a.published = true")
	Collection<Artefact> findToolsAndComponetsByToolkitId(int toolkitId);

	@Query("select a from Artefact a where a.id = :id and a.published = true")
	Artefact findArtefactPublishedById(int id);
	
}
