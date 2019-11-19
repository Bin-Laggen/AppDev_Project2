package ie.domis.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserForm {
	
	@Size(min = 5, max = 64)
	private String email;
	
	@Size(min = 8, max = 32)
	private String password;

	@Size(min = 8, max = 32)
	private String password2;
	
	@Size(min = 2, max = 15)
	private String name;
	
	@Size(min = 2, max = 20)
	private String surname;
	
	@Digits(fraction = 0, integer = 10)
	@NotNull
	@Min(value = 100000000)
	private int phoneNumber;

}
