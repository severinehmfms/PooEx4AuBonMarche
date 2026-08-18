package fr.aubonmarche;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FreshProduct extends Product implements Consumable{
	
	public static String UNITE_KG = "kg";
	public static String UNITE_PIECE = "pièce";
	
	public static String CATEGORY_FRUIT = "Fruit";
	public static String CATEGORY_VEGETABLE = "Légume";
	
	protected String categoryProduct;
	
	private static final List<FreshProduct> products = new ArrayList<>();
	protected static int nbProducts;
	
	/**
	 * Constructeur du fruit sans les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 */
	public FreshProduct(String name, double unitPrice, String unite, double stockQuantity, String categoryProduct) {
		super(name, unitPrice, unite, stockQuantity);
		this.categoryProduct = categoryProduct;
		
		//On ajoute le produit frais à la liste
		products.add(this);
		nbProducts++;
	}
	
	/**
	 * Constructeur du fruit avec les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 * @param pickingDate
	 * @param shelfLifeDays
	 */
	public FreshProduct(String name, double unitPrice, String unite, double stockQuantity, String categoryProduct, LocalDate pickingDate, int shelfLifeDays) {
		super(name, unitPrice, unite, stockQuantity, pickingDate, shelfLifeDays);
		this.categoryProduct = categoryProduct;
		
		//On ajoute le produit frais à la liste
		products.add(this);
		nbProducts++;
	}
	
	public String toString() {
		return categoryProduct + " : " + super.name + " - Prix : " + super.unitPrice + " / " + super.unite;
	}

	
	/**
	 * On redéfinit la méthode calculateExpirationDate de la classe mère Product
	 */
	@Override
	public LocalDate calculateExpirationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * On redéfinit la méthode updateStock de la classe mère Product
	 */
	@Override
	public void updateStock() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * On redéfinit la méthode isRipe de l'interface Consumable implémentée
	 */
	@Override
	public boolean isRipe() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * On redéfinit la méthode isExpired de l'interface Consumable implémentée
	 */
	@Override
	public boolean isExpired(LocalDate dateVerification) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * On redéfinit la méthode daysRemainingBeforeExpiration de l'interface Consumable implémentée
	 */
	@Override
	public long daysRemainingBeforeExpiration(LocalDate dateVerification) {
		// TODO Auto-generated method stub
		return 0;
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
	 * Getters et Setters
	 * 
	 */
	public String getCategoryProduct() {
		return categoryProduct;
	}

	public void setCategoryProduct(String categoryProduct) {
		this.categoryProduct = categoryProduct;
	}
}
