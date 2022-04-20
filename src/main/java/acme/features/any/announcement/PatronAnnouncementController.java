package acme.features.any.announcement;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.announcement.Announcement;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronAnnouncementController extends AbstractController<Patron, Announcement> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronAnnouncementListRecentAllService	announcementListAllService;
		
		@Autowired
		protected PatronAnnouncementShowService	showService;

	// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-announcements", "list", this.announcementListAllService);
			super.addCommand("show", this.showService);
		}
}
