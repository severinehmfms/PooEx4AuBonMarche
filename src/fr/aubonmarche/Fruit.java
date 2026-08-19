package fr.aubonmarche;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
		
		//On ajoute le fruit au catalogue
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
	public Fruit(String name, double unitPrice, String unite, int stockQuantity, LocalDate pickingDate, int shelfLifeDays) {
		super(name, unitPrice, unite, stockQuantity, pickingDate, shelfLifeDays);
		
		//On ajoute le fruit au catalogue
		super.addProductToList(this);
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
	 */
	@Override
	public boolean isRipe() {
		//Pour un fruit on part du principe que 2 jours après la cueillette il est mur...
		//Je récupère la date de la cueillette et je rajoute 2 jours
		LocalDate dateMur = super.getPickingDate().plusDays(2);
		//Je regarde si la date obtenue est celle du jour ou après celle du jour si oui je renvoie true
		if (!dateMur.isAfter(LocalDate.now())) {
		    return true;
		}
		return false;
	}

	/**
	 * On redéfinit la méthode isExpired de l'interface Consumable implémentée
	 */
	@Override
	public boolean isExpired(LocalDate dateVerification) {
		//Je regarde si ma date d'expiration est avant la date du jour
		if (calculateExpirationDate().isBefore(LocalDate.now())) {
			return true;
		}
		return false;
	}

	/**
	 * On redéfinit la méthode daysRemainingBeforeExpiration de l'interface Consumable implémentée
	 */
	@Override
	public long daysRemainingBeforeExpiration(LocalDate dateVerification) {
		//On retourne le nombre de jours entre aujourd'hui et la date calculée de péremption
		return ChronoUnit.DAYS.between(
		        LocalDate.now(),
		        calculateExpirationDate()
		    );
	}
	
	
}
