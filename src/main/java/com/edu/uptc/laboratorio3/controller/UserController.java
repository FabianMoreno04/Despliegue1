package com.edu.uptc.laboratorio3.controller;

import java.util.List;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.uptc.laboratorio3.service.*;
import com.edu.uptc.laboratorio3.model.User;
import com.edu.uptc.laboratorio3.dto.UserDTO;
import com.edu.uptc.laboratorio3.model.GenerateUsers;

@RestController
@RequestMapping(value = "/user/api/v1/")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserServiceAPI userServiceAPI;
	@GetMapping(path = "/mensaje")
	public String mensaje() {
		return "si sirve";
	}
	
	@PostMapping(value = "/generateUsers")
    public ResponseEntity<String> generateUsers() {
		int cantidad = 900;
		GenerateUsers generateUsers = new GenerateUsers();
        try {
            for (int i = 0; i < cantidad; i++) {
                User user = generateUsers.generateRandomUser();// Utiliza el método de generación de usuarios aleatorios
                userServiceAPI.save(user);
            }
            return new ResponseEntity<String>("Se generaron y guardaron " + cantidad + " usuarios.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al generar usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	 
	@GetMapping(path = "/getName")
	public String getPrincipalName(Principal principal) {
		return principal.getName();
	}
	
	@GetMapping(value = "/all")
	public List<User> getAll() throws Exception {
		return userServiceAPI.getAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
	    try {
	        UserDTO user = userServiceAPI.getUserById(id);
	        if (user != null) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@PostMapping(value = "/save/{id}")
	public ResponseEntity<String> save(@RequestBody User user, @PathVariable String id) throws Exception {
		if (id == null || id.length() == 0 || id.equals("null")) {
			id = userServiceAPI.save(user);
		} else {
			userServiceAPI.save(user, id);
		}
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable String id) throws Exception {
		User persona = userServiceAPI.get(id);
		if (persona != null) {
			userServiceAPI.delete(id);
		} else {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<User>(persona, HttpStatus.OK);
	}

}
