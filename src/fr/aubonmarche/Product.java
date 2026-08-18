package fr.aubonmarche;

import java.time.LocalDate;

/**
 * Classe abstraite Product
 */

public abstract class Product {
	
	protected String name;
	protected double unitPrice;
	protected String unite;
	protected double stockQuantity;
	protected LocalDate pickingDate;	//Date de cueillette
	protected int shelfLifeDays;		//Durée maximale de conservation en jours
	
	/**
	 * Constructeur du produit sans les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 */
	public Product(String name, double unitPrice, String unite, double stockQuantity) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.unite = unite;
		this.stockQuantity = stockQuantity;
	}
	
	/**
	 * Constructeur du produit avec les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 * @param pickingDate
	 * @param shelfLifeDays
	 */
	public Product(String name, double unitPrice, String unite, double stockQuantity, LocalDate pickingDate, int shelfLifeDays) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.unite = unite;
		this.stockQuantity = stockQuantity;
		this.pickingDate = pickingDate;
		this.shelfLifeDays = shelfLifeDays;
	}
		
	protected abstract LocalDate calculateExpirationDate();
	
	protected abstract void updateStock();
	
	

}
