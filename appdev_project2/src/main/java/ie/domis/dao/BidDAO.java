package ie.domis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;

public interface BidDAO extends JpaRepository<Bid, Integer> {
	
	List<Bid> findAllBidsByBidder(User bidder);
	List<Bid> findAllBidsByJob(Job job);
	
	@Query(value="SELECT bidId FROM bid", nativeQuery=true)
	List<Integer> findAllBidIds();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE bid SET bidder= :bidder WHERE bidId= :id", nativeQuery= true)
	int updateBidderInBidById(@Param("bidder") User bidder, @Param("id") int id);

	@Modifying
	@Transactional
	@Query(value="UPDATE bid SET job= :job WHERE bidId= :id", nativeQuery= true)
	int updateJobInBidById(@Param("job") Job job, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE bid SET value= :value WHERE bidId= :id", nativeQuery= true)
	int updateValueInBidById(@Param("value") float value, @Param("id") int id);
	
	@Query(value="SELECT * FROM bid WHERE (job, value) IN (SELECT job, MIN(value)  FROM bid WHERE job= :jobId GROUP BY job);", nativeQuery= true)
	Bid getMinBidForJob(@Param("jobId") int jobId);
	
	@Query(value="SELECT EXISTS(SELECT bidId FROM bid WHERE job= :jobId)", nativeQuery= true)
	boolean existsJob(@Param("jobId") int jobId);

}
