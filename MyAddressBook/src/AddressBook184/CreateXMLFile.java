package AddressBook184;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class CreateXMLFile {

	/**	
		Save Address Book to an XML file
	*/
	public static void saveAsXML(AddressBook addressBook, File aFile) throws Exception {
		Serializer serializer = new Persister();
		serializer.write(addressBook, aFile);
	}

	/**	
		Read an Address Book from an XML file
	*/
	public static AddressBook readFromXML(File aFile) throws Exception {
		Serializer serializer = new Persister();
		AddressBook addressBook = null;
		addressBook = serializer.read(AddressBook.class,aFile);
		return addressBook;
	}
}
