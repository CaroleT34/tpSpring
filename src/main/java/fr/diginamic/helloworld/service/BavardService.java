/**
 * BavardService.java
 */
package fr.diginamic.helloworld.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

/**
 * @author Krol
 *
 */

@Component
@Service
public class BavardService {

	 private String nom = "BavardService";

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }
	    
	    public void parler() {
	        System.out.println("Nom : " + nom);
	        System.out.println("Classe : " + this.getClass().getSimpleName());
	    }
	    
	    @PostConstruct
	    private void postConstruct() {
	        System.out.println("Méthode postConstruct exécutée.");
	    }

}
