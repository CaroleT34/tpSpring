/**
 * Species.java
 */
package fr.diginamic.helloworld.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "common_name", length = 50, nullable = false)
    private String commonName;

    @Column(name = "latin_name", length = 200, nullable = false)
    private String latinName;

    // Constructors, getters, and setters

    public Species() {
    }

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

	@Override
	public String toString() {
		return "Species [id=" + id + ", commonName=" + commonName + ", latinName=" + latinName + "]";
	}
    
}
