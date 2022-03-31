package acme.features.anonymous.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousComponentListAllService implements AbstractListService<Anonymous, Artefact>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousArtefactRepository repository;

		@Override
		public boolean authorise(final Request<Artefact> request) {
			assert request != null;

			return true;
		}

		@Override
		public Collection<Artefact> findMany(final Request<Artefact> request) {
			assert request != null;

			Collection<Artefact> result;

			result = this.repository.findManyComponents();

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