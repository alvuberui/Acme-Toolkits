package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
		
		super.signOut();
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
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("http://localhost:8080/acme-toolkits-0.1/inventor/patronage-report/create?id=33");
		super.checkPanicExists();
		
		
		super.signIn("inventor2", "inventor2");
		super.navigate("http://localhost:8080/acme-toolkits-0.1/inventor/patronage-report/create?id=33");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate("http://localhost:8080/acme-toolkits-0.1/inventor/patronage-report/create?id=33");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator", "administrator");
		super.navigate("http://localhost:8080/acme-toolkits-0.1/inventor/patronage-report/create?id=33");
		super.checkPanicExists();
		super.signOut();
		
		
	}
	
	
}
