package ie.domis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.domis.domain.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{

}
