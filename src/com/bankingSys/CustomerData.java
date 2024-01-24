package com.bankingSys;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class CustomerData implements Serializable {
	private static int nextCustomerId = 100;

	
	private int customerId=100;
	private String customerName;
	private int age;
	private long mobileNumber;
	private String passportNumber;
	private BankAccount AccDetails;
	private String dob;

    
	public CustomerData(String customerName,int age,long mobileNumber,String passportNumber,String dob){
		this.customerId=getNextCustomerId();
		this.customerName=customerName;
		this.age=age;
		this.mobileNumber=mobileNumber;
		this.passportNumber=passportNumber;	
		if (validateDOB(dob)) {
            this.dob = dob;
        } else {
            System.out.println("Invalid Date of Birth format. Please re-enter DOB (DD/MM/YYYY): ");
            Scanner scanner = new Scanner(System.in);
            this.dob = scanner.nextLine();
        }
		
		  
	}

	
    public int getCustomerId() {
        return customerId;
    }

    
    public String getCustomerName() {
        return customerName;
    }

    
    public int getAge() {
        return age;
    }

    
    public long getMobileNumber() {
        return mobileNumber;
    }

  
    public String getPassportNumber() {
        return passportNumber;
    }

   
    private static int getNextCustomerId() {
        return nextCustomerId++;
    }


	@Override
	public String toString() {
		return "Name: " + customerName + 
                ", age: " + age +
                ", mobileNumber:  " + mobileNumber + 
                ", passportNumber: " + passportNumber+
                ", DOB: " + dob+
                ", Account Details: " +AccDetails.toString()
                ;
                
	}


	public BankAccount getAccDetails() {
		return AccDetails;
	}


	public void setAccDetails(BankAccount accDetails) {
		AccDetails = accDetails;
	}
	 private boolean validateDOB(String dob) {
	        String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
	        Pattern pattern = Pattern.compile(dateRegex);
	        Matcher matcher = pattern.matcher(dob);

	        if (matcher.matches()) {
	            // If the date format is correct, further validate day, month, and year
	            int day = Integer.parseInt(dob.substring(0, 2));
	            int month = Integer.parseInt(dob.substring(3, 5));
	            int year = Integer.parseInt(dob.substring(6));

	            if (day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900) {
	                return true;  // Valid date
	            }
	        }

	        return false;
	    }


	 public String getDOB() {
	        return dob;
	    }


	


	

	
}
