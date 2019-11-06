package ie.domis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	@GeneratedValue
	private int jobId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private LocalDate date;
	
	public Job(String name, String description, LocalDate date) {
		this.name = name;
		this.description = description;
		this.date = date;
	}

}
