package fr.aubonmarche;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Classe pour tester les fonctions de péremption
 */
public class TestPeremption {	

	//On initialise le scanner
	private static Scanner scanner = new Scanner(System.in);
	//Format date français
	private static final DateTimeFormatter FRENCH_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args){
		
		LocalDate dateCible = null;
				
		//Si la chaîne est au format ISO yyyy-MM-dd Java comprend automatiquement qu'il s'agit d'une date
		String dateString = "2025-01-15";
		dateCible = LocalDate.parse(dateString);
		System.out.println(dateCible.format(FRENCH_DATE_FORMAT));
		
		//Pour une saisie sous la forme française il faudra passer par un DateTimeFormatter
		String saisie = "15/01/2025";
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dateCible = LocalDate.parse(saisie, FRENCH_DATE_FORMAT);
		System.out.println(dateCible.format(FRENCH_DATE_FORMAT));
		
		//On demande à l'utilisateur de saisir une date
		dateCible = Functions.input_date_fr(scanner, "Merci de saisir une date au format dd/MM/yyyy");
		System.out.println(dateCible.format(FRENCH_DATE_FORMAT));
		
		/*Dates qui doivent déclencher une exception
		 * 31/02/2025
			29/02/2025
			31/04/2025
			31/06/2025
			31/09/2025
			31/11/2025
		 * 
		 * */

		//On referme le scanner
		scanner.close();
		
	}
}
