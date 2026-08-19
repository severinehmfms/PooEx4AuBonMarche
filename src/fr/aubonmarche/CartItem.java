package fr.aubonmarche;

/**
 * Classe représentant une ligne du panier (produit - quantité)
 */

public class CartItem {

	private Product product;
	private double quantity;
	
	/**
	 * Constructeur
	 * @param product
	 * @param quantity
	 */
	public CartItem(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	

	//Fonction qui renvoie le sous total de cette ligne du panier
	public double getSubTotalItem() {
		return product.getUnitPrice() * quantity;		
	}
	
	
	/**
	 * Méthode qui retourne le produit
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Méthode qui met à jour le produit
	 * @param product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Méthode qui retourne la quantité
	 * @return quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Méthode qui met à jour la quantité
	 * @param quantity
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
