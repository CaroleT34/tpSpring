/**
 * AnimalController.java
 */
package fr.diginamic.helloworld.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.helloworld.model.Animal;
import fr.diginamic.helloworld.model.Person;
import fr.diginamic.helloworld.repository.AnimalRepository;
import fr.diginamic.helloworld.repository.SpeciesRepository;

import jakarta.validation.Valid;

/**
 * @author Krol
 *
 */
@Controller
public class AnimalController {
	
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("animal")
	public String getListAnimal(Model model) {

		Iterable<Animal> animalList = animalRepository.findAll();
		model.addAttribute("animalList", animalList);

		return "animal/list_animal";
	}

	@GetMapping("animal/{id}")
	public String getDetailAnimal(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animalOpt = animalRepository.findById(id);

		if (animalOpt.isEmpty()) {
			return "error";
		}

		model.addAttribute("animal", animalOpt.get());
		model.addAttribute("speciesList", speciesRepository.findAll());

		return "animal/detail_animal";
	}

	@GetMapping("animal/create")
	public String getCreateAnimal(Model model) {

		model.addAttribute("animal", new Animal());
		model.addAttribute("speciesList", speciesRepository.findAll());
		return "animal/create_animal";
	}

	@PostMapping("/animal")
	public String createOrUpdate(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("speciesList", speciesRepository.findAll());
			if (animal.getId() != 0) {
				return "animal/detail_animal";
			}
			return "animal/create_animal";
		}
		animalRepository.save(animal);
		return "redirect:/animal";
	}

	@GetMapping("/animal/delete/{id}")
	public String deleteAnimal(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDeleteOpt = animalRepository.findById(animalId);

		if (animalToDeleteOpt.isPresent()) {
			Animal animalToDelete = animalToDeleteOpt.get();

			for (Person person : animalToDelete.getPerson()) {
				person.getAnimals().remove(animalToDelete);
			}

			animalRepository.delete(animalToDelete);
		}

		return "redirect:/animal";
	}

}
