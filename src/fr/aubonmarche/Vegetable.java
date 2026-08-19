package fr.aubonmarche;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vegetable extends Product implements Consumable{
		
	/**
	 * Constructeur du légume sans les informations liées à la conservation des produits
	 * @param name
	 * @param unitPrice
	 * @param unite
	 * @param stockQuantity
	 */
	public Vegetable(String name, double unitPrice, String unite, double stockQuantity) {
		super(name, unitPrice, unite, stockQuantity);
		
		//On ajoute le légume au catalogue
		super.addProductToList(this);
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
	public Vegetable(String name, double unitPrice, String unite, double stockQuantity, LocalDate pickingDate, int shelfLifeDays) {
		super(name, unitPrice, unite, stockQuantity, pickingDate, shelfLifeDays);
		
		//On ajoute le légume à la liste des produits
		super.addProductToList(this);
	}
	
	/**
	 * On redéfinit la méthode toString
	 */
	@Override
	public String toString() {
		return "Légume : " + super.toString();
	}
	
	/**
	 * On redéfinit la méthode calculateExpirationDate de la classe mère Product
	 */
	@Override
	public LocalDate calculateExpirationDate() {
		//Si la date de cueillette existe, on calcule la date de péremption
		if (super.getPickingDate() != null) {
		    return super.getPickingDate().plusDays(super.getShelfLifeDays());
		} else {
		    return null;
		}
	}

	/**
	 * On redéfinit la méthode updateStock de la classe mère Product
	 * On met à jour le stock en déduisant la quantité commandée de la quantité existante
	 */
	@Override
	public void updateStock(double orderedQuantity) {
		double newStock = getStockQuantity() - orderedQuantity;
		setStockQuantity(newStock);
	}

	/**
	 * On redéfinit la méthode isRipe de l'interface Consumable implémentée
	 * On part du principe que pour un légume, c'est forcément mur... (ça se discute :-p, c'est pour l'exercice!)
	 */
	@Override
	public boolean isRipe() {
		return true;
	}

	/**
	 * On redéfinit la méthode isExpired de l'interface Consumable implémentée
	 */
	public boolean isExpired(LocalDate dateCible) {
		//Je regarde si ma date d'expiration est avant la date passée en paramètre
		if (calculateExpirationDate().isBefore(dateCible)) {
			return true;
		}
		return false;
	}

	/**
	 * On redéfinit la méthode daysRemainingBeforeExpiration de l'interface Consumable implémentée
	 */
	@Override
	public long daysRemainingBeforeExpiration(LocalDate dateCible) {
		//On retourne le nombre de jours entre la date passée en paramètre et la date calculée de péremption
		return ChronoUnit.DAYS.between(
				dateCible,
		        calculateExpirationDate()
		    );
	}
}
