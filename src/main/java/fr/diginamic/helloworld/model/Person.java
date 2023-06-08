/**
 * person.java
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
import jakarta.persistence.Table;

/**
 * @author Krol
 *
 */

@Entity
@Table(name = "person")
public class Person {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String firstname;

    @Column(length = 50)
    private String lastname;

    private Integer age;
    
    @ManyToMany
    @JoinTable(name = "person_animals",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "animals_id"))
    private List<Animal> animals;

	/**Constructeur
	 *
	 */
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String firstname, String lastname, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
	}

	/**Getter age
	 * 
	 * @return Integer age
	 */
	public Integer getAge() {
		return age;
	}

	/** Setter age
	 * 
	 * @param age the age to set (type Integer)
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**Getter id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**Getter firstname
	 * 
	 * @return String firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**Getter lastname
	 * 
	 * @return String lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/** Setter id
	 * 
	 * @param id the id to set (type int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Setter firstname
	 * 
	 * @param firstname the firstname to set (type String)
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/** Setter lastname
	 * 
	 * @param lastname the lastname to set (type String)
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + "]";
    }

}
