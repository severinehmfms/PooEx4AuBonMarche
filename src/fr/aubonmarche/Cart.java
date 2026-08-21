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
	 * Méthode qui ajoute un produit au panier, ou modifie la quantité si ce produit est déjà dans le panier
	 * @param product
	 * @param quantity
	 */
	public void addProductToCart(Product product, double quantity) {
		//Si le produit choisi existe déjà dans le panier, on modifie la quantité
		for (CartItem item : items) {
			if (item.getProduct().equals(product)) {
	            item.setQuantity(item.getQuantity() + quantity);
	            return;
	        }
	    }
		//Sinon on ajoute un nouveau produit avec la quantité souhaitée
		items.add(new CartItem(product, quantity));
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
	 * Fonction qui retourne l'item correspondant à un produit précis (ou null si aucun item n'existe avec ce produit)
	 * @param product
	 * @return item
	 */
	public CartItem getItem(Product product) {
	    for (CartItem item : items) {
	        if (item.getProduct().equals(product)) {
	            return item;
	        }
	    }
	    return null;
	}
	
	/**
	 * Méthode qui valide la commande (Mise à jour du stock des produits, et validation du statut)
	 */
	public void validCart() {
		if (getStatus().equals(Cart.CartStatus.VALIDATED)) {
			System.out.println("ERREUR - La commande a déjà été validée, il n'est pas possible de la valider plusieurs fois");
			return;
		}
		//Pour chaque item du panier on met à jour le stock du produit
		for (CartItem item : items) {
			item.getProduct().updateStock(item.getQuantity());
		}
		//On passe la commande au statut validée
		status = CartStatus.VALIDATED;
		
		System.out.println("Votre commande a bien été effectuée. Le stock a été mis à jour. Voici votre ticquet de caisse : ");
		//Affichage du ticket de caisse après la commande
		displayTicket();
	}
	
	/**
	 * Méthode pour afficher le panier / la commande (suivant le statut)
	 */
	public void displayCart() {
		System.out.println("----------------------------------------------------------------------------------------------------");
		if (getStatus().equals(Cart.CartStatus.VALIDATED)) {
			System.out.println("Commande validée le " + LocalDate.now());
		}else {
			System.out.println("Panier de " + customer.firstName + " " + customer.name + " en date du " + LocalDate.now());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
		String titleLine = String.format("%-20s %-20s %-20s %-20s %-20s %n", "Produit", "Nom", "Prix", "Quantité", "Sous total");
		System.out.println(titleLine);
		for (CartItem item : items) {
			//Gérer la quantité si int ou double...
			String lineCartStr = String.format("%-20s %-20s %-20s %-20s %-20.2f %n",
					(item.getProduct() instanceof Fruit ? "Fruit": "Légume"),
					item.getProduct().getName(),
					
					String.format("%.2f / %s",
			                item.getProduct().getUnitPrice(),
			                item.getProduct().getUnite()),
			        
					item.getQuantity(),
					item.getSubTotalItem()
					);
			System.out.print(lineCartStr);
		}
		System.out.printf("TOTAL : %.2f%n", this.getTotal());
		System.out.println("----------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * Méthode qui affiche le ticket de caisse si la commande est validée
	 */
	public void displayTicket() {
		if (this.getStatus().equals(CartStatus.IN_PROGRESS)) {
			System.out.println("ERREUR - La commande n'a pas encore été validée, il n'est pas possible d'afficher le ticquet de caisse");
		}else {
			String ticket = "\n================== TICKET DE CAISSE ==================\n";
			for (CartItem item : items) {
			    ticket += String.format(
			    	"%-15s %-6.2f €/%-7s x %5.2f = %7.2f €%n", 
			        item.getProduct().getName(),
			        item.getProduct().getUnitPrice(),
			        item.getProduct().getUnite(),
			        item.getQuantity(),
			        item.getSubTotalItem()
			    );
			}
			ticket += "------------------------------------------------------\n";
			ticket += String.format("%-25s %10.2f €%n", "TOTAL :", this.getTotal());
			ticket += "======================================================\n";
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
