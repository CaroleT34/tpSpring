/**
 * SpeciesRepository.java
 */
package fr.diginamic.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.helloworld.model.Species;

/**
 * @author Krol
 *
 */
@Repository
public interface SpeciesRepository extends CrudRepository<Species, Integer>{
	
	  List<Species> findByCommonName(String commonName);

	  List<Species> findFirstByCommonNameContainingIgnoreCase(String commonName);

	  List<Species> findByLatinNameContainsIgnoreCase(String latinName);
	  
	  //Liste toutes les espèces par nom commun ascendant
	  @Query("FROM Species ORDER BY commonName ASC")
	  List<Species> findAllSpeciesOrderByCommonNameAsc();
	  
	  @Query("FROM Species WHERE commonName Like %:commonName%")
	  List<Species> findAllSpeciesWhereContainsParamCommonName(@Param("commonName") String commonName);

}
