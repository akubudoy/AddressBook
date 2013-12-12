package AddressBook184;

import javax.swing.JTextField;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Contact")
public class Person implements Comparable<Person> {
	
	@Element(required=false)
	private String firstName;
	@Element(required=false)
	private String middleName;
	@Element(required=false)
	private String lastName;
	@Element(required=false)
	private String contactNumber;
	@Element(required=false)
	private String address;
	@Element(required=false)
	private String city;
	@Element(required=false)
	private String zipCode;
	@Element(required=false)
	private String country;
	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getStreet() {
		return address;
	}


	public void setStreet(String street) {
		this.address = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
	

	public String toString(){
		StringBuilder value = new StringBuilder();
		value.append(lastName);
		value.append(", ");
		value.append(firstName);
		value.append("          ");
		value.append(contactNumber);
		value.append("          ");
		value.append(address);
		value.append(",  ");
		value.append(city);
		value.append("          ");
		value.append(zipCode);
		

		return value.toString();
	}


	@Override
	public int compareTo(Person personB) {
		return lastName.compareTo(personB.getLastName());
	}




}