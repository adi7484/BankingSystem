package com.bankingSys;

import java.io.*;

import java.util.*;

import com.bankingSys.AccountType.FixedDepositAccount;
import com.bankingSys.AccountType.SavingsAccount;

public class BankApp {
		
	 public static void runBankApp() {
//			ArrayList with CustomerData Generic
			ArrayList<CustomerData> customers = new ArrayList<CustomerData>();
			
			
				
			
			    /*----------------Menu--------------*/
				System.out.println("1. Create New Customer Data");
				System.out.println("2. Assign a Bank Account to a Customer");
				System.out.println("3. Display balance and interest earned of a Customer");
				System.out.println("4. Sort Customer Data");
				System.out.println("5. Deposit or Withdraw amount");
				System.out.println("6. Show All Customers");
				System.out.println("7. Search Customers by Name");
				System.out.println("8. Exit");
				
				
				
				while(true) {
					Scanner scanner =new Scanner(System.in);
					System.out.println("Enter your choice: ");
					int choice= scanner.nextInt();
					scanner.nextLine();
					
					
					switch (choice) {
			            
						case 1:
			            	 //Calling createNewCustomer() method 
							 createNewCustomer(customers);

			            	 break;
			           
			            case 2:
			            	
			            	try {
							//Calling assignNewCustomer() method 
							assignBankCustomer(customers);
							serializeCustomers(customers, "customerData.ser");
			            		} catch (FundsException e) {
							
			            			e.printStackTrace();
			            	}
			                break;
			          
			            case 3:	
			            	customers= deserializeCustomers("customerData.ser");
			            	System.out.println("Enter customer id to get the balance or interest earned: ");
			            	
			            	int id=scanner.nextInt();
			            	try {
							CustomerData cust = findCustomerById(customers,id);
							System.out.println("The balance of account is: "+cust.getAccDetails().getBalance());
							System.out.println("Interest Earned : "+cust.getAccDetails().calculateInterest());
			            		} catch (CustomerNotFoundException e) {							
							e.printStackTrace();
			            	}
						
			                break;
			           
			            case 4:
			            	customers= deserializeCustomers("customerData.ser");
			                System.out.println("Sort Customer on Id/Name: ");
			                System.out.println("1. Id");
				            System.out.println("2. Name");
				            int option= scanner.nextInt();
				            switch(option) {
				            	case 1:
				            		sortingOnId(customers); 
				                	break;
				            	case 2:
				            		sortCustomerNames(customers);
				            		break;
				                	 
				                default:
				                	System.out.println("Enter valid choice.");
				            }
			            
			                break;
			           
			            case 5:
			            	try {
			            			handleTransaction(customers, scanner);
			            	} catch (CustomerNotFoundException e1) {
			            		e1.printStackTrace();
			            	} catch (FundsException e1) {
			            			e1.printStackTrace();
			            	}
					        
			                break;
			           
			            
			            case 6:
			            	
			            	//Display Customers using toString()
			            	//Checking Customers ArrayList if customers are created or not
			            	customers= deserializeCustomers("customerData.ser");
			            	
			            	if(customers.isEmpty()) {
			            	 System.out.println("No customers are created");
			                 }else{
			                	 for (CustomerData cust : customers) {
				                     System.out.println(cust.toString());
			                 }
			                	 }
			                break;
			                
			                
			            case 7:
			            	customers= deserializeCustomers("customerData.ser");
			            	System.out.println("Enter customer name to find customer: ");
			            	
			            	String findname=scanner.nextLine();
			            	try {
							CustomerData cust = findCustomerByName(customers,findname);
							System.out.println("Deatils of customer: "+cust.toString());
							
			            		} catch (CustomerNotFoundException e) {
							
			            			e.printStackTrace();
			            	}
			                break;
			                
			            case 8:
			            	serializeCustomers(customers, "customerData.ser");
			            	System.out.println("Exiting, Thank you! for using our services");
			            	System.exit(0);
			            	
			            default:
			                System.out.println("Invalid choice");
					}
			 }		

	 }
	 
	 
	    //method for creating a new customer
		private static void createNewCustomer(ArrayList<CustomerData> customers) {
			//1. Create New Customer 
			
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Enter customer details :");
	        System.out.println("Customer Name: ");
	        String customerName = scanner.nextLine();
	        System.out.println("Age: ");
	        int age = scanner.nextInt();
	        System.out.println("Mobile Number: ");
	        long mobileNumber = scanner.nextLong();
	        scanner.nextLine();
	        System.out.println("Passport Number: ");
	        String passportNumber = scanner.nextLine();
	        System.out.println("Date of Birth (DD/MM/YYYY): ");
	        String dob = scanner.nextLine();
	        
	        //Assigning all values to CustomerData constructor
	        CustomerData customer = new CustomerData(customerName, age, mobileNumber, passportNumber, dob);
	       
	        //adding customer object to ArrayList of customers
	        customers.add(customer);

	        System.out.println("Customer created successfully.");
	    }
		
