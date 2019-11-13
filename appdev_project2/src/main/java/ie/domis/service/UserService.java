package ie.domis.service;

import java.util.List;

import ie.domis.domain.User;

public interface UserService {
	
	User findById(int id);
	User findByEmail(String email);
	List<User> findAll();
	int addUser(User user);
	boolean removeUser(int id);
	boolean updateUserName(int id, String name);
	boolean updateUserSurname(int id, String name);
	boolean updateUserPhoneNumber(int id, int phoneNumber);
	boolean updateUserEnabled(int id, boolean enabled);
	boolean isUserInDatabase(int id);

}
