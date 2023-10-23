package com.edu.uptc.laboratorio3.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.uptc.laboratorio3.model.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.edu.uptc.laboratorio3.dto.UserDTO;
import com.edu.uptc.laboratorio3.commons.GenericServiceImpl;


@Service
public class UserServiceImpl extends GenericServiceImpl<User,UserDTO> implements UserServiceAPI {

	@Autowired
	private Firestore firestore;
	
	public UserServiceImpl() {
		super(UserDTO.class);
	}
	
	@Override
	public CollectionReference getCollection() {
		return firestore.collection("Users");
	}


}