		//method for assigning bank account to customer
		private static void assignBankCustomer(ArrayList<CustomerData> customers) throws FundsException {
			 Scanner scanner = new Scanner(System.in);
			// 2. Assign a Bank Account to a Customer
			 for (CustomerData cust : customers) {
		        	System.out.println("Current saved customer ids : ");
	                System.out.println(" [ "+" "+cust.getCustomerId()+" "+" ] ");
	                
		        }
	        System.out.println("Enter customer ID to whom a new Bank Account would be assigned: ");
	        
	        
	        int customerId = scanner.nextInt();
	        scanner.nextLine();
	        
	        try {
	        	//Finding the user input customerId
	            CustomerData selectedCustomer = findCustomerById(customers, customerId);

	            System.out.println("BSB Code: ");
	            long bsbCode = scanner.nextLong();
	            scanner.nextLine();
	            System.out.println("Bank Name: ");
	            String bankName = scanner.nextLine();
	            System.out.println("Balance: ");
	            double balance = scanner.nextDouble();
	            scanner.nextLine();

	            System.out.println("Select the type of Bank Account:");
	            System.out.println("1. Savings Account");
	            System.out.println("2. Fixed Deposit Account");
	            int accountType = scanner.nextInt();
	            scanner.nextLine();
	            
	            
	            //Creating new menu for acountType whether Savings or FixedDeposit
	            switch (accountType) {
	                case 1:
	                    // Savings Account
	                    System.out.println("Enter Savings Account details:");
	                    System.out.println("Is Salary Account (True/False): ");
	                    boolean isSalaryAccount = scanner.nextBoolean();
	                    
	                    //Passing values to check whether savings account condition is satisfied
	                    createSavingsAccount(selectedCustomer, bsbCode, bankName, balance, isSalaryAccount);
	                    System.out.println("Successfully assigned");
	                    break;

	                case 2:
	                    // Fixed Deposit Account
	                    System.out.println("Enter Fixed Deposit Account details:");
	                    System.out.println("Deposit Amount should be more than 1000$: ");
	                    double depositAmount = scanner.nextDouble();
	                    System.out.println("Tenure (in years): ");
	                    int tenure = scanner.nextInt();
	                    
	                    //Passing values to check whether Fixed account condition is satisfied
	                    createFixedDepositAccount(selectedCustomer, bsbCode, bankName, balance, tenure, depositAmount);
	                    System.out.println("Successfully assigned");
	                    break;

	                default:
	                    System.out.println("Invalid Bank Account type.");
	                    break;
	            }

	            System.out.println("Bank Account assigned successfully.");
	        } catch (CustomerNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	        
		}
		
		//method for finding customerId in customers ArrayList
		private static CustomerData findCustomerById(ArrayList<CustomerData> customers, int customerId) throws CustomerNotFoundException {
		        for (CustomerData cust : customers) {
		            if (cust.getCustomerId() == customerId) {
		                return cust;
		            }
		        }
		        throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
		    }
		
		//method for checking savingAccount is satisfied and assigning to customer
		private static void createSavingsAccount(CustomerData customer, long bsbCode, String bankName, double balance, boolean isSalaryAccount)
			        throws FundsException {
			    try {
			        if (isSalaryAccount) {
			            if (balance != 0) {
			                throw new FundsException("For Salary Account, only Zero balance is allowed.");
			               
			            }
			        } else {
			            if (balance < 100) {
			                throw new FundsException("Insufficient balance for Savings Account, Minimum balance should be 100 $");
			            }
			            //All condition satisfied passing values in savbankAccount and assigning customer to BankAccount of SavingAccount
			            BankAccount savbankAccount = new SavingsAccount(bsbCode, bankName, balance, isSalaryAccount);
			            customer.setAccDetails(savbankAccount);
			        }
			    } catch (FundsException ife) {
			        throw ife; // Re-throw the exception to be caught in the calling method
			    }
		 }
		 
		//method for checking FixedDepositAccount is satisfied and assigning to customer 
		private static void createFixedDepositAccount(CustomerData customer, long bsbCode, String bankName, double balance, int tenure, double depositAmount)
			        throws FundsException {
			    try {
			        if (depositAmount < 1000) {
			            throw new FundsException("Insufficient deposit amount for Fixed Deposit Account, Minimum deposit should be 1000 $");
			        }

			        if (tenure < 1 || tenure > 7) {
			            throw new FundsException("Invalid tenure for Fixed Deposit Account, Tenure should be between 1 and 7 years");
			        }
			        
			      //All condition satisfied passing values in fixedDepositbankAccount and assigning customer to BankAccount of FixedDepositAccount
			        BankAccount fixedDepositAccount = new FixedDepositAccount(bsbCode, bankName, balance, tenure, depositAmount);
			        customer.setAccDetails(fixedDepositAccount);

			    } catch (FundsException ife) {
			        throw ife; // Re-throw the exception to be caught in the calling method
			    }
			}
	 
		private static void serializeCustomers(ArrayList<CustomerData> customers, String filename) {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
	            oos.writeObject(customers);
	            System.out.println("Customer data serialized and saved to " + filename);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		private static ArrayList<CustomerData> deserializeCustomers(String filename) {
		        ArrayList<CustomerData> customers = new ArrayList<>();
		        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
		            customers = (ArrayList<CustomerData>) ois.readObject();
		            System.out.println("Customer data deserialized from " + filename);
		        } catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		        return customers;
		    }
		
		
		private static class CustomerComparator implements Comparator<CustomerData> {
		        @Override
		        public int compare(CustomerData customer1, CustomerData customer2) {
		            return Integer.compare(customer1.getCustomerId(), customer2.getCustomerId());
		        }
		    }
		 
		private static void sortingOnId(ArrayList<CustomerData> customers) {
			// Sort the list using the custom comparator
	        Collections.sort(customers, new CustomerComparator());

	        // Display the sorted customerID values
	        System.out.println("Sorted Customer IDs:");
	        for (CustomerData customer : customers) {
	        	System.out.println(customer.toString());
	        }
	    }
		
		private static class CustomerNameComparator implements Comparator<CustomerData> {
		        @Override
		        public int compare(CustomerData customer1, CustomerData customer2) {
		            return customer1.getCustomerName().compareTo(customer2.getCustomerName());
		        }
		    }

		// Method to sort and display sorted customer names
		private static void sortCustomerNames(ArrayList<CustomerData> customers) {
		        // Sort the list using the custom comparator for customer names
		        Collections.sort(customers, new CustomerNameComparator());

		        // Display the sorted customer names
		        System.out.println("Sorted Customer Names:");
		        for (CustomerData customer : customers) {
		        	System.out.println(customer.toString());
		        }
		    }
		
		private static CustomerData findCustomerByName(ArrayList<CustomerData> customers,String customerName) throws CustomerNotFoundException {
			for(CustomerData cust:customers) {
				if(cust.getCustomerName().equals(customerName)) {
					return cust;
				}
				
			}
			return null;
		}
		
		private static void handleTransaction(ArrayList<CustomerData> customers, Scanner scanner) throws CustomerNotFoundException, FundsException {
		    System.out.println("Enter customer ID: ");
		    int customerID = scanner.nextInt();

		    CustomerData customer = findCustomerById(customers, customerID);
			BankAccount account = customer.getAccDetails();

			if (account instanceof SavingsAccount) {
			    System.out.println("1. Deposit");
			    System.out.println("2. Withdraw");
			    int transactionChoice = scanner.nextInt();

			    switch (transactionChoice) {
			        case 1:
			            System.out.println("Enter amount to deposit: ");
			            double depositAmount = scanner.nextDouble();
			            ((SavingsAccount) account).deposit(depositAmount);
			            System.out.println("Deposit successful. New balance: " + account.getBalance());
			            break;

			        case 2:
			            System.out.println("Enter amount to withdraw: ");
			            double withdrawAmount = scanner.nextDouble();
			            ((SavingsAccount) account).withdraw(withdrawAmount);
			            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
			            break;

			        default:
			            System.out.println("Invalid choice.");
			    }
			} else {
			    System.out.println("Invalid account type for transactions.");
			}
		}

		

		
		
}
	
	

