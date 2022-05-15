package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitsPublishTest extends InventorToolkitsHarness{

	// Test cases -----------------------------
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePublishToolkitInventorTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.createToolkit(code, title, description, assemblyNotes, link);
		super.checkNotErrorsExist();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmit("Publish");
		super.checkSubmitExists("Publish");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeToolkitInventorTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My toolkit");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnButton("Update artefacts");
		super.fillInputBoxIn("quantity", "1");
		super.clickOnSubmit("Update");
		
		super.clickOnSubmit("Publish");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Artefacts");
		
		
		super.checkNotButtonExists("Update artefacts");
		super.checkNotButtonExists("Update");
		super.checkNotSubmitExists("Delete");
		super.checkNotSubmitExists("Publish");
		
		super.clickOnButton("Artefacts");
		super.checkNotListingEmpty();
		
	}
	
	

	
}
