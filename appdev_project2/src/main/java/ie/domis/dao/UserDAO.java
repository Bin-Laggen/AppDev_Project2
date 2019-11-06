package ie.domis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.domis.domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
	User findByEmail(String email);

}
