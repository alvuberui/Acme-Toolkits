package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository{
	
	@Query("select a from Announcement a")
	Collection<Announcement> findAllAnnouncements();
	
	@Query("select a from Announcement a where a.creation > :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("select a from Announcement a where a.id = :id")
	Announcement findAnnouncementById(int id);
}
