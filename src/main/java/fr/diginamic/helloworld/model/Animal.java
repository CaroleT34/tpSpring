/**
 * Animal.java
 */
package fr.diginamic.helloworld.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Krol
 *
 */

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String color;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String sex;

    @Column(name = "species_id")
    @ManyToOne
	private Species species;
    
    @ManyToMany
    @JoinTable(name = "person_animals",
    		joinColumns = @JoinColumn(name = "animals_id"),
    		inverseJoinColumns = @JoinColumn(name = "person_id")
            )
    private List<Animal> animals;

    /**Constructeur
	 *
	 */
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	 // Constructor with parameters
    public Animal(String color, String name, String sex, Species species) {
        this.color = color;
        this.name = name;
        this.sex = sex;
        this.species = species;
    }
	
	/**Getter id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**Getter color
	 * 
	 * @return String color
	 */
	public String getColor() {
		return color;
	}

	/**Getter name
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**Getter sex
	 * 
	 * @return String sex
	 */
	public String getSex() {
		return sex;
	}

	/** Setter id
	 * 
	 * @param id the id to set (type int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Setter color
	 * 
	 * @param color the color to set (type String)
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/** Setter name
	 * 
	 * @param name the name to set (type String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Setter sex
	 * 
	 * @param sex the sex to set (type String)
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	

	/**Getter species
	 * 
	 * @return Species species
	 */
	public Species getSpecies() {
		return species;
	}

	/**Getter animals
	 * 
	 * @return List<Animal> animals
	 */
	public List<Animal> getAnimals() {
		return animals;
	}

	/** Setter species
	 * 
	 * @param species the species to set (type Species)
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}

	/** Setter animals
	 * 
	 * @param animals the animals to set (type List<Animal>)
	 */
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", color=" + color + ", name=" + name + ", sex=" + sex + ", speciesId=" + species
				+ "]";
	}
}
