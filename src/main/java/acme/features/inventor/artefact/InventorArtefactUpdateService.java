package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactUpdateService implements AbstractUpdateService<Inventor, Artefact> {
	
	@Autowired
	protected InventorArtefactRepository repository;
	
	// AbstractUpdateService<Employer, Job> -------------------------------------


		@Override
		public boolean authorise(final Request<Artefact> request) {
			assert request != null;

			boolean result;
			int masterId;
			Artefact artefact;
			final Inventor inventor;

			masterId = request.getModel().getInteger("id");
			artefact = this.repository.findArtefactById(masterId);
			inventor = artefact.getInventor();
			result = !artefact.isPublished() && request.isPrincipal(inventor);

			return result;
		}

		@Override
		public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if (!errors.hasErrors("code")) {
				Artefact existing;
				
				existing = this.repository.findOneByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.artefact.error.duplicated");
			}
			
			if (!errors.hasErrors("retailPrice")) {
				errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.artefact.form.error.negative-salary");
			}
			

		}

		@Override
		public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors,  "type", "name", "code", "technology", "description", "retailPrice", "moreInfo");
		}

		@Override
		public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "moreInfo", "published");
		}

		@Override
		public Artefact findOne(final Request<Artefact> request) {
			assert request != null;

			Artefact result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findArtefactById(id);

			return result;
		}

		@Override
		public void update(final Request<Artefact> request, final Artefact entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}