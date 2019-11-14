package ie.domis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bidId;
	
	@ManyToOne
	@JoinColumn(name="bidder")
	private User bidder;
	
	@ManyToOne
	@JoinColumn(name="job")
	private Job job;
	
	@Column(nullable=false)
	private float value;
	
	public Bid(User bidder, Job job, float value) {
		this.bidder = bidder;
		this.job = job;
		this.value = value;
	}
	
	public String toString() {
		String output = "Bid: ";
		if (this.bidId != 0) {
			output += this.bidId;
		}
		output += "\n\tBidder: " + bidder.getEmail() + "\n\tJob: " + job.getJobId();
		return output;
	}
	
}
