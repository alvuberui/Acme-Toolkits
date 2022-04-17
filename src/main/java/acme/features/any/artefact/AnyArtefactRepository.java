package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtefactRepository extends AbstractRepository {
	
	
	@Query("select a from Artefact a where a.type = :artefactType")
	Collection<Artefact> findManyArtifacts(ArtefactType artefactType);

}
