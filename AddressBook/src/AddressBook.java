import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBook {
	
	
	
	
	//creates file
	public static void createFile(String fileName) {
	    try {
	        File file = new File(fileName);
	        if (!file.exists()) {
	            file.createNewFile();
	        } else {
	            FileOutputStream writer = new FileOutputStream(fileName);
	            writer.write(("").getBytes());
	            writer.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void readFile(String fileName, ArrayList<Contact> al)throws FileNotFoundException{
		
	    try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String line = br.readLine();
	        while(line != null) {
	        	String[] metadata = line.split(",");
	        	al.add(new Contact(metadata[0], metadata[1], metadata[2], 
	    				Integer.parseInt(metadata[3])));
	        	line = br.readLine();
	        }
	        
	    } catch (IOException e) {
	      System.out.println("SOMETHINGS IS MESSED UP, OOPS!");
	    }
	}
		

	//1. Add a new contact 
	public static ArrayList<Contact> addContact(ArrayList<Contact> contacts) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the following: [First Name], [Last Name], [Street Address],"
				+ " [Phone Number (numbers only)]");
		try {	
			String fname = sc.next();
			String lname = sc.next();
			String address = sc.next();
			int phoneNumber = Integer.parseInt(sc.next());
			
			try {
			contacts.add(new Contact(fname, lname, address, phoneNumber));
			} catch (Exception e) {
				System.out.println(e.getMessage() + "\nFailed to create new contact");
			}
			
		}catch (InputMismatchException e) {
			System.out.println("Improper input detected; cancelling request");
		}
		sc.close();	
		return contacts;
	}

	//2. Update an existing contact
	//2a
	public void updateContactByName(ArrayList<Contact> contacts, String fName, String lname) {
		//TODO
	}
	//2b
	public void updateContactByNumber() {
		//TODO
	}
	//2c
	public void updateContactByAddress(String oldAddress , String newAddress) {
		//TODO
	}

	//3. Delete a contact for every instance of the full name and returns updated contact
	public static ArrayList<Contact> deleteContact(String FirstName, String LastName, ArrayList<Contact> contacts) {
		String fullName = FirstName + " "+ LastName;
		for(int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getFullName() == fullName) {
				contacts.remove(i);
			}
		}
		return contacts;
	}

	//4. List all added contacts in sorted order
	public static void displayContacts(ArrayList<Contact> contacts) {
		System.out.println("Current Contacts List:\n------------------\n");
		for (Contact c : contacts) {
			c.toString();
		}
	}

	//5. Search for a given contact. Search can be done by first name, last name, or phone number. 
	//   Return all the details upon finding a match.
	public static String searchContact(int searchCategory, ArrayList<Contact> contacts) {
		Scanner search = new Scanner(System.in);
		String input = search.next();
		search.close();
		
		// Search by first name
		if (searchCategory == 1) {
			for(int i = 0; i < contacts.size(); i++) {
				if(contacts.get(i).getfName() == input) {
					return contacts.get(i).toString();
				}
			}
		}
		
		//Search by last name
		else if(searchCategory == 2) {
			for(int i = 0; i < contacts.size(); i++) {
				if(contacts.get(i).getlName() == input) {
					return contacts.get(i).toString();
				}
			}
		}
		//Search by phone number
		else if (searchCategory == 3) {
			for(int i = 0; i < contacts.size(); i++) {
				if(contacts.get(i).getPhoneNumber() == Integer.parseInt(input)) {
					return contacts.get(i).toString();
				}
			}
		}
		//Invalid entry
		return "Improper input, cancelling request.";
		
	}
	
	//6. Saves items in array list into .CSV file
	public static void save() {
		
		
	}
	
	//7. Save and Quit
	public static void quit() {
		//TODO
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		createFile("AddressBook.csv");
		
		
		System.out.println("AddressBook.txt is loaded.\n"
				+ "Please save and quit properly when you are done to avoid losing unsaved data!");
		readFile("AddressBook.csv", contacts);
		
		System.out.println("Please Choose (1-7) from the following options: ");
		System.out.println("-----------------------------------------");
		boolean wantsToQuit = false;
		
		
		while(wantsToQuit) {
			
			System.out.println(
					 "1. Add a new contact\n"
					+"2. Update an existing contact\n"
					+"3. Delete a contact\n"
					+"4. List all added contact in sorted order by first name"
					+"5. Search for first instance of a contact by first name, last name, or phone number"
					+"6. Save data "
					+"7. Save and quit"
			);
			Scanner sc = new Scanner(System.in);
			try {
			
				switch (sc.nextInt()) {
					case 1:
						contacts = addContact(contacts);
						contacts.sort(Contact::compareTo); 
						break;
						
					case 2:
						//TODO
						break;
						
					case 3:
						System.out.println("Enter the first and last name of the contact");
						try {
							String firstName = sc.next();
							String lastName = sc.next();
							
							contacts = deleteContact(firstName, lastName, contacts);
							
						}catch(Exception e) {
							System.out.println(e.getMessage()+ "Failed to delete. Please try again");
						}
						break;
						
					case 4:
						displayContacts(contacts);
						break;
						
					case 5:
						System.out.println("Choose (1) to search using first name, (2) for last name, or (3) by phone number:");
						try {
							System.out.println("Search Result: \n" + searchContact(sc.nextInt(), contacts));
							
						}catch (InputMismatchException e) {
								System.out.println("Improper Input detected. Cancelling request.");
							}
							
						
						break;
					case 6:
						break;
					case 7:
						break;
				
				}
			
			}catch (InputMismatchException e) {
				System.out.println("Improper Input detected, please try again and choose an option (1-7)");
				
			}
			
			
			sc.close();
		}
		
		
	}
	
	
}
