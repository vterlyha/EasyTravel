package jar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class EasyTourDbConnection {
	
	private Connection dbConnection;
	private ResultSet dbStatementResult;
	private Statement dbStatement;
	private Scanner scanner;
	
	public EasyTourDbConnection() {
		scanner = new Scanner(System.in);
		final String url = "jdbc:mysql://localhost:3306/tour_firm";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			dbConnection = DriverManager.getConnection(
					url, "root", "TARAS1822");
			dbStatement = dbConnection.createStatement();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException occured");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException occured");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException occured");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException occured");
			e.printStackTrace();
		}
	}
	
	public void getAllCountries() {
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select countries.name from countries");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("name"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getAllHotelsFromCityName(){
		System.out.println("Enter city name: ");
		try {
			String output = scanner.nextLine();
			dbStatementResult = dbStatement.executeQuery(
					"select hotels.name " 
					+"from hotels " 
					+"join cities on (cities.id = hotels.cityId) "
					+"where cities.name = '"+output+"'");
			while (dbStatementResult.next()) {
				System.out.println("Hotel" + dbStatementResult.getString("name"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void checkIfThereAreFreeRooms(){
		System.out.println("Enter hotel id: ");
		int hotelId = scanner.nextInt();
		System.out.println("Enter room category id: ");
		int roomCategoryId = scanner.nextInt();
		System.out.println("Enter date from: ");
		String dateFrom = scanner.next();
		System.out.println("Enter date to: ");
		String dateTo = scanner.next();
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select roomcategoryhotel.quantity "
					+"from roomcategoryhotel "
					+"join roomcategory on roomcategory.id = roomcategoryhotel.categoryId "
					+"join hotels on hotels.id = roomcategoryhotel.hotelId "
					+"join bookings on bookings.hotelId = hotels.id "
					+"where bookings.dateFrom = '"+dateFrom+"' "
					+"and bookings.dateTo = '"+dateTo+"' and hotels.id = "+hotelId+" "
					+"and roomcategory.id = "+roomCategoryId+";");
			while (dbStatementResult.next()) {
				if (dbStatementResult.getInt("quantity") < 12)
					System.out.println("Free rooms are available");
				else System.out.println("Unfortunately no free rooms");
			}
			System.out.println(
					"|--------------------------------------------------------|");
			
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void checkHotelsInParticularCity() {
		System.out.println("Enter city id: ");
		int cityId = scanner.nextInt();
		System.out.println("Enter date from: ");
		String dateFrom = scanner.next();
		System.out.println("Enter date to: ");
		String dateTo = scanner.next();
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select hotels.name "
					+"from hotels "
					+"join cities on cities.id = hotels.cityId "
					+"join bookings on  bookings.hotelId = hotels.id "
					+"where bookings.dateFrom = '"+dateFrom+"' "
					+"and bookings.dateTo = '"+dateTo+"' and cities.id = "+cityId+"");
			while (dbStatementResult.next()) {
				System.out.println("Hotel" + dbStatementResult.getString("name"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
			
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getAllVisasOfClient(){
		System.out.println("Enter client id: ");
		int clientId = scanner.nextInt();
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select clients.firstName, clients.lastName, count(visas.id) " 
					+"from clients "
					+"join clientsvisas on (clients.id = clientsvisas.clientsId) "
					+"join visas on (visas.id = clientsvisas.visasId) "
					+"where clients.id = "+clientId+"");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("clients.firstName") + " "
						+ dbStatementResult.getString("clients.lastName") + "\t"
						+ dbStatementResult.getString("count(visas.id)"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
			
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getAllVisasOfCountry(){
		System.out.println("Enter country id: ");
		int countryId = scanner.nextInt();
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select countries.name, count(visas.id) "
					+"from countries "
					+"join visas on (countries.id = visas.countryId) "
					+"join clientsvisas on (visas.id = clientsvisas.visasId) "
					+"where countries.id = "+countryId+"");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("countries.name") + " "
						+ dbStatementResult.getString("count(visas.id)"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
			
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getAllCities(){
		System.out.println("Enter country id: ");
		int countryId = scanner.nextInt();
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select cities.name from cities "
					+"join countries on countries.id = cities.countryId "
					+"where countries.id = "+countryId+"");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("cities.name"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getClientsInfo(){
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select clients.firstName, clients.lastName, "
					+"visas.countryId, countries.name from clients "
					+"join clientsvisas on clientsvisas.clientsId = clients.id "
					+"join visas on visas.id = clientsvisas.visasId "
					+"join countries on countries.id = visas.countryId");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("clients.firstName") + " "
						+dbStatementResult.getString("clients.lastName") + "\t" 
						+dbStatementResult.getString("visas.countryId") + "\t"
						+dbStatementResult.getString("countries.name"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	public void getHotelInfo(){
		try {
			dbStatementResult = dbStatement.executeQuery(
					"select hotels.name, cities.name, count(clients.id) "
					+"from hotels "
					+"join bookings on bookings.hotelId = hotels.id "
					+"join cities on cities.id = hotels.cityId "
					+"join clients on clients.id = bookings.client_id");
			while (dbStatementResult.next()) {
				System.out.println(dbStatementResult.getString("hotels.name") + "\t"
						+dbStatementResult.getString("cities.name") + "\t" 
						+dbStatementResult.getString("count(clients.id)"));
			}
			System.out.println(
					"|--------------------------------------------------------|");
		} catch (SQLException e) {
			System.out.println("Executing querry error");
			e.printStackTrace();
		}
	}
	
	protected void finalize() {
		System.out.println("Releasing resources...");
        try {
        	dbStatement.close();
			dbConnection.close();
			scanner.close();
		} catch (SQLException e) {
			System.out.println("SQLException occured");
			e.printStackTrace();
		}
    }
}
