package ie.domis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ie.domis.domain.Job;
import ie.domis.domain.User;

public interface JobDAO extends JpaRepository<Job, Integer> {
	
	boolean existsById(int id);
	List<Job> findAllByOwner(User user);
	List<Job> findAllByActive(boolean active);
	
	@Query(value="SELECT jobId FROM job", nativeQuery=true)
	List<Integer> findAllJobIds();
	
	@Query(value="SELECT * FROM job WHERE LOWER(name) LIKE %:phrase% OR LOWER(description) LIKE %:phrase%", nativeQuery=true)
	List<Job> findAllJobsWhichContainPhrase(@Param("phrase") String phrase);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE job SET name= :name WHERE jobId= :id", nativeQuery= true)
	int updateJobName(@Param("id") int id, @Param("name") String name);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE job SET description= :description WHERE jobId= :id", nativeQuery= true)
	int updateJobDescription(@Param("id") int id, @Param("description") String description);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE job SET owner= :owner WHERE jobId= :id", nativeQuery= true)
	int updateJobOwner(@Param("id") int id, @Param("owner") User owner);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE job SET active= :active WHERE jobId= :id", nativeQuery= true)
	int updateJobActive(@Param("id") int id, @Param("active") boolean active);

}
