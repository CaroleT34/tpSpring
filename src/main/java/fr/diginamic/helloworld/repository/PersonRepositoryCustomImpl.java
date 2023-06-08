/**
 * PersonRepositoryCustomImpl.java
 */
package fr.diginamic.helloworld.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import fr.diginamic.helloworld.model.Animal;
import fr.diginamic.helloworld.model.Person;
import fr.diginamic.helloworld.model.Species;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * @author Krol
 *
 */
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void deletePersonWithoutAnimal() {

		//Recupere les personnes sans animaux
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.animals IS EMPTY", Person.class);
		List<Person> persons = query.getResultList();
		System.out.println("Nombre de personnes sans animaux : " + persons.size());

		//Supprime les personnes sans animaux
		persons.forEach(p -> {
			System.out.println(p.getFirstname() + " " + p.getLastname() + " est supprimé de la base");
			em.remove(p);
		});

	}

	@Override
	@Transactional
	public void addPerson(Integer nbPersonnes) {

		//Génére des noms aléatoire
		Faker faker = new Faker();

		for (int i = 0; i < nbPersonnes; i++) {
			Person person = new Person();
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			em.persist(person);
		}

		System.out.println("Nombre de personnes ajouté : " + nbPersonnes);

	}
	
	
	//(en plus du TP 06)
	@Override
	@Transactional
	public void addPersonWithAnimal(Integer nbPersonnes) {

		//Génére des noms aléatoire
		Faker faker = new Faker();

		for (int i = 0; i < nbPersonnes; i++) {
			Person person = new Person();
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			
	        // Crée un nouvel animal
	        Animal animal = new Animal();
	        animal.setName(faker.animal().name());
	        
	        // Assigner un sexe à l'animal
	        Random random = new Random();
	        if (random.nextInt(2) == 0) {
	            animal.setSex("F");
	        } else {
	            animal.setSex("M");
	        }
	        //em.persist(animal);

	        // Ajoute l'animal à la liste des animaux de la personne
	        List<Animal> animals = new ArrayList<>();
	        animals.add(animal);
	        person.setAnimals(animals);
	        
	        // Crée une nouvelle espèce
	        Species species = new Species();
	        species.setCommonName("TestCommun"); 
	        species.setLatinName("TestLatin"); 

	        // Sauvegardez l'espèce
	        em.persist(species);

	        // Assigner l'espèce à l'animal
	        animal.setSpecies(species);

	        // Sauvegardez l'animal
	        em.persist(animal);

	        em.persist(person);
	    }
		System.out.println("Nombre de personnes avec un animal ajouté : " + nbPersonnes);

	}

}
