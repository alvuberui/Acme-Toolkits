package acme.testing.inventor.toolkits;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitsHarness extends TestHarness{

	// Test cases -----------------------------
	
	
	public void createToolkit(String code, String title, String description, String AssemblyNotes, String link) {
		super.clickOnMenu("Inventor", "My toolkits");
		super.clickOnButton("Create toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", AssemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
	}

	public void updateFirstToolkit(String input, String value) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My toolkits");
		super.checkNotErrorsExist();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Update");
		super.fillInputBoxIn(input, value);
		super.clickOnSubmit("Update");

	}
	
	public void updateArtefactFirstToolkit(String quantityValue, String artefactValue) {
		super.clickOnMenu("Inventor", "My toolkits");
		super.checkNotErrorsExist();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Update artefacts");
		super.fillInputBoxIn("quantity", quantityValue);
		super.fillInputBoxIn("artefactId", artefactValue);
		super.clickOnSubmit("Update");
	}
	
	public String getIdArtefactComponentOrTool(Boolean component) {
		super.clickOnMenu("Anonymous", "Toolkit list");
		super.checkListingExists();
		if(component) {
			super.fillInputBoxIn("artefactName", "name03");
		}else {
			super.fillInputBoxIn("artefactName", "name04");
		}
		super.clickOnSubmit("Filter");
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Artefacts");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		return super.getCurrentQuery().replace("?id=", "");
	}
	
	
}
