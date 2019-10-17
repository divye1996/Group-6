package com.cg.ibs.spmgmt.ui;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.cg.ibs.spmgmt.bean.BankAdmin;
import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.exception.IBSException;
import com.cg.ibs.spmgmt.exception.RegisterException;
import com.cg.ibs.spmgmt.service.ServiceProviderService;
import com.cg.ibs.spmgmt.service.ServiceProviderServiceImpl;

public class Application {
	// Main-------------------------------------------------------
	public static void main(String[] args) {
		Application application = new Application();
		Scanner scanner = new Scanner(System.in);
		BankAdmin admin = new BankAdmin();
		int switchInput = 0;
		boolean exitTrigger = true;
int abc = 1;
		// Keep Showing the menu until exit button pressed
		do {
			ServiceProviderService service = new ServiceProviderServiceImpl();
			ServiceProvider serviceProvider = new ServiceProvider();
			switchInput = application.menu(scanner);
			switch (switchInput) {
			case 1:
				// Takes User Input for all details-->performs basic input
				// checks
				application.registerServiceProvider(scanner, service, serviceProvider);
				application.returnToMainMenu(scanner);
				break;
			case 2:
				// Takes login ID and password--> checks validity-->shows
				// status/SPI if approved
				try {
					application.loginMethod(scanner, service, serviceProvider);
				} catch (IBSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				application.returnToMainMenu(scanner);
				break;
			case 3:
				// BankAdmin Login
				application.bankAdmin(scanner, admin, service);
				// Getting the list of pending Service Providers in Database
				ArrayList<ServiceProvider> serviceProviders;
				try {
					serviceProviders = service.showPending();
					// Getting the Request History from database
					ArrayList<ServiceProvider> serviceProviders2 = service.showHistory();
					// Showing Details-->Setting Approval status
					application.bankMethod(serviceProviders, serviceProviders2, service, scanner);
				} catch (IBSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 4:
				// Exit from Application
				exitTrigger = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		} while (exitTrigger);
	}

	// Methods
	// Display Main Menu
	private int menu(Scanner scanner) {
		System.out.println("Welcome to Service Provider Portal");
		System.out.println("Select an option below (1/2/3/4):");
		System.out.println("1. New Registration");
		System.out.println("2. Login");
		System.out.println("3. Bank Administrator Login");
		System.out.println("4. Exit");
		// Ensuring input matches the menu
		while (!scanner.hasNextInt()) {
			scanner.next();
			scanner.nextLine();
			System.out.println("Invalid Input! Please Enter a Number: 1 2 3 4");
		}
		int returnInput = scanner.nextInt();
		scanner.nextLine();
		return returnInput;
	}

	// Register a user
	private void registerServiceProvider(Scanner scanner, ServiceProviderService service,
			ServiceProvider serviceProvider) {
		serviceProvider.setNameOfCompany(takeNameInput(scanner));
		try {
			// Generates Unique ID & Password
			serviceProvider = service.generateIdPassword(serviceProvider);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		System.out.println("Note Down the ID & Password for future logins:");
		System.out.println("User ID:" + serviceProvider.getUserId());
		System.out.println("Password: " + serviceProvider.getPassword());
		// Take Details with basic pattern matching
		serviceProvider = getKYC(scanner, serviceProvider);
		try {
			// store the object into database
			service.storeSPDetails(serviceProvider);
			System.out.println("\nREGISTRATION COMPLETE. APPROVAL REQUEST SENT TO BANK! "
					+ "\nLOGIN FROM MAIN MENU TO CHECK STATUS OF REQUEST \n");
		} catch (RegisterException exception) {
			System.out.println("------------------------------------------------------ \n" + exception.getMessage()
			+ "\n------------------------------------------------------");
		} catch (IBSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Input character length check
	private String takeNameInput(Scanner scanner) {
		String string = "";
		String namePattern = "[A-Z,a-z]{5}[ ,A-Z,a-z,0-9]{0,}";
		// Keep Taking User Input until it matches the pattern
		do {
			System.out.println("Enter Name of the Company(Minimum 5 Characters with no Spaces)");
			string = scanner.nextLine();
		} while (!string.matches(namePattern));
		return string;
	}

	// Taking KYC details and doing basic input checks to match standard
	// patterns
	private ServiceProvider getKYC(Scanner scanner, ServiceProvider serviceProvider) {
		boolean bn = false;
		String st = "";
		String gstinPattern = "[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][0-9][A-Z][0-9,A-Z]";
		String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]";
		String acNumberPattern = "[1-9][0-9]{9,14}";
		String phoneNumberPattern = "[1-9][0-9]{9}";
		System.out.println("\n------Enter the Necessary Details----- ");

		System.out.println("Enter GST Number(16 Characters digit or uppercase alphabets): \nEx. 35AABCD1429B1ZX");
		do {
			st = scanner.next();
			scanner.nextLine();
			bn = !Pattern.matches(gstinPattern, st);
			if (bn) {
				System.out.println("Enter valid number(16 Characters-digits/uppercase letters)");
			}
		} while (bn);
		serviceProvider.setGstin(st);
		System.out.println("Enter PAN(10 Characters-digits/uppercase letters): \nEx: AAAPL1234C ");
		do {
			st = scanner.next();
			scanner.nextLine();
			bn = !Pattern.matches(panPattern, st);
			if (bn) {
				System.out.println("Enter valid PAN(10 Characters-digits/uppercase letters)");
			}
		} while (bn);
		serviceProvider.setPanNumber(st);
		System.out.println("Enter Bank Account Number(10-14 digits): ");
		do {
			st = scanner.next();
			scanner.nextLine();
			bn = !Pattern.matches(acNumberPattern, st);
			if (bn) {
				System.out.println("Enter a valid Account number(10-14 digits)");
			}
		} while (bn);
		serviceProvider.setAccountNumber((new BigInteger(st)));
		System.out.println("Enter Name of the Bank: ");
		serviceProvider.setBankName(scanner.nextLine());
		System.out.println("Enter Company Address: ");
		serviceProvider.setCompanyAddress(scanner.nextLine());
		System.out.println("Enter Contact Number (10 digits): ");
		do {
			st = scanner.next();
			scanner.nextLine();
			bn = !Pattern.matches(phoneNumberPattern, st);
			if (bn) {
				System.out.println(
						"Enter 10 digit phone number that doesn't start with 0(No special characters like + or -)");
			}
		} while (bn);
		serviceProvider.setMobileNumber(new BigInteger(st));
		printDetails(serviceProvider);
		return serviceProvider;
	}

	// BankAdmin Login Inputs
	private void bankAdmin(Scanner scanner, BankAdmin admin, ServiceProviderService service) {
		boolean exitTrig = true;
		// Taking Inputs until login succeeds
		do {
			System.out.println("Enter Admin ID: ");
			admin.setAdminID(scanner.next());
			scanner.nextLine();
			System.out.println("Enter Admin Password: ");
			admin.setAdminPassword(scanner.next());
			scanner.nextLine();
			try {
				exitTrig = !service.validateAdminLogin(admin.getAdminID(), admin.getAdminPassword());
			} catch (IBSException exception) {
				System.out.println(exception.getMessage());
			}
		} while (exitTrig);
		System.out.println("---------------Login Sucessful-----------------");
	}

	// Prints Details of a Service Provider
	private void printDetails(ServiceProvider serviceProvider) {
		System.out.println("Name of Company: " + serviceProvider.getNameOfCompany());
		System.out.println("GST Number: " + serviceProvider.getGstin());
		System.out.println("PAN: " + serviceProvider.getPanNumber());
		System.out.println("Account Number: " + serviceProvider.getAccountNumber());
		System.out.println("Bank Name:" + serviceProvider.getBankName());
		System.out.println("Company Address: " + serviceProvider.getCompanyAddress());
		System.out.println("Mobile Number: " + serviceProvider.getMobileNumber());
	}

	// Shows all Pending Service Providers and allows to approve/disapprove
	private void bankMethod(ArrayList<ServiceProvider> serviceProviders, ArrayList<ServiceProvider> serviceProviders2,
			ServiceProviderService service, Scanner scanner) {

		int index = 0;
		boolean exitTrigger2 = true;
		do {
			System.out.println(" Press 1 for pending List  Press 2 to view Request History 3 to exit ");
			while (!scanner.hasNextInt()) {
				scanner.next();
				scanner.nextLine();
				System.out.println("Invalid Input! Please Enter a Number");
			}
			switch (scanner.nextInt()) {
			case 1:

				if (serviceProviders.isEmpty()) {
					System.out.println("*******No pending accounts*********" + "\n RETURNING TO MAIN MENU! \n");
					break;
				}
				System.out.println("List of Pending Accounts: ");
				System.out.println("Select an Account from the list below");
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				System.out.println(serviceProviders.toString());
				for (index = 0; index < serviceProviders.size(); index++) {
					ServiceProvider value = serviceProviders.get(index);
					System.out.println((index + 1) + ". " + value.getNameOfCompany() + "\t Request Date and Time: "
							+ value.getRequestDate());
				}

				while (!scanner.hasNextInt()) {
					System.out.println("Enter a digit!");
					scanner.next();
					scanner.nextLine();
				}
				int k = scanner.nextInt();

				while (k > serviceProviders.size()) {
					System.out
					.println("Invalid input. Choose the correct number from menu to display company details!");
					while (!scanner.hasNextInt()) {
						System.out.println("Enter a digit!");
						scanner.next();
						scanner.nextLine();
					}
					k = scanner.nextInt();
				}
				scanner.nextLine();
				// Show selected Service Providers details
				for (index = 0; index <= serviceProviders.size(); index++) {
					if ((index + 1) == k) {
						ServiceProvider value = serviceProviders.get(index);
						printDetails(value);
						System.out.println("Enter true to Approve, false to Disapprove");
						try {
							while (!scanner.hasNextBoolean()) {
								System.out.println("Enter true or false!");
								scanner.next();
								scanner.nextLine();
							}
							service.approveSP(value, scanner.nextBoolean());
							scanner.nextLine();
						} catch (IBSException exception) {
							System.out.println(exception.getMessage());
						}
						serviceProviders.remove((index));
						break;
					}
				}
				System.out.println(
						"Press 1 to see remaining pending accounts \nPress any other number to go back to Main Menu");
				if (scanner.nextInt() == 1) {
					exitTrigger2 = true;
				}
				scanner.nextLine();
				break;

			case 2:
				int indx = 0;
				DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				if (serviceProviders2.isEmpty()) {
					System.out.println("*******No history Found*********" + "\n RETURNING TO MAIN MENU! \n");
					break;
				} else {
					for (ServiceProvider serviceProvider : serviceProviders2) {
						ServiceProvider value = serviceProviders2.get(indx);
						System.out.println(index + ". " + value.getNameOfCompany() + "\t Status "
								+ serviceProvider.getStatus() + "\t Request Date and Time: "
								+ serviceProvider.getRequestDate());
						index++;
					}
				}
				break;
			case 3:
				exitTrigger2 = false;
			default:
				System.out.println("Invalid Input");
				break;
			}
		} while (exitTrigger2);
	}

	// Taking 1 as input
	private void returnToMainMenu(Scanner scanner) {
		System.out.println("Enter a single digit number to return to main menu");
		while (!scanner.hasNextInt()) {
			System.out.println("Enter a digit!");
			scanner.next();
			scanner.nextLine();
		}
		scanner.nextInt();
		scanner.nextLine();
	}

	// Displays details and approval status
	private void loginMethod(Scanner scanner, ServiceProviderService service, ServiceProvider serviceProvider) throws IBSException {
		boolean exitTrig = true;
		if (service.emptyData()) {
			System.out.println("No Users Registered");
			return;
		}
		serviceProvider = null;
		do {

			System.out.println("Enter User ID: ");
			String inputId = scanner.next();
			scanner.nextLine();
			System.out.println("Enter Password: ");
			String inputPassword = scanner.next();
			scanner.nextLine();
			boolean check = false;
			try {
				check = service.validateLogin(inputId, inputPassword);
			} catch (IBSException exception) {
				System.out.println(exception.getMessage());
			}
			if (check == true) {
				try {
					serviceProvider = service.getServiceProvider(inputId);
				} catch (IBSException exception) {
					System.out.println(exception.getMessage());
				} finally {
					exitTrig = (serviceProvider == null);
				}
			}
		} while (exitTrig);
		System.out.println("-------------Login Succesful------------------");
		printDetails(serviceProvider);
		System.out.println("Approval Status: " + serviceProvider.getStatus());
		if (serviceProvider.getStatus().equals("Approved")) {
			System.out.println("Unique SPI ID: " + serviceProvider.getSpi());
		}
	}
}
