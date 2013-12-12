package AddressBook184;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class AddNew extends JDialog implements ActionListener{
	private Person contact;
	private OKButtonClickListener listener;
	private JPanel buttonPane;
	private JTextField FirstName;
	private JTextField MiddleName;
	private JTextField LastName;
	private JTextField Street;
	private JTextField City;
	private JTextField ZipCode;
	private JTextField ContactNumber;

	public void setOKButtonClickListener(OKButtonClickListener listener) {
		this.listener = listener;
	}

	/**
	 	Create and show the "Add/Edit Contact" dialog.
	 */
	public static void createDialog(Component parent, Person contact, OKButtonClickListener listener){
		try {
			AddNew dialog = new AddNew(contact);
			dialog.setOKButtonClickListener(listener);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setPreferredSize(new Dimension(400, 220));
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setLocationRelativeTo(parent);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 	Create the dialog.
	 */
	public AddNew(Person aContact) {
		setBounds(100, 100, 450, 339);
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 267, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("ADD CONTACT");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		getContentPane().setLayout(null);
		getContentPane().add(buttonPane);
		{
			JLabel lblFirstName = new JLabel("First Name : ");
			lblFirstName.setBounds(33, 42, 74, 14);
			getContentPane().add(lblFirstName);
		}
		{
			FirstName = new JTextField();
			FirstName.setBounds(121, 39, 278, 20);
			getContentPane().add(FirstName);
			FirstName.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name : ");
			lblMiddleName.setBounds(33, 70, 74, 14);
			getContentPane().add(lblMiddleName);
		}
		{
			MiddleName = new JTextField();
			MiddleName.setBounds(121, 67, 278, 20);
			getContentPane().add(MiddleName);
			MiddleName.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name :");
			lblLastName.setBounds(33, 101, 74, 14);
			getContentPane().add(lblLastName);
		}
		{
			LastName = new JTextField();
			LastName.setBounds(121, 98, 278, 20);
			getContentPane().add(LastName);
			LastName.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("Street :");
			lblStreet.setBounds(33, 132, 46, 14);
			getContentPane().add(lblStreet);
		}
		{
			Street = new JTextField();
			Street.setBounds(121, 129, 278, 20);
			getContentPane().add(Street);
			Street.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("City :");
			lblCity.setBounds(33, 161, 46, 14);
			getContentPane().add(lblCity);
		}
		{
			City = new JTextField();
			City.setBounds(121, 158, 278, 20);
			getContentPane().add(City);
			City.setColumns(10);
		}
		{
			JLabel lblZipCode = new JLabel("Zip Code :");
			lblZipCode.setBounds(33, 186, 74, 14);
			getContentPane().add(lblZipCode);
		}
		{
			ZipCode = new JTextField();
			ZipCode.setBounds(121, 183, 86, 20);
			getContentPane().add(ZipCode);
			ZipCode.setColumns(10);
		}
		{
			JLabel lblContactNumber = new JLabel("Contact Number :");
			lblContactNumber.setBounds(33, 211, 86, 14);
			getContentPane().add(lblContactNumber);
		}
		{
			ContactNumber = new JTextField();
			ContactNumber.setBounds(121, 208, 278, 20);
			getContentPane().add(ContactNumber);
			ContactNumber.setColumns(10);
		}
		
		if(aContact == null){
			contact = new Person();
			setTitle("Add New Contact");
		}
		else{
			contact = aContact;
			updateDialog();
			setTitle("Edit Contact");
		}
	}
	
	public interface OKButtonClickListener{
		public void onOKButtonClick(Person contact);
	}

	/**
 	 	Handles the OK Button
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		updateContact();
		if(listener != null)
			listener.onOKButtonClick(contact);
		dispose();
	}
	
	/**
	 	Synchronizes the fields of the Contact with that of the dialog
	 */
	public void updateContact(){
		contact.setFirstName(FirstName.getText());
		contact.setMiddleName(MiddleName.getText());
		contact.setLastName(LastName.getText());
		contact.setStreet(Street.getText());
		contact.setCity(City.getText());
		contact.setZipCode(ZipCode.getText());
		contact.setContactNumber(ContactNumber.getText());

	}
	
	/**
 		Synchronizes the fields of the dialog with that of the Contact
	*/
	public void updateDialog(){
		FirstName.setText(contact.getFirstName());
		MiddleName.setText(contact.getMiddleName());
		LastName.setText(contact.getLastName());
		Street.setText(contact.getStreet());
		City.setText(contact.getCity());
		ZipCode.setText(contact.getZipCode());
		ContactNumber.setText(contact.getContactNumber());
	}
	
	}
