package AddressBook184;

import java.util.Comparator;

public class sortbyZipComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {

		return person1.getZipCode().compareTo(person2.getZipCode());
	}
	

}
