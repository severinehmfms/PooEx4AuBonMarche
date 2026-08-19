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
	
	private String name;
	private double unitPrice;
	private String unite;
	private double stockQuantity;
	private LocalDate pickingDate;	//Date de cueillette
	private int shelfLifeDays;		//Durée maximale de conservation en jours
	
	//Catalogue de produits
	private static final List<Product> catalogProducts = new ArrayList<>();
	
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
	
	protected abstract void updateStock(double orderedQuantity);
	
	/**
	 * Méthode toString de base
	 */
	public String toString() {
		return this.name + " - Prix : " + this.unitPrice + " / " + this.unite;
	}
	
	/**
	 * Fonction qui va afficher tous les produits
	 */
	/*
	public static void displayAllFreshProducts() {
		//for (FreshProduct product : products) {
		//    System.out.println(product);
		//}
		
		//Avec affichage du numéro pour simplifier la saisie du client
		for (int i = 0; i < products.size(); i++) {
	        System.out.println(
	            (i + 1) + " - " + products.get(i)
	        );
		}	
	}*/
	
	/**
	 * Fonction qui ajoute un Product à la liste des produits (en attribut)
	 * @param product
	 */
	public static void addProductToList(Product product) {
		catalogProducts.add(product);
	}
	
	/**
	 * Fonction qui retourne le produit correspondant au numéro choisi par le client
	 * @param productNumber
	 * @return
	 */
	public static Product getProductListByNumber(int productNumber) {
	    return catalogProducts.get(productNumber - 1);
	}
	
	/**
	 * Getters et Setters des attributs 
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public LocalDate getPickingDate() {
		return pickingDate;
	}

	public void setPickingDate(LocalDate pickingDate) {
		this.pickingDate = pickingDate;
	}

	public int getShelfLifeDays() {
		return shelfLifeDays;
	}

	public void setShelfLifeDays(int shelfLifeDays) {
		this.shelfLifeDays = shelfLifeDays;
	}

	public static List<Product> getProducts() {
		return catalogProducts;
	}
	
	
	
}
