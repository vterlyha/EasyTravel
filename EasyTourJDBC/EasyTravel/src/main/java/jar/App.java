package jar;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	EasyTourDbConnection db = new EasyTourDbConnection();
    	System.out.println("Welcome to easyTour!");
    	while (true){
    		System.out.println("\nEnter 1 to select countries we are working with");
    		System.out.println("Enter 2 to select hotels of particular city");
    		System.out.println("Enter 3 to check if there are free rooms in the hotel");
    		System.out.println("Enter 4 to get all available hotels in the city on date");
    		System.out.println("Enter 5 to get all visas of particular client");
    		System.out.println("Enter 6 to get all visas of particular country");
    		System.out.println("Enter 7 to get all cities of particular country agency is working with");
    		System.out.println("Enter 8 to get full clients information");
    		System.out.println("Enter 9 to get full hotels information");
    		System.out.println("Enter 0 to exit");
    		int choise = sc.nextInt();
        	if ( choise == 1) {
        		db.getAllCountries();
        	}
        	else if (choise == 2) {
        		db.getAllHotelsFromCityName();
        	}
        	else if (choise == 3) {
        		db.checkIfThereAreFreeRooms();
        	}
        	else if (choise == 4) {
        		db.checkHotelsInParticularCity();
        	}
        	else if (choise == 5) {
        		db.getAllVisasOfClient();
        	}
        	else if (choise == 6) {
        		db.getAllVisasOfCountry();
        	}
        	else if (choise == 7) {
        		db.getAllCities();
        	}
        	else if (choise == 8) {
        		db.getClientsInfo();
        	}
        	else if (choise == 9) {
        		db.getHotelInfo();
        	}
        	else if (choise == 0) {
        		System.out.println("See you soon!");
        		db.finalize();
        		sc.close();
        		break;
        	}
    	}
    }
}
