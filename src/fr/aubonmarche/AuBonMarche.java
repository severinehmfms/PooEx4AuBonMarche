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
		
		//On initialise les produits et leur stock, et dates péremption etc..
		initialisationProducts();
		
		String[] menu = {
			    "Quitter le programme",
			    "Choix des produits",
			    "Affichage du panier",
			    "Validation du panier et commande",
			    "Affichage du ticket de caisse après la commande"
			};
		
		int choice_user = -1;
		while (choice_user != 0) {
			//On demande à l'utilisateur son choix par rapport au menu proposé
			choice_user = Functions.ask_user_choice(scanner, menu);
			switch(choice_user) {
				case 1:				
					//Choix des produits
					System.out.println("Choix des produits");
					choiceProducts();
					break;
				case 2:
					//Affichage du panier
					System.out.println("Affichage du panier");
					break;
				case 3:	
					//Validation du panier et commande
					System.out.println("Validation du panier et commande");
					break;
				case 4:
					//Affichage du ticket de caisse après la commande
					System.out.println("Affichage du ticket de caisse après la commande");
					break;
				case 0:
					System.out.println("Au-revoir et à bientôt !");
					break;
			}
		}
		
		//On referme le scanner
		scanner.close();
	}
	
	public static void choiceProducts() {
		
		
		//Tant que l'utilisateur veut continuer de choisir des produits, on continue de lui proposer
		
			//On affiche les produits disponibles
			FreshProduct.displayAllFreshProducts();
		
			//On récupère la saisie de l'utilisateur
			int choice =  Functions.input_int(scanner, "Merci de rentrer le numéro du produit choisi", 1, FreshProduct.nbProducts);
			
			//On récupère le produit correspondant au choix de l'utilisateur
			//FreshProduct selectedProduct = catalogue.get(choice - 1);
			
			//On demande à l'utilisateur le nombre de pièces/de kilos de ce produit qu'il souhaite acheter
			
			//On ajoute ce produit au panier,
			
		
	}
	
	public static void displayCart() {
		//TODO Fonction qui affiche le panier
	}
	
	public static void validateCart() {
		//TODO Fonction qui valide le panier et passe la commande (met à jour le stock!)
	}
	
	public static void displayTicket() {
		//TODO Fonction qui affiche le ticket de caisse
	}
	
	public static void initialisationProducts() {
		//Product[] products = {
	    new FreshProduct("Clémentine", 		2.90, FreshProduct.UNITE_KG, 	6, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(3),   7); //,
	    new FreshProduct("Datte",       	7.00, FreshProduct.UNITE_KG, 	4, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(2),	7);
	    new FreshProduct("Grenade",    		3.50, FreshProduct.UNITE_KG, 	3, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(5),	14);
	    new FreshProduct("Kaki",       		4.50, FreshProduct.UNITE_KG, 	3, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(2),	10);
	    new FreshProduct("Kiwi",       		3.50, FreshProduct.UNITE_KG, 	5, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(1),	5);		    
	    new FreshProduct("Mandarine",  		2.80, FreshProduct.UNITE_KG, 	6, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(2),	10);
	    new FreshProduct("Orange",     		1.50, FreshProduct.UNITE_KG, 	8, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(3),	10);
	    new FreshProduct("Pamplemousse",	2.00, FreshProduct.UNITE_PIECE, 8, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(1),	7);
	    new FreshProduct("Poire",      		2.50, FreshProduct.UNITE_KG, 	5, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(1),	4);
	    new FreshProduct("Pomme",      		1.50, FreshProduct.UNITE_KG, 	8, FreshProduct.CATEGORY_FRUIT, LocalDate.now().minusDays(1),	10);
	    
	    new FreshProduct("Carotte",      	1.30, FreshProduct.UNITE_KG, 	7, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(2),	10);
	    new FreshProduct("Choux de Bruxelles",4.00, FreshProduct.UNITE_KG, 	4, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(1),	3);
	    new FreshProduct("Chou vert",  		2.50, FreshProduct.UNITE_PIECE, 12,FreshProduct.CATEGORY_VEGETABLE, LocalDate.now(),				2);
	    new FreshProduct("Courge butternut",2.50, FreshProduct.UNITE_PIECE, 6, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(10),	50);
	    new FreshProduct("Endive",     		2.50, FreshProduct.UNITE_KG, 	5, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now(),				7);
	    new FreshProduct("Epinard",    		2.60, FreshProduct.UNITE_KG, 	4, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(1),	4);
	    new FreshProduct("Poireau",    		1.20, FreshProduct.UNITE_KG, 	5, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(1),	5);
	    new FreshProduct("Potiron",    		2.50, FreshProduct.UNITE_PIECE, 6, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(5),	50);
	    new FreshProduct("Radis noir", 		5.00, FreshProduct.UNITE_PIECE, 10,FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(1),	5);
	    new FreshProduct("Salsifis",   		2.50, FreshProduct.UNITE_KG, 	3, FreshProduct.CATEGORY_VEGETABLE, LocalDate.now().minusDays(3),	10);
			//};
	}
		
}
