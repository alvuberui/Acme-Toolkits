package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitsCreateTest extends InventorToolkitsHarness{

	// Test cases -----------------------------
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolkitInventorTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.createToolkit(code, title, description, assemblyNotes, link);
		super.checkNotErrorsExist();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);

		super.checkButtonExists("Artefacts");
		super.checkButtonExists("Update artefacts");
		super.checkButtonExists("Update");
		super.checkSubmitExists("Delete");
		super.checkSubmitExists("Publish");
		
		super.clickOnButton("Artefacts");
		super.checkListingEmpty();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeToolkitInventorTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.createToolkit(code, title, description, assemblyNotes, link);
		super.checkErrorsExist("code");
		super.checkErrorsExist("title");
		super.checkErrorsExist("description");
		super.checkErrorsExist("assemblyNotes");
		super.checkErrorsExist("link");
	}
	
	
}
