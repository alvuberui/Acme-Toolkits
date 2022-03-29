package acme.features.patron.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronArtefactRepository extends AbstractRepository{
	
	@Query("select a from Artefact a where a.type = 0")
	Collection<Artefact> findManyComponents();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);
	
	@Query("select a from Artefact a where a.type = 1")
	Collection<Artefact> findManyTools();
	

}
