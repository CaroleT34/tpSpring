package fr.diginamic.helloworld;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.helloworld.model.Animal;
import fr.diginamic.helloworld.model.Person;
import fr.diginamic.helloworld.model.Species;
import fr.diginamic.helloworld.repository.AnimalRepository;
import fr.diginamic.helloworld.repository.PersonRepository;
import fr.diginamic.helloworld.repository.SpeciesRepository;
import fr.diginamic.helloworld.service.BavardService;



@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner{
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@Autowired
    private BavardService bavardService;

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	
	@GetMapping("hello")
	public String hello() {
		return "Bonjour, monde !";
	}
	
	@GetMapping("blabla")
	public String blabla() {
	    bavardService.parler();
	    return "Default value or error message";
	    
	}

	@Override
	public void run(String... args) throws Exception {
		
		//TP3
		//Affiche la liste des animaux
		Iterable<Animal> animals = animalRepository.findAll();
		System.out.println("Liste des animaux :");
		for (Animal animal : animals) {
		    System.out.println(animal);
		}
		
		 //Créer quelques entités avec la méthode save
//		Species species1 = new Species("Lion", "Panthera leo");
//		Species species2 = new Species("Panthère noire", "Panthera pardus");
//		speciesRepository.save(species1);
//		speciesRepository.save(species2);
//		
//	    Animal animal1 = new Animal("Marron", "Léon", "M", 4);
//	    Animal animal2 = new Animal("Noir", "Bagheera", "F", 5);
//	    animalRepository.save(animal1);
//	    animalRepository.save(animal2);
	    
	    //Rechercher une entité par son id avec findById
//	    Optional<Person> optionalPerson = personRepository.findById(1);
//	    if (optionalPerson.isPresent()) {
//	        Person personById = optionalPerson.get();
//	        System.out.println("Personne avec ID 1 : " + personById);
//	    } else {
//	        System.out.println("Aucune personne trouvée avec l'ID 1");
//	    }
	    
	    //Supprimer une entité avec delete
//	    animals = animalRepository.findAll();
//	    System.out.println("Nombre d'animaux : " + animals.size());
//	    
//	    animalRepository.deleteById(7);
//	    animalRepository.deleteById(8);
//	    animals = animalRepository.findAll();
//	    System.out.println("Liste des animaux après suppression :");
//	    System.out.println("Nombre d'animaux : " + animals.size());
	    
		//TP4
		//SPECIES
		List<Species> test = this.speciesRepository.findByCommonName("Lion");
		System.out.println("test 1 : " + test);
		
		test = this.speciesRepository.findFirstByCommonNameContainingIgnoreCase("L");
		System.out.println("test 2 : " + test);
		
		test = this.speciesRepository.findByLatinNameContainsIgnoreCase("Panthera pardus");
		System.out.println("test 3 : " + test);
		
		//PERSON
		List<Person> test2 = this.personRepository.findByAgeGreaterThan(20);
		System.out.println("Il y a "+ test2.size() + " qui ont plus de 20ans");
		
		test2 = this.personRepository.findDistinctPeopleByLastnameOrFirstname("Lamarque", "Bill");
		System.out.println("test 5 : " + test2);
		
		//ANIMAL
		List<Animal> test3 = this.animalRepository.findAllAnimauxBySpeciesId(2);
		System.out.println("Les animaux ayant pour espèces l'id 2  : " + test3 );
		
		List<String> colorList = Arrays.asList("Noir", "Blanc");
		test3 = this.animalRepository.findAllAnimauxByColorIn(colorList);
		System.out.println("Liste de tous les animaux dont les couleurs sont noir ou blanc : " + test3);
		
		//TP5
		test = this.speciesRepository.findAllSpeciesOrderByCommonNameAsc();
		System.out.println("Liste de toutes les espèces : " + test);
		
		test = this.speciesRepository.findAllSpeciesWhereContainsParamCommonName("Ch");
		System.out.println("test 8 : " + test);
		
		test2 = this.personRepository.findAllPeopleByAgeIsBetweenAgeMinAndAgeMax(10, 25);
		System.out.println("Personnes ayant un age entre 10 et 25 ans : " + test2);
		
		test2 = this.personRepository.findAllPeopleByAnimalName("Max");
		System.out.println("Les esclaves de Max sont : " + test2);
		
		Boolean test4 = this.animalRepository.AnimalHasOwner(1);
		System.out.println("Animal avec l'Id 1 a t'il un propriétaire? : " + test4);
		
		Integer test5 = this.animalRepository.countAnimalsBySex("F");
		System.out.println("Il y a " + test5 + " animaux de sex féminin");
	}
	
	

}
