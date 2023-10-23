package com.edu.uptc.laboratorio3.model;

import java.util.Random;

public class GenerateUsers {

	public User generateRandomUser() {
	    User user = new User();
	    user.setId(generateIDNumerico());
	    user.setName(generateRandomName());
	    user.setLastname(generateRandomLastName());
	    user.setTypeDocument(generateRandomDocumentType());
	    user.setDocument(generateRandomDocument());
	    user.setBirthDate(generateRandomBirthDate());
	    user.setContactNumber(generateRandomContactNumber());
	    user.setStatus(new Random().nextBoolean());
	    user.setRegistrationDate(generateRandomRegistrationDate());
	    user.setAddress(generateRandomAddress());
	    user.setPath(generateRandomPath());
	    
	    return user;
	}
	public static String generateIDNumerico() {
		Random rand = new Random();
	    int num = rand.nextInt(5000) + 1;  // Rango de 1 a 5000
	    String numString = String.valueOf(num);
	    return numString;
	}
	
	private String generateRandomName() {
	    String[] names = {"John", "Jane", "Michael", "Emily", "William", "Juan", "Danilo", "Nelson", "Fabian", "Michel"};
	    return names[new Random().nextInt(names.length)];
	}

	private String generateRandomLastName() {
	    String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Miller", "Diaz", "Rodriguez", "Daza", "Porras"};
	    return lastNames[new Random().nextInt(lastNames.length)];
	}

	private String generateRandomDocumentType() {
	    String[] documentTypes = {"Passport", "Cedula Extrangera", "Cedula Ciudadania", "Tarjeta de Identidad"};
	    return documentTypes[new Random().nextInt(documentTypes.length)];
	}

	private String generateRandomDocument() {
	    // Genera un número de documento aleatorio (por ejemplo, un número de 8 dígitos)
	    return String.format("%08d", new Random().nextInt(100000000));
	}

	private String generateRandomBirthDate() {
	    // Genera una fecha de nacimiento aleatoria en el rango de 18 a 70 años atrás
	    int year = 1950 + new Random().nextInt(51);
	    int month = 1 + new Random().nextInt(12);
	    int day = 1 + new Random().nextInt(30); // Asumiendo un máximo de 28 días por simplicidad
	    return String.format("%04d-%02d-%02d", year, month, day);
	}

	private String generateRandomContactNumber() {
	    // Genera un número aleatorio de 9 dígitos y luego agrega "3" como el primer dígito
	    long randomNumber = (long) (Math.random() * 9000000000L) + 1000000000L;
	    return "3" + String.valueOf(randomNumber);
	}

	private String generateRandomRegistrationDate() {
	    // Genera una fecha de registro aleatoria en el rango de los últimos 10 años
	    int year = 2013 + new Random().nextInt(10);
	    int month = 1 + new Random().nextInt(12);
	    int day = 1 + new Random().nextInt(30); // Asumiendo un máximo de 28 días por simplicidad
	    return String.format("%04d-%02d-%02d", year, month, day);
	}

	private String generateRandomAddress() {
	    String[] addresses = {"123 Tunja,Boyaca", "456 Bogota,suba", "789 Medellin,poblado"
	    		, "101 Sogamoso,popular", "202 Cali, valle"};
	    return addresses[new Random().nextInt(addresses.length)];
	}

	private String generateRandomPath() {
	    String[] paths = {"https://drive.google.com/file/d/1uGgEbVm5t9Gzb7lxreH4UbZRqDQbu5xZ/view?usp=sharing"};
	    return paths[new Random().nextInt(paths.length)];		
	}
}
