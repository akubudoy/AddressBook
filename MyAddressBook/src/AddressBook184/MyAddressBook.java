package AddressBook184;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class MyAddressBook {

	private boolean notSaved;
	private JFrame frmAddressBook;
	private DefaultListModel<Person> mModel;
	private JList<Person> table;
	private JScrollPane scrollPane;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private File openedFile;
	private JTextField keyword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyAddressBook window = new MyAddressBook();
					window.frmAddressBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyAddressBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddressBook = new JFrame();
		frmAddressBook.setTitle("CSC 184 - Address Book");
		frmAddressBook.setBounds(100, 100, 450, 475);
		frmAddressBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		
		JLabel lblSortBy = new JLabel("Sort by");
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeSortbyName();
			}
		});
		
		JRadioButton rdbtnZipCode = new JRadioButton("Zip Code");
		rdbtnZipCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeSortbyZip();
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnName);
		group.add(rdbtnZipCode);
		
		keyword = new JTextField();
		keyword.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeSearch();
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmAddressBook.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSortBy)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnZipCode)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(keyword, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSortBy)
						.addComponent(rdbtnName)
						.addComponent(rdbtnZipCode)
						.addComponent(btnNewButton)
						.addComponent(keyword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);

		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeAddContact();
			}
		});
		panel.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeEditContact();
			}
		});
		panel.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeDeleteContact();
			}
		});
		panel.add(btnDelete);
		frmAddressBook.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmAddressBook.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeNew();
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeOpen();
			}
		});
		mnFile.add(mntmOpen);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeSave();
			}
		});
		mntmSave.setEnabled(false);
		mnFile.add(mntmSave);

		mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setEnabled(false);
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeSaveAs();
			}
		});
		mnFile.add(mntmSaveAs);

		JMenuItem mntmQuit = new JMenuItem("Exit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeQuit();
			}
		});
		mnFile.add(mntmQuit);
		
		frmAddressBook.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddressBook.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        executeQuit();
		    }
		});
	}

	/**
	 * Create a new Address Book
	 */
	private void executeNew() {
		initializeList();
		enableComponents(true);
		notSaved = false;
	}
	
	/**
	 * Shows the JFileChooser and opens an Address Book from the selected File
	 */
	private void executeOpen() {
			JFileChooser openChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Address Book XML file (*.xml)", "xml");
			openChooser.setFileFilter(filter);
			int returnVal = openChooser.showOpenDialog(frmAddressBook);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = openChooser.getSelectedFile();
				AddressBook addressBook;
				try {
					addressBook = CreateXMLFile.readFromXML(file);
					initializeList();
					for (Person contact : addressBook.getContacts()) {
						mModel.addElement(contact);
					}
					enableComponents(true);
					openedFile = file;
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(frmAddressBook, e.toString(),
									"Error reading xml file",
									JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}
	
	/**
	 *  Save Address Book
	 */
	private boolean executeSave() {
		if (openedFile == null) {
			return executeSaveAs();
		} else {
			return saveAddressBook(openedFile);
		}
	}
	
	/**
	 * Shows the JFileChooser and saves the current Address Book to the selected
	 * File
	 */
	private boolean executeSaveAs() {
		JFileChooser saveChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Address Book XML file (*.xml)", "xml");
		saveChooser.setFileFilter(filter);
		saveChooser.setDialogTitle("Save As");
		int returnVal = saveChooser.showSaveDialog(frmAddressBook);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = saveChooser.getSelectedFile();
			String name = selectedFile.getName();
			if (!name.endsWith(".xml"))
				selectedFile = new File(selectedFile.getParent(),
						name.concat(".xml"));
			saveAddressBook(selectedFile);
		}
		else{
			return false;
		}
		return true;
	}
	
	/**
	 * Closes the application
	 */
	private void executeQuit() {
			frmAddressBook.dispose();
	}


	/**
	 * Shows the Add Contact dialog
	 */
	private void executeAddContact() {
		AddNew.createDialog(frmAddressBook, null,
				new AddNew.OKButtonClickListener() {

					@Override
					public void onOKButtonClick(Person contact) {
						mModel.addElement(contact);
						notSaved = true;
					}
				});
	}

	/**
	 * Shows the Edit Contact dialog
	 */
	private void executeEditContact() {
		final int selectedIndex = table.getSelectedIndex();
		Person selectedContact = table.getSelectedValue();
		AddNew.createDialog(frmAddressBook, selectedContact,
				new AddNew.OKButtonClickListener() {

					@Override
					public void onOKButtonClick(Person contact) {
						mModel.set(selectedIndex, contact);
						notSaved = true;
					}
				});
	}
	
	
	/**
	 * Removes the currently selected Contact
	 */
	private void executeDeleteContact() {
		final int selectedIndex = table.getSelectedIndex();
		mModel.remove(selectedIndex);
		notSaved = true;
	}
	
	/**
	 * Searches the keyword on the list.
	 */
	
	private void executeSearch(){
		String key = keyword.getText().toLowerCase();
		List<Person> persons = new ArrayList<Person>();
		Enumeration<Person> enumeration = mModel.elements();
		int i=0;
		while(enumeration.hasMoreElements()){
			Person person = enumeration.nextElement();
			String lastName = person.getLastName().toLowerCase();
			String firstName = person.getFirstName().toLowerCase();
			if(lastName.startsWith(key))
			{
				table.setSelectedIndex(i);
				JOptionPane.showMessageDialog(frmAddressBook, person.getLastName(),
						"Matches Found", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(firstName.startsWith(key))
			{
				table.setSelectedIndex(i);
				JOptionPane.showMessageDialog(frmAddressBook, person.getFirstName(),
						"Matches Found", JOptionPane.INFORMATION_MESSAGE);
			}

			i++;
			
		}
	}
	
	/**
	*Sorts the Entries by Name
	**/
	
	private void executeSortbyName(){
		List<Person> persons = new ArrayList<Person>();
		Enumeration<Person> enumeration = mModel.elements();
		while(enumeration.hasMoreElements()){
			persons.add(enumeration.nextElement());
		}
		Collections.sort(persons);
		mModel = new DefaultListModel<Person>();
		table.setModel(mModel);
		for(Person person : persons)
			mModel.addElement(person);
	}
	
	/**
	 * Sorts the Entries by ZIP
	 * */
	private void executeSortbyZip(){
	
		List<Person> persons = new ArrayList<Person>();
		Enumeration<Person> enumeration = mModel.elements();
		while(enumeration.hasMoreElements()){
			persons.add(enumeration.nextElement());
		}
		
		Collections.sort(persons, new sortbyZipComparator());
		mModel = new DefaultListModel<Person>();
		table.setModel(mModel);
		for(Person person : persons)
			mModel.addElement(person);
}
	
	
	/**
	 * Saves the Address Book to the selected File
	 */
	private boolean saveAddressBook(File selectedFile) {
		Enumeration<Person> contactsEnumeration = mModel.elements();
		AddressBook addressBook = new AddressBook();
		while (contactsEnumeration.hasMoreElements()) {
			addressBook.add(contactsEnumeration.nextElement());
		}
		try {
			CreateXMLFile.saveAsXML(addressBook, selectedFile);
			notSaved = false;
			mntmSave.setEnabled(false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddressBook, e.getMessage(),
					"Error writing xml file", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * Initialize a ListModel and use it as parameter to create a Contacts JList
	 */
	private void initializeList() {
		mModel = new DefaultListModel<Person>();
		table = new JList<Person>(mModel);
		scrollPane.setViewportView(table);
	}

	/**
	 * Enable components that are only available when a file is opened
	 */
	private void enableComponents(boolean enable) {
		mntmSave.setEnabled(enable);
		mntmSaveAs.setEnabled(enable);
		btnAdd.setEnabled(enable);
		btnEdit.setEnabled(enable);
		btnDelete.setEnabled(enable);
	}
	
}
