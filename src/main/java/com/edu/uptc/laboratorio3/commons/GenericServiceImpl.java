package com.edu.uptc.laboratorio3.commons;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;


@SuppressWarnings("unchecked")
@Service
public abstract class GenericServiceImpl <User, UserDTO> implements GenericServiceAPI<User, UserDTO>{
	private Class<UserDTO> clazz;
	
	public GenericServiceImpl(Class<UserDTO> entityClass) {
		this.clazz = entityClass;
	}
	@Override
	public String save(User entity) throws Exception {
		return this.save(entity, null);
	}
	@Override
	public String save(User entity, String id) throws Exception {
		if (id == null || id.length() == 0) {
			return getCollection().add(entity).get().getId();
		}

		DocumentReference reference = getCollection().document(id);
		reference.set(entity);
		return reference.getId();
	}

	@Override
	public void delete(String id) throws Exception {
		getCollection().document(id).delete().get();
	}
	

	@Override
	public User get(String id) throws Exception {
		DocumentReference ref = getCollection().document(id);
		ApiFuture<DocumentSnapshot> futureDoc = ref.get();
		DocumentSnapshot document = futureDoc.get();
		if (document.exists()) {
			User object = (User) document.toObject(clazz);
			PropertyUtils.setProperty(object, "id", document.getId());
			return (User) object;
		}
		return null;
	}

	@Override
	public List<User> getAll() throws Exception {
		List<User> result = new ArrayList<User>();
		ApiFuture<QuerySnapshot> query = getCollection().get();
		List<QueryDocumentSnapshot> documents = query.get().getDocuments();
		for (QueryDocumentSnapshot doc : documents) {
			User object = (User) doc.toObject(clazz);
			PropertyUtils.setProperty(object, "id", doc.getId());
			result.add(object);
		}
		return result;
	}

	@Override
	public UserDTO getUserById(String userId) throws Exception {
	    ApiFuture<QuerySnapshot> query = getCollection().whereEqualTo("id", userId).get();

	    List<QueryDocumentSnapshot> documents = query.get().getDocuments();
	    if (!documents.isEmpty()) {
	        QueryDocumentSnapshot doc = documents.get(0);
	        UserDTO user =  doc.toObject(clazz);
	        PropertyUtils.setProperty(user, "id", doc.getId());
	        return user;
	    } else {
	        return null;
	    }
	}

	public abstract CollectionReference getCollection();
	
}
