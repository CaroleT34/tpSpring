/**
 * PersonController.java
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
import fr.diginamic.helloworld.repository.PersonRepository;
import jakarta.validation.Valid;

/**
 * @author Krol
 *
 */
@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@GetMapping("person")
	public String getListPerson(Model model) {

		Iterable<Person> personList = personRepository.findAll();
		model.addAttribute("personList", personList);

		return "person/list_person";
	}

	@GetMapping("person/{id}")
	public String getDetailPerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> personOpt = personRepository.findById(id);

		if (personOpt.isEmpty()) {
			return "error";
		}

		model.addAttribute("person", personOpt.get());
		model.addAttribute("animalList", animalRepository.findAll());

		return "person/detail_person";
	}

	@GetMapping("person/create")
	public String getCreatePerson(Model model) {

		model.addAttribute("person", new Person());
		model.addAttribute("animalList", animalRepository.findAll());

		return "person/create_person";
	}

	@PostMapping("/person")
	public String createOrUpdate(@Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("animalList", animalRepository.findAll());
			if (person.getId() != 0) {
				return "person/detail_person";
			}
			return "person/create_person";
		}
		personRepository.save(person);
		return "redirect:/person";
	}

	@GetMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer personId) {
		Optional<Person> personToDeleteOpt = personRepository.findById(personId);

		if (personToDeleteOpt.isPresent()) {
			Person personToDelete = personToDeleteOpt.get();

			for (Animal animal : personToDelete.getAnimals()) {
				animal.getPerson().remove(personToDelete);
			}

			personRepository.delete(personToDelete);
		}

		return "redirect:/person";
	}
}
