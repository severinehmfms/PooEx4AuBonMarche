package fr.aubonmarche;

/**
 * Classe Customer : représente un client
 */ 

public class Customer {
	protected String name;
	protected String firstName;
	
	/**
	 * Constructeur
	 * @param namePerson
	 * @param surnamePerson
	 */
	public Customer(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
	}
		
	/**
	 * Méthode toString
	 */
	public String toString() {
		return this.name + " " + this.firstName;
	}

	/**
	 * Getters et Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
