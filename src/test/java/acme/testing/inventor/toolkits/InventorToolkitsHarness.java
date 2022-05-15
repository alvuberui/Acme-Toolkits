package acme.testing.inventor.toolkits;


import acme.testing.TestHarness;

public class InventorToolkitsHarness extends TestHarness{

	// Test cases -----------------------------
	
	
	public void createToolkit(String code, String title, String description, String AssemblyNotes, String link) {
		super.clickOnMenu("Inventor", "My toolkit");
		super.clickOnButton("Create toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", AssemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
	}

	
}
