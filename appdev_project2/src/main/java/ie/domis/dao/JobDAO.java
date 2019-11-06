package ie.domis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.domis.domain.Job;

public interface JobDAO extends JpaRepository<Job, Integer> {
	
	boolean existsById(int id);

}
