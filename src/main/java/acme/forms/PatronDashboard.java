package acme.forms;

import java.io.Serializable;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import acme.entities.patonages.PatronageStatus;

public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Map<String, Integer>		numberOfPatronage;
	
	Map<Pair<PatronageStatus, String>, Double> averageBudgetOfPatronage;

	Map<Pair<PatronageStatus, String>, Double> DeviationBudgetOfPatronage;

	Map<Pair<PatronageStatus, String>, Double> minimumBudgetOfPatronage;

	Map<Pair<PatronageStatus, String>, Double> maximumBudgetOfPatronage;

	
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------{

}
