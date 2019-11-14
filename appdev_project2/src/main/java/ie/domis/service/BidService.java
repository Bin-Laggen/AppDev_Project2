package ie.domis.service;

import java.util.List;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;

public interface BidService {
	
	List<Bid> findAll();
	Bid findById(int id);
	List<Bid> findAllBidsByBidder(int id);
	List<Bid> findAllBidsByJob(int id);
	List<Integer> findAllBidIds();
	Bid addBid(Bid bid);
	boolean removeBid(int id);
	boolean updateBidBidder(int id, User bidder);
	boolean updateBidJob(int id, Job job);
	boolean updateBidValue(int id, float value);
	boolean isBidInDatabase(int id);

}
