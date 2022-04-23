package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListRecentTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-announcement.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAnnouncementInventorTest(final int recordIndex, final String creation, final String title, final String body, final String flag, final String url) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "Announcements list");
		super.checkListingExists();
		super.sortListing(0, "asc");


		super.checkColumnHasValue(recordIndex, 0, creation);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, flag);
		super.checkColumnHasValue(recordIndex, 4, url);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("url", url);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-announcement.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAnnouncementAdministratorTest(final int recordIndex, final String creation, final String title, final String body, final String flag, final String url) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Announcements list");
		super.checkListingExists();
		super.sortListing(0, "asc");


		super.checkColumnHasValue(recordIndex, 0, creation);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, flag);
		super.checkColumnHasValue(recordIndex, 4, url);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("url", url);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-announcement.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAnnouncementPatronTest(final int recordIndex, final String creation, final String title, final String body, final String flag, final String url) {

		super.signIn("patron1", "patron1");
		super.clickOnMenu("Authenticated", "Announcements list");
		super.checkListingExists();
		super.sortListing(0, "asc");


		super.checkColumnHasValue(recordIndex, 0, creation);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, flag);
		super.checkColumnHasValue(recordIndex, 4, url);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("url", url);
		
		super.signOut();

	}

	
	

	
}
