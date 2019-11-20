package ie.domis.forms;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SearchForm {
	
	@Size(min = 2)
	private String searchPhrase;

}
