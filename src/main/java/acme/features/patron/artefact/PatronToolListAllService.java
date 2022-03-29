package acme.features.patron.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronToolListAllService implements AbstractListService<Patron, Artefact>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronArtefactRepository repository;

		@Override
		public boolean authorise(final Request<Artefact> request) {
			assert request != null;

			return true;
		}

		@Override
		public Collection<Artefact> findMany(final Request<Artefact> request) {
			assert request != null;

			Collection<Artefact> result;

			result = this.repository.findManyTools();

			return result;
		}

		@Override
		public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "type", "name", "code", "technology",
				"description","retailPrice", "moreInfo");
			
		}
}
