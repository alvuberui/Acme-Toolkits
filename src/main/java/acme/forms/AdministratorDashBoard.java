package acme.forms;

import java.io.Serializable;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class AdministratorDashBoard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer totalOfComponents;
	
	Map<Pair<String, String>, Double> averageRetailPriceOfComponentsByTechnologyAndCurrency;
	
	Map<Pair<String, String>, Double> desviationRetailPriceOfComponentsByTechnologyAndCurrency;

	Map<Pair<String, String>, Double> minimumRetailPriceOfComponentsByTechnologyAndCurrency;
	
	Map<Pair<String, String>, Double> maximumRetailPriceOfComponentsByTechnologyAndCurrency;
	
	Integer totalOfTools;
	
	Map<String, Double> averageRetailPriceOfToolsByCurrency;
	
	Map<String, Double> desviationRetailPriceOfToolsByCurrency;

	Map<String, Double> minimumRetailPriceOfToolsByCurrency;
	
	Map<String, Double> maximumRetailPriceOfToolsByCurrency;
	
	Map<String, Double> totalPratronagesByStatus;
	
	Map<String, Double> averageBudgetPratronagesByStatus;
	
	Map<String, Double> desviationBudgetPratronagesByStatus;
	
	Map<String, Double> minumumBudgetPratronagesByStatus;
	
	Map<String, Double> maximumBudgetPratronagesByStatus;
	
	
	
	
	
		
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------{	

}
