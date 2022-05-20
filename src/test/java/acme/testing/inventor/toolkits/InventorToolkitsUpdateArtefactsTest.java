package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitsUpdateArtefactsTest extends InventorToolkitsHarness{

	// Test cases -----------------------------
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/Toolkits/create-toolkits.csv", encoding = "utf-8", numLinesToSkip = 2)
	@Order(10)
	public void createToolkit(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.createToolkit(code, title, description, assemblyNotes, link);
		super.checkNotErrorsExist();
	}

	@Test
	@Order(20)
	public void positiveUpdateArtefactToolkitInventorTest() {
		String componentId  = super.getIdArtefactComponentOrTool(true);
		String toolId  = super.getIdArtefactComponentOrTool(false);
		super.signIn("inventor1", "inventor1");
		super.updateArtefactFirstToolkit("1",componentId);
		super.updateArtefactFirstToolkit("2",toolId);
		super.clickOnButton("Artefacts");
		super.sortListing(1,"asc");
		super.checkColumnHasValue(0, 7, "1");
		super.checkColumnHasValue(1, 7, "2");
	}
	
	@Test
	@Order(30)
	public void positiveDeleteArtefactToolkitInventorTest() {
		String componentId  = super.getIdArtefactComponentOrTool(true);
		String toolId  = super.getIdArtefactComponentOrTool(false);
		super.signIn("inventor1", "inventor1");
		super.updateArtefactFirstToolkit("0",componentId);
		super.updateArtefactFirstToolkit("0",toolId);
		super.clickOnButton("Artefacts");
		super.checkListingEmpty();
	}
}
