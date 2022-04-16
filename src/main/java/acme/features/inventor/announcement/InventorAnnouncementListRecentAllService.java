package acme.features.inventor.announcement;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorAnnouncementListRecentAllService implements AbstractListService<Inventor, Announcement>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorAnnouncementRepository repository;

		@Override
		public boolean authorise(final Request<Announcement> request) {
			assert request != null;

			return true;
		}

		@Override
		public Collection<Announcement> findMany(final Request<Announcement> request) {
			assert request != null;

			Collection<Announcement> result;
			Calendar calendar;
			Date deadline;
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			deadline= calendar.getTime();
			
			result = this.repository.findRecentAnnouncements(deadline);

			return result;
		}

		@Override
		public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "creation", "title", "body", "flag",
				"url");
			
		}
}
