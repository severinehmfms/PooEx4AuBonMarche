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
			Fruit.displayAllFreshProducts();
		
			//On récupère la saisie de l'utilisateur
			int choice =  Functions.input_int(scanner, "Merci de rentrer le numéro du produit choisi", 1, Fruit.nbProducts);
			
			//On récupère le produit correspondant au choix de l'utilisateur
			//FreshProduct selectedProduct = catalogue.get(choice - 1);
			//getProduct(int productNumber) (dans Classe Product)
			
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
