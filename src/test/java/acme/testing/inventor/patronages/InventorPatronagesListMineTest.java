package acme.testing.inventor.patronages;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronagesListMineTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronages/list-patronages.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveArtefactInventorTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initPeriod", initPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("initPeriod", initPeriod);
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("company", company);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronages/list-patronages.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void NegativeArtefactListAnonymousTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		
		super.navigate("/inventor/patronages/list-mine");
		
		super.checkPanicExists();
	}
	

	@Order(40)
	public void NegativeArtefactListPatronTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		super.signIn("patron1", "patron1");
		
		super.navigate("/inventor/patronages/list-mine");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	

	@Order(50)
	public void NegativeArtefactListAdministratorTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/inventor/patronages/list-mine");
		
		super.checkPanicExists();
		
		super.signOut();
	}
	
	

	@Order(60)
	public void NegativeArtefactShowAnonymousTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
        super.clickOnListingRecord(recordIndex);
        final String query = super.getCurrentQuery();

        super.signOut();

      
        final String queryNueva = query.substring(1);
        super.navigate("/inventor/patronages/show",queryNueva);
        super.checkPanicExists();

		
		
		super.checkPanicExists();
	}
	

	@Order(70)
	public void NegativeArtefactShowInventorTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
        super.clickOnListingRecord(recordIndex);
        final String query = super.getCurrentQuery();

        super.signOut();

        super.signIn("inventor2", "inventor2");
        final String queryNueva = query.substring(1);
        super.navigate("/inventor/patronages/show",queryNueva);
        super.checkPanicExists();

		super.signOut();
	}
	

	@Order(80)
	public void NegativeArtefactShowPatronTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
        super.clickOnListingRecord(recordIndex);
        final String query = super.getCurrentQuery();

        super.signOut();

        super.signIn("patron2", "patron2");
        final String queryNueva = query.substring(1);
        super.navigate("/inventor/patronages/show",queryNueva);
        super.checkPanicExists();

		super.signOut();
	}

	@Order(90)
	public void NegativeArtefactShowAdministratorTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String initPeriod, final String finalPeriod, final String link, final String username, final String company) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
        super.clickOnListingRecord(recordIndex);
        final String query = super.getCurrentQuery();

        super.signOut();

        super.signIn("administrator", "administrator");
        final String queryNueva = query.substring(1);
        super.navigate("/inventor/patronages/show",queryNueva);
        super.checkPanicExists();

		super.signOut();
	}
	
	
	
}
