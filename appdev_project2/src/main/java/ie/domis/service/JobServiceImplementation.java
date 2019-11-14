package ie.domis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.domis.dao.JobDAO;
import ie.domis.domain.Job;
import ie.domis.domain.User;

@Service
public class JobServiceImplementation implements JobService {
	
	@Autowired
	JobDAO dao;

	@Override
	public Job findJobById(int id) {
		if (isJobInDatabase(id)) {
			return dao.findById(id).get();
		}
		return null;
	}

	@Override
	public List<Job> findAllJobs() {
		return dao.findAll();
	}

	@Override
	public List<Job> findAllOwnersJobs(int userId) {
		User u = new User();
		u.setUserId(userId);
		return dao.findAllByOwner(u);
	}

	@Override
	public List<Job> findAllActiveJobs() {
		return dao.findAllByActive(true);
	}

	@Override
	public List<Integer> findAllJobIds() {
		return dao.findAllJobIds();
	}

	@Override
	public Job addJob(Job job) {
		if (isJobInDatabase(job.getJobId())) {
			return null;
		}
		return dao.save(job);
	}

	@Override
	public boolean removeJob(int id) {
		if (isJobInDatabase(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateJobName(int id, String name) {
		if (isJobInDatabase(id)) {
			return dao.updateJobName(id, name) == 1;
		}
		return false;
	}

	@Override
	public boolean updateJobDescription(int id, String description) {
		if (isJobInDatabase(id)) {
			return dao.updateJobDescription(id, description) == 1;
		}
		return false;
	}

	@Override
	public boolean updateJobOwner(int id, User owner) {
		if (isJobInDatabase(id)) {
			return dao.updateJobOwner(id, owner) == 1;
		}
		return false;
	}

	@Override
	public boolean updateJobActive(int id, boolean active) {
		if (isJobInDatabase(id)) {
			return dao.updateJobActive(id, active) == 1;
		}
		return false;
	}

	@Override
	public boolean isJobInDatabase(int id) {
		return dao.existsById(id);
	}

}
