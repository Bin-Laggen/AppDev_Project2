package ie.domis.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private LocalDateTime date;
	
	@Column(nullable=false)
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	@OneToMany(mappedBy = "job")
	private List<Bid> bids;
	
	public Job(String name, String description, LocalDateTime date, boolean active, User owner) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.active = active;
		this.owner = owner;
	}
	
	public Job(String name, String description, User owner) {
		this.name = name;
		this.description = description;
		this.date = LocalDateTime.now();
		this.active = true;
		this.owner = owner;
	}
	
	public String toString() {
		String output = "Job: ";
		if (this.jobId != 0) {
			output += this.jobId;
		}
		output += "\n\tName: " + this.name + "\n\tDescription: " + this.description + "\n\tOwner: " + this.owner.getEmail() + "\n\tDate Created: " + this.date.toString() + "\n\tActive: ";
		if (this.active) {
			output += "Yes";
		} else {
			output += "No";
		}
		return output;
	}

}
