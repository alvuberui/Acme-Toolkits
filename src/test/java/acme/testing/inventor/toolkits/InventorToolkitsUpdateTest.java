package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class InventorToolkitsUpdateTest extends InventorToolkitsHarness{

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
		super.checkButtonExists("Update");
		super.clickOnButton("Update");
		
		String codeUpdate = code.replace("000", "001");
		String titleUpdate = "titleUpdate";
		String descriptionUpdate = "descriptionUpdate";
		String assemblyNoteUpdate = "assemblyNoteUpdate";
		String linkUpdate = link.replace("google","linkUpdate");
		

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", titleUpdate);
		super.fillInputBoxIn("description", descriptionUpdate);
		super.fillInputBoxIn("assemblyNotes", assemblyNoteUpdate);
		super.fillInputBoxIn("link", linkUpdate);
		
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", titleUpdate);
		super.checkInputBoxHasValue("description", descriptionUpdate);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNoteUpdate);
		super.checkInputBoxHasValue("link", linkUpdate);
		
		super.clickOnButton("Update");
		super.fillInputBoxIn("code", codeUpdate);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		super.checkInputBoxHasValue("code", codeUpdate);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeToolkitInventorTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My toolkit");
		super.checkNotErrorsExist();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.clickOnButton("Update");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist("code");
		super.checkErrorsExist("title");
		super.checkErrorsExist("description");
		super.checkErrorsExist("assemblyNotes"); 
		super.checkErrorsExist("link");
	}
	
	

	
}
