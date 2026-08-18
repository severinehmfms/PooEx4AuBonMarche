package fr.aubonmarche;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite Product
 */

public abstract class Product {
	//Constantes pour les unités
	public static String UNITE_KG = "kg";
	public static String UNITE_PIECE = "pièce";
	
	protected String name;
	protected double unitPrice;
	protected String unite;
	protected double stockQuantity;
	protected LocalDate pickingDate;	//Date de cueillette
	protected int shelfLifeDays;		//Durée maximale de conservation en jours
	
	//Liste de produits et le nombre de produits
	protected static final List<Product> products = new ArrayList<>();
	protected static int nbProducts;
	
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
	
	/**
	 * Méthode toString de base
	 */
	public String toString() {
		return this.name + " - Prix : " + this.unitPrice + " / " + this.unite;
	}

	
	/**
	 * Fonction qui va afficher tous les produits
	 */
	public static void displayAllFreshProducts() {
		/*for (FreshProduct product : products) {
		    System.out.println(product);
		}*/
		
		//Avec affichage du numéro pour simplifier la saisie du client
		for (int i = 0; i < products.size(); i++) {
	        System.out.println(
	            (i + 1) + " - " + products.get(i)
	        );
		}	
	}
	
	/**
	 * Fonction qui retourne le produit du numéro choisi par le client
	 * @param productNumber
	 * @return
	 */
	public static Product getProduct(int productNumber) {
	    return products.get(productNumber - 1);
	}
		
	protected abstract LocalDate calculateExpirationDate();
	
	protected abstract void updateStock();
	
}
