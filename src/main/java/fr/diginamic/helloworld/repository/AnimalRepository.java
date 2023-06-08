/**
 * AnimalRepository.java
 */
package fr.diginamic.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.helloworld.model.Animal;

/**
 * @author Krol
 *
 */
@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer>{

	List<Animal> findAllAnimauxBySpeciesId(int species_id);
	
	List<Animal> findAllAnimauxByColorIn(List<String> colorList);
	
	 @Query(value = "SELECT Case When count(*)>0 then 'true' else 'false' end FROM person_animals WHERE animals_id = :animals_id", 
			 nativeQuery = true)
	  Boolean AnimalHasOwner(@Param("animals_id") Integer animals_id);
	 
	@Query(value = "SELECT COUNT(a) FROM Animal a where a.sex = :sex ")
	Integer countAnimalsBySex(@Param("sex")String sex);
}
