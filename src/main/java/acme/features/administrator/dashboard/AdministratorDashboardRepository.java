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
		
		
}
