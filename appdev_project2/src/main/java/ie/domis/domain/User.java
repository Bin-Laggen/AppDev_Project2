package ie.domis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int userId;

	@Column(nullable=false, unique=true)
	@Email
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false, unique=true)
	private int phoneNumber;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@OneToOne
	@JoinColumn
	Role userRole;

	public User(String email, String password, String name, String surname, int phoneNumber, Role role, boolean enabled) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.userRole = role;
		this.enabled = enabled;
	}
	
	public String toString() {
		String output = "User: ";
		if (this.userId != 0) {
			output += this.userId;
		}
		output += "\n\tEmail: " + this.email + "\n\tPassword: " + this.password + "\n\tName: " + this.name + "\n\tSurname: " + this.surname + "\n\tPhone: " + this.phoneNumber + "\n\tActive: ";
		if (this.enabled) {
			output += "Yes";
		} else {
			output += "No";
		}
		return output;
	}
	
}
