/**
 * PersonRepository.java
 */
package fr.diginamic.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.helloworld.model.Person;

/**
 * @author Krol
 *
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{

	//Liste des personnes dont l'âge est supérieur au param "age"
	List<Person> findByAgeGreaterThan(int age);
	
	//Liste des personnes ayant pour nom le premier paramètre ou ayant pour prénom le second paramètre
	List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);

	//Liste des personnes dont l’âge est entre « ageMin » et « ageMax »
	@Query("FROM Person Where age BETWEEN :ageMin AND :ageMax")
	List<Person> findAllPeopleByAgeIsBetweenAgeMinAndAgeMax(@Param("ageMin")int ageMin, @Param("ageMax")int ageMax);

	//Liste des propriétaires de l'animal donné en paramètre
	@Query("FROM Person p JOIN p.animals a WHERE a.name = :nameAnimal")
	List<Person> findAllPeopleByAnimalName(@Param("nameAnimal")String nameAnimal);

}
