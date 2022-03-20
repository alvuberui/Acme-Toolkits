package acme.forms;

import java.io.Serializable;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import acme.entities.patonages.PatronageStatus;

public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Map<String, Integer>		numberOfPatronageByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> averageBudgetOfPatronageByCurrencyAndStatus;

	Map<Pair<PatronageStatus, String>, Double> deviationBudgetOfPatronageByCurrencyAndStatus;

	Map<Pair<PatronageStatus, String>, Double> minimumBudgetOfPatronageByCurrencyAndStatus;

	Map<Pair<PatronageStatus, String>, Double> maximumBudgetOfPatronageByCurrencyAndStatus;

	
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------{

}
