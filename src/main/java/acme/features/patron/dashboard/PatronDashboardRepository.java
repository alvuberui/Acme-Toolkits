package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface PatronDashboardRepository extends AbstractRepository{

	@Query("select count(p) from Patronages p where p.status = 0")//acme.entities.patronages.PatronageStatus.PROPOSED
	int numberOfProposedPatronages();
	
	@Query("select count(p) from Patronages p where p.status = 1")//acme.entities.patronages.PatronageStatus.ACCEPTED
	int numberOfAcceptedPatronages();
	
	@Query("select count(p) from Patronages p where p.status = 2")//acme.entities.patronages.PatronageStatus.DENIED
	int numberOfDeniedPatronages();
	
	@Query("select p.status, p.budget.currency, avg(p.budget.amount) from Patronages p group by p.budget.currency, p.status")
	List<String> averageBudgetOfPatronageByCurrencyAndStatus();
	
	@Query("select p.status, p.budget.currency, stddev(p.budget.amount) from Patronages p group by p.budget.currency, p.status")
	List<String> deviationBudgetOfPatronageByCurrencyAndStatus();
	
	@Query("select p.status, p.budget.currency, min(p.budget.amount) from Patronages p group by p.budget.currency, p.status")
	List<String> minimumBudgetOfPatronageByCurrencyAndStatus();
	
	@Query("select p.status, p.budget.currency, max(p.budget.amount) from Patronages p group by p.budget.currency, p.status")
	List<String> maximumBudgetOfPatronageByCurrencyAndStatus();


}
