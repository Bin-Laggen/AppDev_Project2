package ie.domis.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BidForm {
	
	@Digits(integer = 3, fraction = 0)
	private int jobId;

	@Digits(integer = 3, fraction = 0)
	private int bidderId;
	
	@Digits(integer = 6, fraction = 2)
	@Min(value = 1)
	private float value;
	
	public String toString() {
		return "jobId: " + this.jobId + " bidderId: " + this.bidderId + " value: " + this.value;
	}

}
