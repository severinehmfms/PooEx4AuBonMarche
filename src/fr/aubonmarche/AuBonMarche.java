package fr.aubonmarche;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Classe qui représente l'application AuBonMarche pour l'achat de fruits et légumes par un client
 */

public class AuBonMarche {
	
	//On initialise le scanner
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args){		
		
		//On crée le client (en dur pour l'exercice actuel)
		Customer myClient = new Customer("Dupont","Pierre");
		
		//On crée le panier correspondant à cet utilisateur, pour cette journée (pour l'exercice actuel on ne va pas plus loin dans la gestion de clients)
		Cart myCart = new Cart(myClient, LocalDate.now(), Cart.CartStatus.IN_PROGRESS);
		
		//On initialise les produits et leur stock, et dates péremption etc..
		initialisationProducts();
		
		String[] menu = {
			    "Quitter le programme",
			    "Commander des produits",
			    "Affichage de mon panier / de ma commande",
			    "Validation de mon panier pour passer la commande",
			    "Affichage des produits en stock"
			};
		
		int choice_user = -1;
		while (choice_user != 0) {
			//On demande à l'utilisateur son choix par rapport au menu proposé
			choice_user = Functions.ask_user_choice(scanner, menu);
			switch(choice_user) {
				case 1:				
					//Choix des produits
					if (myCart.getStatus().equals(Cart.CartStatus.VALIDATED)) {
						System.out.println("ERREUR - La commande a été validée, il n'est plus possible de rajouter des produits");
					}else {
						choiceProducts(myCart);
					}
					break;
				case 2:
					if (myCart.getStatus().equals(Cart.CartStatus.VALIDATED)) {
						System.out.println("Affichage de ma commande");
					}else {
						System.out.println("Affichage du panier");
					}
					myCart.displayCart();
					break;
				case 3:	
					if (myCart.getStatus().equals(Cart.CartStatus.VALIDATED)) {
						System.out.println("ERREUR - La commande a déjà été validée, il n'est pas possible de la valider plusieurs fois");
					}else {
						//Validation du panier et commande
						myCart.validCart();
						System.out.println("Votre commande a bien été effectuée. Le stock a été mis à jour. Voici votre ticquet de caisse : ");
						//Affichage du ticket de caisse après la commande
						myCart.displayTicket();
					}
					break;
				case 4:	
					//Affichage des produits en stock
					displayProductsStock(Fruit.getProducts());
					break;					
				case 0:
					System.out.println("Au-revoir et à bientôt !");
					break;
			}
		}
		
		//On referme le scanner
		scanner.close();
	}
	
	
	/**
	 * Fonction qui gère l'interaction avec l'utilisateur pour le choix des produits et l'ajout au panier
	 * @param myCart
	 */
	public static void choiceProducts(Cart myCart) {
		System.out.println("Liste des produits disponibles");
		boolean choice_continue = true;
		
		while (choice_continue) {
			//Fruit.displayAllFreshProducts();
			//On récupère la liste des produits et on l'affiche (je trouve que c'est plus cohérent que de faire l'affichage correspondant au bon marché dans la classe produit)
			displayProductsList(Fruit.getProducts());
			
			//On récupère la saisie de l'utilisateur
			int numProductChoose =  Functions.input_int(scanner, "Merci de rentrer le numéro du produit choisi", 1, Fruit.getProducts().size()+1);
			
			//On récupère le produit correspondant au choix de l'utilisateur
			Product chooseProduct = Product.getProductListByNumber(numProductChoose);
			
			//On calcule le stock disponible
			double stockDisponible = chooseProduct.getStockQuantity();
			//Si ce produit existe déjà dans le panier, on calcule le nouveau stock disponible
			CartItem item = myCart.getItem(chooseProduct);
			if (item != null) {
			    stockDisponible = stockDisponible - item.getQuantity();
			}
			
			if (stockDisponible > 0) {
				//On demande à l'utilisateur la quantité d'unités du produit qu'il souhaite acheter
				String promptQuantity = "Combien de "  + chooseProduct.getUnite() + "s de " + chooseProduct.getName() + " voulez vous acheter ? ";
				double quantityChoose;
				//Si c'est un produit à l'unité on doit saisir un int qu'on convertit ensuite en double
				if (chooseProduct.getUnite().equals(Product.UNITE_PIECE)) {
					int quantityChooseInt =  Functions.input_int(scanner, promptQuantity +" (Maximum " + String.valueOf((int) stockDisponible) + ")", 1, (int) stockDisponible);
					quantityChoose = quantityChooseInt;
				}else {
					//Si c'est un produit au kg on doit saisir un double entre 0.1 et le stock de ce produit
					quantityChoose =  Functions.input_double(scanner, promptQuantity +" (Maximum " + String.valueOf(stockDisponible) + ")", 0.1, stockDisponible);
				}
				
				//On ajoute ce produit au panier
				myCart.addProductToCart(chooseProduct, quantityChoose);
				
				//On affiche le panier
				myCart.displayCart();
				
				//On demande à l'utilisateur s'il souhaite continuer ses achats
				choice_continue = Functions.input_yes_no(scanner, "Voulez vous continuer vos achats? oui/non ");
			}else {
				System.out.println("ERREUR - Il n'y a plus de stock disponible pour ce produit : " + chooseProduct.getName());
			}
		}	
		
	}
	
	/**
	 * Fonction qui affiche la liste des produits passée en paramètres, pour saisie utilisateur
	 * @param products
	 */
	public static void displayProductsList(List<Product> products) {
		//Avec affichage du numéro pour simplifier la saisie du client
		for (int i = 0; i < products.size(); i++) {
	        System.out.println( (i + 1) + " - " + products.get(i) );
		}	
	}
	
	/**
	 * Fonction qui affiche la liste des produits et leur stock
	 * @param products
	 */
	public static void displayProductsStock(List<Product> products) {
		System.out.println("Produits en stock : ");
		for (Product product : products) {			
			System.out.println( product.getName() + " : " + product.getStockQuantity() + " " + product.getUnite());
		}
	}
	
	/**
	 * Fonction qui initialise les produits du catalogue
	 */
	public static void initialisationProducts() {
		new Fruit("Clémentine", 		2.90, Fruit.UNITE_KG, 	6, 	LocalDate.now().minusDays(3),   7);
	    new Fruit("Datte",       	7.00, Fruit.UNITE_KG, 		4, 	LocalDate.now().minusDays(2),	7);
	    new Fruit("Grenade",    		3.50, Fruit.UNITE_KG, 	3, 	LocalDate.now().minusDays(5),	14);
	    new Fruit("Kaki",       		4.50, Fruit.UNITE_KG, 	3,  LocalDate.now().minusDays(2),	10);
	    new Fruit("Kiwi",       		3.50, Fruit.UNITE_KG, 	5,  LocalDate.now().minusDays(1),	5);		    
	    new Fruit("Mandarine",  		2.80, Fruit.UNITE_KG, 	6,  LocalDate.now().minusDays(2),	10);
	    new Fruit("Orange",     		1.50, Fruit.UNITE_KG, 	8,  LocalDate.now().minusDays(3),	10);
	    new Fruit("Pamplemousse",	2.00, Fruit.UNITE_PIECE, 	8,  LocalDate.now().minusDays(1),	7);
	    new Fruit("Poire",      		2.50, Fruit.UNITE_KG, 	5, 	LocalDate.now().minusDays(1),	4);
	    new Fruit("Pomme",      		1.50, Fruit.UNITE_KG, 	8, 	LocalDate.now().minusDays(1),	10);
	    
	    new Vegetable("Carotte",      	1.30, Fruit.UNITE_KG, 	7, 		LocalDate.now().minusDays(2),	10);
	    new Vegetable("Choux de Bruxelles",4.00, Fruit.UNITE_KG, 	4,  LocalDate.now().minusDays(1),	3);
	    new Vegetable("Chou vert",  		2.50, Fruit.UNITE_PIECE, 12,LocalDate.now(),				2);
	    new Vegetable("Courge butternut",2.50, Fruit.UNITE_PIECE, 6, 	LocalDate.now().minusDays(10),	50);
	    new Vegetable("Endive",     		2.50, Fruit.UNITE_KG, 	5, 	LocalDate.now(),				7);
	    new Vegetable("Epinard",    		2.60, Fruit.UNITE_KG, 	4, 	LocalDate.now().minusDays(1),	4);
	    new Vegetable("Poireau",    		1.20, Fruit.UNITE_KG, 	5, 	LocalDate.now().minusDays(1),	5);
	    new Vegetable("Potiron",    		2.50, Fruit.UNITE_PIECE, 6, LocalDate.now().minusDays(5),	50);
	    new Vegetable("Radis noir", 		5.00, Fruit.UNITE_PIECE, 10,LocalDate.now().minusDays(1),	5);
	    new Vegetable("Salsifis",   		2.50, Fruit.UNITE_KG, 	3, 	LocalDate.now().minusDays(3),	10);
	}
		
}
