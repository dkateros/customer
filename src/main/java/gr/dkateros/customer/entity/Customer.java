package gr.dkateros.customer.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import gr.dkateros.util.jee7.data.Identified;

@Entity
@NamedQueries({
	@NamedQuery(name=Customer.LIST_ALL, query="select object(c) from Customer c"),
})
public class Customer implements Identified {
	
	public static final String LIST_ALL = "allCustomers";
	
	@Id
	@SequenceGenerator(name="customer_seq", sequenceName="customer_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	Long id;
	
	@NotNull
	String firstName;
	
	@NotNull
	String lastName;
	
	@Email
	String email;
	
	@NotNull @Past
	LocalDate birthDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

}
