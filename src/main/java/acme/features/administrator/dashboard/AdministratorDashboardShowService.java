package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		return true;
	}


	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		final AdministratorDashboard result=new AdministratorDashboard();
		final int NumberOfProposedPatronages=this.repository.NumberOfProposedPatronages();
		final int NumberOfAcceptedPatronages=this.repository.NumberOfAcceptedPatronages();
		final int NumberOfDeniedPatronages=this.repository.NumberOfDeniedPatronages();	
		final int NumberOfTools= this.repository.NumberOfTools();
		final int NumberOfComponents= this.repository.NumberOfComponents();
		final Map<Pair<String, String>, Double> avgRetailPriceOfComponents =  new HashMap<Pair<String, String>, Double>();	
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponents =  new HashMap<Pair<String, String>, Double>();		
		final Map<Pair<String, String>, Double> minRetailPriceOfComponents =  new HashMap<Pair<String, String>, Double>();		
		final Map<Pair<String, String>, Double> maxRetailPriceOfComponents =  new HashMap<Pair<String, String>, Double>();		
		final Map<String,Double> avgRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> deviationRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> minRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> maxRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> avgBudgetByStatus=new HashMap<String, Double>();
		final Map<String,Double> deviationBudgetByStatus=new HashMap<String, Double>();
		final Map<String,Double> minBudgetByStatus=new HashMap<String, Double>();
		final Map<String,Double> maxBudgetByStatus=new HashMap<String, Double>();

		int i=0;
		
		while(i<this.repository.deviationRetailPriceOfComponents().size()) {
			final String linea= this.repository.deviationRetailPriceOfComponents().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			deviationRetailPriceOfComponents.put(res, key);
			i++;
		}
		i=0;
		
		
		
		while(i<this.repository.avgRetailPriceOfComponents().size()) {
			final String linea= this.repository.avgRetailPriceOfComponents().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			avgRetailPriceOfComponents.put(res, key);
			i++;
		}
		i=0;
		while(i<this.repository.minRetailPriceOfComponents().size()) {
			final String linea= this.repository.minRetailPriceOfComponents().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			minRetailPriceOfComponents.put(res, key);
			i++;
		}
		i=0;
		while(i<this.repository.avgRetailPriceOfTools().size()) {
			final String linea= this.repository.avgRetailPriceOfTools().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			avgRetailPriceOfTools.put(divisa, key);
			i++;
		}
		
		i=0;
		while(i<this.repository.maxRetailPriceOfComponents().size()) {
			final String linea= this.repository.maxRetailPriceOfComponents().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			maxRetailPriceOfComponents.put(res, key);
			i++;
		 }
		
		i=0;
		while(i<this.repository.deviationRetailPriceOfTools().size()) {
			final String linea= this.repository.deviationRetailPriceOfTools().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			deviationRetailPriceOfTools.put(divisa, key);
			i++;
		 }
		i=0;
		
		while(i<this.repository.minRetailPriceOfTools().size()) {
			final String linea= this.repository.minRetailPriceOfTools().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			minRetailPriceOfTools.put(divisa, key);
			i++;
		 }
		i=0;
		
		while(i<this.repository.maxRetailPriceOfTools().size()) {
			final String linea= this.repository.maxRetailPriceOfTools().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			maxRetailPriceOfTools.put(divisa, key);
			i++;
		}
		i=0;
		while(i<this.repository.deviationBudgetByStatus().size()) {
			final String linea= this.repository.deviationBudgetByStatus().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			deviationBudgetByStatus.put(status, key);
			i++;
		 }
		
		 
		i=0;
		while(i<this.repository.avgBudgetByStatus().size()) {
			final String linea= this.repository.avgBudgetByStatus().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			avgBudgetByStatus.put(status, key);
			i++;
		}
		i=0;
		while(i<this.repository.maxBudgetByStatus().size()) {
			final String linea= this.repository.maxBudgetByStatus().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			maxBudgetByStatus.put(status, key);
			i++;
		
		
		 }
		i=0;
		while(i<this.repository.minBudgetByStatus().size()) {
			final String linea= this.repository.minBudgetByStatus().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			minBudgetByStatus.put(status, key);
			i++;
		}
		
		result.setAvgBudgetByStatus(avgBudgetByStatus);
		result.setDeviationBudgetByStatus(deviationBudgetByStatus);
		result.setMaxBudgetByStatus(maxBudgetByStatus);
		result.setMinBudgetByStatus(minBudgetByStatus);
		
		result.setNumberOfTools(NumberOfTools);
		result.setAvgRetailPriceOfTools(avgRetailPriceOfTools);
		result.setDeviationRetailPriceOfTools(deviationRetailPriceOfTools);
		result.setMinRetailPriceOfTools(minRetailPriceOfTools);
		result.setMaxRetailPriceOfTools(maxRetailPriceOfTools);
		
		result.setNumberOfComponents(NumberOfComponents);
		result.setAvgRetailPriceOfComponents(avgRetailPriceOfComponents);
		result.setDeviationRetailPriceOfComponents(deviationRetailPriceOfComponents);
		result.setMaxRetailPriceOfComponents(maxRetailPriceOfComponents);
		result.setMinRetailPriceOfComponents(minRetailPriceOfComponents);
		
		result.setNumberOfAcceptedPatronages(NumberOfAcceptedPatronages);
		result.setNumberOfProposedPatronages(NumberOfProposedPatronages);
		result.setNumberOfDeniedPatronages(NumberOfDeniedPatronages);	
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "NumberOfProposedPatronages", "NumberOfAcceptedPatronages", "NumberOfDeniedPatronages", 
			"NumberOfComponents", "avgRetailPriceOfComponents", "deviationRetailPriceOfComponents", "minRetailPriceOfComponents","maxRetailPriceOfComponents",
			"NumberOfTools","avgRetailPriceOfTools","deviationRetailPriceOfTools","minRetailPriceOfTools","maxRetailPriceOfTools","avgBudgetByStatus","deviationBudgetByStatus","maxBudgetByStatus","minBudgetByStatus");
	}}

