package ie.domis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;

public interface BidDAO extends JpaRepository<Bid, Integer> {
	
	List<Bid> findAllBidsByBidder(User bidder);
	List<Bid> findAllBidsByJob(Job job);

}
