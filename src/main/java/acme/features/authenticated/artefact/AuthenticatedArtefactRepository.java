package acme.features.authenticated.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedArtefactRepository extends AbstractRepository{

	
	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and t.id = :toolkitId and a.type = acme.entities.artefact.ArtefactType.COMPONENT")
	Collection<Artefact> findComponentsByToolkitId(int toolkitId);


	@Query("select a from Artefact a, Quantity q, Toolkit t where q.artefact = a and q.toolkit=t and t.id = :toolkitId and a.type = acme.entities.artefact.ArtefactType.TOOL")
	Collection<Artefact> findToolsByToolkitId(int toolkitId);
}
