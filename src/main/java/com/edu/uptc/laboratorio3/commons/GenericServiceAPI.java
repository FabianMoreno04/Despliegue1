package com.edu.uptc.laboratorio3.commons;

import java.util.List;

public interface GenericServiceAPI<User,UserDTO> {
	String save(User entity,String id)throws Exception;
	String save(User entity)throws Exception;
	void delete(String id)throws Exception;
	User get(String id)throws Exception;
	List<User> getAll()throws Exception;
	UserDTO getUserById(String userId) throws Exception;
}
