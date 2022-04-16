package acme.features.patron.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patonages.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result = new PatronDashboard();
		final Map<PatronageStatus, Integer> patronagesByStatus = new EnumMap<>(PatronageStatus.class);
		final Map<Pair<PatronageStatus, String>, Double> averageBudgetOfPatronage = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus, String>, Double> deviationBudgetOfPatronage = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus, String>, Double> minimumBudgetOfPatronage = new HashMap<Pair<PatronageStatus,String>, Double>();
		final Map<Pair<PatronageStatus, String>, Double> maximumBudgetOfPatronage = new HashMap<Pair<PatronageStatus,String>, Double>();

		final int numberOfProposedPatronages = this.repository.numberOfProposedPatronages();
		final int numberOfAcceptedPatronages = this.repository.numberOfAcceptedPatronages();
		final int numberOfDeniedPatronages = this.repository.numberOfDeniedPatronages();

		patronagesByStatus.put(PatronageStatus.PROPOSED, numberOfProposedPatronages);
		patronagesByStatus.put(PatronageStatus.ACCEPTED, numberOfAcceptedPatronages);
		patronagesByStatus.put(PatronageStatus.DENIED, numberOfDeniedPatronages);
		
		for(int i = 0; i<this.repository.averageBudgetOfPatronageByCurrencyAndStatus().size(); i++) {
			final String line = this.repository.averageBudgetOfPatronageByCurrencyAndStatus().get(i);
			final String[] separateLine = line.split(",");
			final Double average = Double.parseDouble(separateLine[2]);
			final String status = separateLine[0];
			final String currency = separateLine[1];
			final Pair<PatronageStatus,String> key = Pair.of(PatronageStatus.valueOf(status), currency);
			averageBudgetOfPatronage.put(key, average);
		}
		
		/*for(int i = 0; i<this.repository.deviationBudgetOfPatronageByCurrencyAndStatus().size(); i++) {
			final String line = this.repository.deviationBudgetOfPatronageByCurrencyAndStatus().get(i);
			final String[] separateLine = line.split(",");
			final Double average = Double.parseDouble(separateLine[2]);
			final String status = separateLine[0];
			final String currency = separateLine[1];
			final Pair<PatronageStatus,String> key = Pair.of(PatronageStatus.valueOf(status), currency);
			deviationBudgetOfPatronage.put(key, average);
		}
		
		for(int i = 0; i<this.repository.minimumBudgetOfPatronageByCurrencyAndStatus().size(); i++) {
			final String line = this.repository.minimumBudgetOfPatronageByCurrencyAndStatus().get(i);
			final String[] separateLine = line.split(",");
			final Double average = Double.parseDouble(separateLine[2]);
			final String status = separateLine[0];
			final String currency = separateLine[1];
			final Pair<PatronageStatus,String> key = Pair.of(PatronageStatus.valueOf(status), currency);
			minimumBudgetOfPatronage.put(key, average);
		}
		
		for(int i = 0; i<this.repository.maximumBudgetOfPatronageByCurrencyAndStatus().size(); i++) {
			final String line = this.repository.maximumBudgetOfPatronageByCurrencyAndStatus().get(i);
			final String[] separateLine = line.split(",");
			final Double average = Double.parseDouble(separateLine[2]);
			final String status = separateLine[0];
			final String currency = separateLine[1];
			final Pair<PatronageStatus,String> key = Pair.of(PatronageStatus.valueOf(status), currency);
			maximumBudgetOfPatronage.put(key, average);
		}
		result.setDeviationBudgetOfPatronageByCurrencyAndStatus(deviationBudgetOfPatronage);
		result.setMinimumBudgetOfPatronageByCurrencyAndStatus(minimumBudgetOfPatronage);
		result.setMinimumBudgetOfPatronageByCurrencyAndStatus(maximumBudgetOfPatronage);*/
		result.setAverageBudgetOfPatronageByCurrencyAndStatus(averageBudgetOfPatronage);
		result.setNumberOfPatronageByStatus(patronagesByStatus);
		return result;
	}
	
	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfPatronageByStatus","averageBudgetOfPatronageByCurrencyAndStatus");
	}
	
}
