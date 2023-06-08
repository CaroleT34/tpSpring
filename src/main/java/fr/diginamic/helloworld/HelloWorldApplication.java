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
		
	}
	
	

}
