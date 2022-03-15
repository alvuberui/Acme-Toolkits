package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	// ------------------------------------ COMPONENTS GROUP BY CURRENCY AND TECHNOLOGY ---------------------------------------
	
	// Total de componentes 
	@Query("select count(c) from Component c")
	Integer getNumberOfComponents();
	
	// Media de "retail price" de los componentes agrupado por "currency" y "technology"
	@Query("select avg(c.retailPrice.amount) from Component c group by c.retailPrice.currency, c.technology")
	Double getAverageOfRetailpriceComponentGrupByCurrencyAndTechnology();
	
	// Desviacón de "retail price" de los componentes agrupados por "currency" y "technology"
	@Query("select stddev(c.retailPrice.amount) from Component c group by c.retailPrice.currency, c.technology")
	Double getDeviationOfRetailpriceComponentGrupByCurrencyAndTechnology();
	
	// Máximo de "reail price" de los componentes agrupados por "currency" y "technology"
	@Query("select max(c.retailPrice.amount) from Component c group by c.retailPrice.currency, c.technology")
	Double getMaxOfRetailpriceComponentGrupByCurrencyAndTechnology();
	
	// Mínimo de "reail price" de los componentes agrupados por "currency" y "technology"
	@Query("select min(c.retailPrice.amount) from Component c group by c.retailPrice.currency, c.technology")
	Double getMinOfRetailpriceComponentGrupByCurrencyAndTechnology();
	
	// ------------------------------------ TOOLS GROUP BY CURRENCY ---------------------------------------
	
	// Total de herramientas
	@Query("select count(t) from Tool t")
	Integer getNumberOfTools();
		
	// Media de "retail price" de las herramientas agrupado por "currency" 
	@Query("select avg(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Double getAverageOfRetailpriceCToolGrupByCurrency();
	
	// Desviacion de "retail price" de las herramientas agrupado por "currency" 
	@Query("select stddev(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Double getDeviationOfRetailpriceCToolGrupByCurrency();
	
	// Maximo de "retail price" de las herramientas agrupado por "currency"
	@Query("select max(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Double getMaxOfRetailpriceCToolGrupByCurrency();
	
	// Minimo de "retail price" de las herramientas agrupado por "currency"
	@Query("select min(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Double getMinOfRetailpriceCToolGrupByCurrency();
	
	// ------------------------------------- PATRONAGES ---------------------------------------
	
	
	// Total de Patronages Proposed
	@Query("select count(p) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED")
	Integer getNumberOfProposedPatronage();
	
	// Total de Patronages Acceptep
	@Query("select count(p) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED")
	Integer getNumberOfAcceptepPatronage();
		
	// Total de Patronages Denied
	@Query("select count(p) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.DENIED")
	Integer getNumberOfDeniedPatronage();

	
	
	// Average de Buget Patronages Proposed
	@Query("select avg(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED group by p.budget.currency")
	Integer getAverageBudgetOfProposedPatronage();
	
	// Average de Budget Patronages Acceptep
	@Query("select avg(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED group by p.budget.currency")
	Integer getAverageBudgetOfAcceptepPatronage();
			
	// Average de Budget Patronages Denied
	@Query("select avg(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.DENIED group by p.budget.currency")
	Integer getAverageBudgetOfDeniedPatronage();
	
	
	
	
	// Desviation de Buget Patronages Proposed
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED group by p.budget.currency")
	Integer getDesviationBudgetOfProposedPatronage();
		
	// Desviation de Budget Patronages Acceptep
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED group by p.budget.currency")
	Integer getDesviationBudgetOfAcceptepPatronage();
				
	// Desviation de Budget Patronages Denied
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.DENIED group by p.budget.currency")
	Integer getDesviationBudgetOfDeniedPatronage();
	
	
	
	// Minimo de Buget Patronages Proposed
	@Query("select min(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED group by p.budget.currency")
	Integer getMinBudgetOfProposedPatronage();
			
	// Minimo de Budget Patronages Acceptep
	@Query("select min(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED group by p.budget.currency")
	Integer getMinBudgetOfAcceptepPatronage();
					
	// Minimo de Budget Patronages Denied
	@Query("select min(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.DENIED group by p.budget.currency")
	Integer getMinBudgetOfDeniedPatronage();
	
	
	
	// Maximo de Buget Patronages Proposed
	@Query("select miax(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED group by p.budget.currency")
	Integer getMaxBudgetOfProposedPatronage();
			
	// Maximo de Budget Patronages Acceptep
	@Query("select min(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED group by p.budget.currency")
	Integer getMaxBudgetOfAcceptepPatronage();
					
	// Maximo de Budget Patronages Denied
	@Query("select min(p.budget.amount) from Patronage p where p.status = acme.entities.patonages.PatronageStatus.DENIED group by p.budget.currency")
	Integer getMaxBudgetOfDeniedPatronage();
	
	
	
	
}
