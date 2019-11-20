package ie.domis.service;

import java.util.List;

import ie.domis.domain.Job;
import ie.domis.domain.User;

public interface JobService {

	Job findJobById(int id);
	List<Job> findAllJobs();
	List<Job> findAllOwnersJobs(int userId);
	List<Job> findAllActiveJobs();
	List<Integer> findAllJobIds();
	Job addJob(Job job);
	boolean removeJob(int id);
	boolean updateJobName(int id, String name);
	boolean updateJobDescription(int id, String description);
	boolean updateJobOwner(int id, User owner);
	boolean updateJobActive(int id, boolean active);
	boolean isJobInDatabase(int id);
	List<Job> findJobsContainingPhrase(String phrase);
	
}
