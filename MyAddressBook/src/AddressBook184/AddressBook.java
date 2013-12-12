package AddressBook184;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="AddressBook")
public class AddressBook {
	@ElementList(inline=true)
	private List<Person> contacts;
	
	/**	
	 	Create a new Address Book
	 */	
	public AddressBook(){
		contacts = new ArrayList<Person>();
	}
	
	/**	
 		Adds Contact to the Address Book
	*/		
	public boolean add(Person aContact){
		return contacts.add(aContact);
	}

	/**	
		Removes Contact from the Address Book
	*/		
	public boolean remove(Person aContact){
		return contacts.remove(aContact);
	}

	/**	
		Get all Contacts in the Address Book
	*/	
	public List<Person> getContacts(){
		return contacts;
	}
	
	/**	
		Find all Contacts in the Address Book that contains the specified text
	*/		
	public List<Person> findText(String text){
		return null;
	}
}
