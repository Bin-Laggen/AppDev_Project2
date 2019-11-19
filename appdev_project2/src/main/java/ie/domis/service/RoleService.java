package ie.domis.service;

import java.util.List;

import ie.domis.domain.Role;

public interface RoleService {
	
	Role findByUserEmail(String email);
	List<Role> findAll();
	Role addRole(Role role);
	boolean updateRoleDescription(String email, String description);
	boolean removeRole(String email);
	boolean doesUserHaveRole(String email);

}
