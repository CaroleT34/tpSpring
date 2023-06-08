/**
 * SpeciesController.java
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

import fr.diginamic.helloworld.model.Species;
import fr.diginamic.helloworld.repository.SpeciesRepository;
import jakarta.validation.Valid;

/**
 * @author Krol
 *
 */
@Controller
public class SpeciesController {
	
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("species")
	public String getListSpecies(Model model) {
		
		Iterable<Species> speciesList = speciesRepository.findAll();
		model.addAttribute("speciesList", speciesList);
		
		return "species/list_species";
	}

	@GetMapping("species/{id}")
	public String getDetailSpecies(@PathVariable("id") Integer id, Model model) {
		Optional<Species> speciesOpt = speciesRepository.findById(id);

		if (speciesOpt.isEmpty()) {
			return "error";
		}

		model.addAttribute("species", speciesOpt.get());

		return "species/detail_species";
	}

	@GetMapping("species/create")
	public String getCreateSpecies(Model model) {

		model.addAttribute("species", new Species());

		return "species/create_species";
	}
	
	@PostMapping("/species")
	public String createOrUpdate(@Valid Species species, BindingResult result, Model model) {
		if (result.hasErrors()) {
			if (species.getId() != 0) {
				return "species/detail_species";
			}
			return "species/create_species";
		}
		speciesRepository.save(species);
		return "redirect:/species";
	}

	@GetMapping("/species/delete/{id}")
	public String deleteSpecies(@PathVariable("id") Integer speciesId) {
		speciesRepository.deleteById(speciesId);
		return "redirect:/species";
	}
	

}
