package com.bankingSys.AccountType;



import com.bankingSys.BankAccount;
import com.bankingSys.FundsException;

public class SavingsAccount extends BankAccount {

	


	private boolean IsSalaryAccount;
	private double MinmBalance=100.00;
	private String dob;
	
	
	public SavingsAccount(long BSBCode,String BankName, double Balance,boolean IsSalaryAccount) {
		
		super( BSBCode, BankName, Balance);
		
		//System.out.println("Inside Savings account constructor");
		this.IsSalaryAccount = IsSalaryAccount;
		//System.out.println("Exiting Savings account constructor");
	}



	public boolean isIsSalaryAccount() {
		return IsSalaryAccount;
	}


	public void setIsSalaryAccount(boolean isSalaryAccount) {
		IsSalaryAccount = isSalaryAccount;
	}


	public double getMinmBalance() {
		return MinmBalance;
	}


	public void setMinmBalance(double minmBalance) {
		MinmBalance = minmBalance;
	}

	@Override
	public String toString() {
		//System.out.println("Inside SavingsAccount toString()");
		//System.out.println("Exiting SavingsAccount toString()");
	    return "Savings Account Information\n" +
	            "Account Number: " + getAccountNumber() +
	            "\nBSB Code: " + getBSBCode() +
	            "\nBank Name: " + getBankName() +
	            "\nBalance: " + getBalance() +
	            "\nOpening Date: " + getOpenDate();
	            
	            
	}
	
	public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public void withdraw(double amount) throws FundsException {
        if (IsSalaryAccount) {
            if (amount > getBalance()) {
                throw new FundsException("For Salary Account, withdrawal amount exceeds available balance.");
            }
        } else {
            if (getBalance() - amount < getMinmBalance()) {
                throw new FundsException("Insufficient balance for Savings Account, Minimum balance should be " + MinmBalance + " $");
            }
        }
        setBalance(getBalance() - amount);
    }
	


	@Override
	public double calculateInterest() {
		
		return 0.04 * getBalance();
		
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}

}
