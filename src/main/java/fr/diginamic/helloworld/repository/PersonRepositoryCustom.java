/**
 * PersonRepositoryCustom.java
 */
package fr.diginamic.helloworld.repository;

/**
 * @author Krol
 *
 */
public interface PersonRepositoryCustom {

	public void deletePersonWithoutAnimal();
	
	public void addPerson(Integer nbPersonnes);
	
	public void addPersonWithAnimal (Integer nbPersonnes);
}
