package ie.domis.forms;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class JobForm {
	
	private int ownerId;
	
	@Size(min = 4, max = 24)
	private String name;
	
	@Size(min = 4, max = 64)
	private String description;
	
	public String toString() {
		return "Owner: " + this.ownerId + " Name: " + this.name + " Description: " + this.description;
	}

}
