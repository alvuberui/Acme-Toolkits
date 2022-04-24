package acme.testing.authenticated.toolkit;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedToolkitShowTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShowToolkitPatronTest(final int recordIndex, final String title,final String code, final String description, final String assemblyNotes, final String link, final String price) {
	
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title",title);
		super.checkInputBoxHasValue("code",code);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("assemblyNotes",assemblyNotes);
		super.checkInputBoxHasValue("link",link);
		super.checkInputBoxHasValue("price",price);
	}
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveListArtefactToolkitPatronTest(final int recordIndex,final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {
	
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefact");
		super.clickOnButton("Artefact");
		super.checkListingExists();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit-artefact.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveArtefactOfToolkitTest(final int recordIndex,final int recordIndex2, final String type, final String name, final String code, final String techonology, final String description, final String retailPrice, final String moreInfo) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefact");
		super.clickOnButton("Artefact");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex2, 0, type);
		super.checkColumnHasValue(recordIndex2, 1, name);
		super.checkColumnHasValue(recordIndex2, 2, code);
		super.checkColumnHasValue(recordIndex2, 3, techonology);
		super.checkColumnHasValue(recordIndex2, 4, description);
		super.checkColumnHasValue(recordIndex2, 5, retailPrice);
		
		super.clickOnListingRecord(recordIndex2);

	}


	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void negativeShowToolkitTest(final int recordIndex, final String title,final String code, final String description, final String assemblyNotes, final String link, final String price) {
	
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		String pathAuthenticated = super.getCurrentPath();
		
		super.signOut();
		
		super.navigate(pathAuthenticated);
		
		super.checkPanicExists();
	}
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void negativeListArtefactToolkitTest(final int recordIndex,final String code, final String title, final String description, final String assemblyNotes, final String link, final String price) {
	
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefact");
		super.clickOnButton("Artefact");
		super.checkListingExists();
		
		String pathAuthenticated = super.getCurrentPath();
		
		super.signOut();
		
		super.navigate(pathAuthenticated);
		
		super.checkPanicExists();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit-artefact.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)
	public void negativeShowArtefactTest(final int recordIndex,final int recordIndex2, final String type, final String name, final String code, final String techonology, final String description, final String retailPrice, final String moreInfo) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefact");
		super.clickOnButton("Artefact");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex2);
		
		String pathAuthenticated = super.getCurrentPath();
		
		super.signOut();
		
		super.navigate(pathAuthenticated);
		
		super.checkPanicExists();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/toolkit/list-toolkit-artefact-not-published.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(60)
	public void negativeNotPublishedArtefactTest(final int recordIndex) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Toolkit list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefact");
		super.clickOnButton("Artefact");
		super.checkListingEmpty();
	}

	

	
	// Ancillary methods ------------------------------------------------------
}
