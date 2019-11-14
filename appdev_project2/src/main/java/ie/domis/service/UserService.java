package ie.domis.service;

import java.util.List;

import ie.domis.domain.User;

public interface UserService {
	
	User findById(int id);
	User findByEmail(String email);
	List<User> findAll();
	List<User> findAllActiveUsers();
	List<Integer> findAllUserIds();
	List<String> findAllUserEmails();
	User addUser(User user);
	boolean removeUser(int id);
	boolean updateUserName(int id, String name);
	boolean updateUserSurname(int id, String surname);
	boolean updateUserPhoneNumber(int id, int phoneNumber);
	boolean updateUserEnabled(int id, boolean enabled);
	boolean isUserInDatabase(int id);
	boolean isUserInDatabase(String email);

}
