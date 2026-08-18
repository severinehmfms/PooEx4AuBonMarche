package fr.aubonmarche;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fruit extends Product implements Consumable{
		
	/**
	 * Constructeur du fruit sans les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 */
	public Fruit(String name, double unitPrice, String unite, double stockQuantity) {
		super(name, unitPrice, unite, stockQuantity);
		
		//On ajoute le fruit à la liste des produits
		super.products.add(this);
		super.nbProducts++;
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
	public Fruit(String name, double unitPrice, String unite, double stockQuantity, LocalDate pickingDate, int shelfLifeDays) {
		super(name, unitPrice, unite, stockQuantity, pickingDate, shelfLifeDays);
		
		//On ajoute le fruit à la liste des produits
		super.products.add(this);
		super.nbProducts++;
	}
	
	/**
	 * On redéfinit la méthode toString
	 */
	@Override
	public String toString() {
		return "Fruit : " + super.toString();
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
	
	
}
