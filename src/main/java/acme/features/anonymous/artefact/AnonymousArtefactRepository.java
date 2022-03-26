package acme.features.anonymous.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousArtefactRepository extends AbstractRepository{
	
	@Query("select a from Artefact a where a.type = 0")
	Collection<Artefact> findManyComponents();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);
}
