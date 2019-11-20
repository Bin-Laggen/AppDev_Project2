package ie.domis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.domis.dao.BidDAO;
import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;

@Service
public class BidServiceImplementation implements BidService {
	
	@Autowired
	BidDAO dao;
	
	@Autowired
	JobService jobService;

	@Override
	public List<Bid> findAll() {
		return dao.findAll();
	}

	@Override
	public Bid findById(int id) {
		if (isBidInDatabase(id)) {
			return dao.findById(id).get();
		}
		return null;
	}

	@Override
	public List<Bid> findAllBidsByBidder(int id) {
		User user = new User();
		user.setUserId(id);
		return dao.findAllBidsByBidder(user);
	}

	@Override
	public List<Bid> findAllBidsByJob(int id) {
		Job job = new Job();
		job.setJobId(id);
		return dao.findAllBidsByJob(job);
	}

	@Override
	public List<Integer> findAllBidIds() {
		return dao.findAllBidIds();
	}

	@Override
	public Bid addBid(Bid bid) {
		if (isBidInDatabase(bid.getBidId())) {
			return null;
		}
		Bid minBid = getMinBidForJob(bid.getJob().getJobId());
		if (minBid != null && (bid.getValue() >= minBid.getValue() || bid.getBidder().getUserId() == minBid.getBidder().getUserId())) {
			return null;
		}
		return dao.save(bid);
	}

	@Override
	public boolean removeBid(int id) {
		if (isBidInDatabase(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBidBidder(int id, User bidder) {
		if (isBidInDatabase(id)) {
			String jobOwner = dao.findById(id).get().getJob().getOwner().getEmail();
			if (!bidder.getEmail().equalsIgnoreCase(jobOwner)) {
				return dao.updateBidderInBidById(bidder, id) == 1;
			}
		}
		return false;
	}

	@Override
	public boolean updateBidJob(int id, Job job) {
		if (isBidInDatabase(id)) {
			return dao.updateJobInBidById(job, id) == 1;
		}
		return false;
	}

	@Override
	public boolean updateBidValue(int id, float value) {
		if (isBidInDatabase(id)) {
			return dao.updateValueInBidById(value, id) == 1;
		}
		return false;
	}

	@Override
	public boolean isBidInDatabase(int id) {
		return dao.existsById(id);
	}

	@Override
	public boolean doesJobHaveBids(int jobId) {
		return dao.existsJob(jobId);
	}

	@Override
	public Bid getMinBidForJob(int jobId) {
		if (doesJobHaveBids(jobId)) {
			return dao.getMinBidForJob(jobId);
		}
		return null;
	}

}
