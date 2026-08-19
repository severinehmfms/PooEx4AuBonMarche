package fr.aubonmarche;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le panier du client
 */
public class Cart {
	
	//Le panier est relié à un client
	private Customer customer;
	//Et à une date
	private LocalDate dateCreateCart;
	//Un statut (en cours ou validé)
	private CartStatus status;
	//Liste des items choisis dans le panier
	private List<CartItem> items;
	
	//Status possibles pour ce panier
	public enum CartStatus {
	    IN_PROGRESS,
	    VALIDATED
	}
	/*//Finalement pas besoin de la description !
	public enum CartStatus {
		IN_PROGRESS("En cours"), VALIDATED("Validé");
		
		private final String description;

		CartStatus(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}*/
	
	
	/**
	 * Constructeur
	 */
	public Cart(Customer customer, LocalDate dateCreateCart, CartStatus status) {
		this.customer = customer;
		this.dateCreateCart = dateCreateCart;
		this.status = status;
		
		this.items = new ArrayList<>();
	}
	
	/**
	 * Méthode qui ajoute un produit au panier
	 * @param product
	 * @param quantity
	 */
	public void addProductToCart(Product product, double quantity) {
		CartItem item= new CartItem(product, quantity);
		items.add(item);
	}
	
	/**
	 * Méthode qui calcule le total du panier de l'utilisateur 
	 * @return total
	 */
	public double getTotal() {
		double total = 0.0;
		//On parcoure les items du panier, pour chaque item on récupère le sous total, on additionne tous les sous totaux
		for (CartItem item : items) {
			total += item.getSubTotalItem();
		}
		return total; 
	}
	
	/**
	 * Méthode qui valide la commande (Mise à jour du stock des produits, et validation du statut)
	 */
	public void validCart() {
		//Pour chaque item du panier on met à jour le stock du produit
		for (CartItem item : items) {
			item.getProduct().updateStock(item.getQuantity());
		}
		//On passe la commande au statut validée
		status = CartStatus.VALIDATED;
	}
	
	/**
	 * Méthode pour afficher le panier / la commande (suivant le statut)
	 */
	public void displayCart() {
		System.out.println("--------------------------------------------------");
		if (this.getStatus().equals(CartStatus.IN_PROGRESS)) {
			System.out.println("Panier pour " + customer.firstName + " " + customer.name + " en date du " + LocalDate.now());
		}else {
			System.out.println("Commande validée le " + LocalDate.now());
		}
		
		System.out.println("--------------------------------------------------");
		String titleLine = String.format("%-20s %-20s %-20s %-20s %-20s %n", "Produit", "Nom", "Prix", "Quantité", "Sous total");
		for (CartItem item : items) {
			String lineCartStr = String.format("%-20s %-20s %-20s %-20s %-20s %n",
					(item.getProduct() instanceof Fruit ? "Fruit": "Légume"),
					item.getProduct().getName(),
					item.getProduct().getUnitPrice() + "/ " + item.getProduct().getUnite(),
					item.getQuantity(),
					item.getSubTotalItem()
					);
			System.out.print(lineCartStr);
		}
		String lineCartStr = String.format("%-20s %-20s %-20s %-20s %-20s %n",
				"TOTAL",
				"",
				"",
				"",
				this.getTotal()
				);
		System.out.println("--------------------------------------------------");
	}
	
	/**
	 * Méthode qui affiche le ticket de caisse si la commande est validée
	 */
	public void displayTicket() {
		if (this.getStatus().equals(CartStatus.IN_PROGRESS)) {
			System.out.println("ERREUR - La commande n'a pas encore été validée, il n'est pas possible d'afficher le ticquet de caisse");
		}else {
			String ticket = "";
			for (CartItem item : items) {
				ticket += item.getProduct().getName() + "-" + item.getProduct().getUnitPrice() + "/ " + item.getProduct().getUnite() 
						+ item.getQuantity() + " = " + item.getSubTotalItem() + "\n";
			}
			ticket += "\nTotal : " + this.getTotal() + "\n";
			System.out.print(ticket);			
		}
	}
	
	/**
	 * Getters et Setters
	 */
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getDateCreateCart() {
		return dateCreateCart;
	}
	public void setDateCreateCart(LocalDate dateCreateCart) {
		this.dateCreateCart = dateCreateCart;
	}
	public CartStatus getStatus() {
		return status;
	}
	public void setStatus(CartStatus status) {
		this.status = status;
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
}
