package com.bankingSys.AccountType;

import com.bankingSys.BankAccount;

public class FixedDepositAccount extends BankAccount   {

	private int DepoTenure;
	private double DepoAmmount=100.00;
	private double intEarned;
	
	public FixedDepositAccount(long BSBCode, String BankName, double Balance,int DepoTenure,double DepoAmmount) {
		super(BSBCode, BankName, Balance);
				
		this.DepoAmmount = DepoAmmount;
        this.DepoTenure = DepoTenure;
	}

	public int getDepoTenure() {
		return DepoTenure;
	}

	public void setDepoTenure(int depoTenure) {
		DepoTenure = depoTenure;
	}

	public double getDepoAmmount() {
		return DepoAmmount;
	}

	public void setDepoAmmount(double depoAmmount) {
		DepoAmmount = depoAmmount;
	}

	public double getIntEarned() {
		return intEarned;
	}

	public void setIntEarned(double intEarned) {
		this.intEarned = intEarned;
	}

	@Override
	public String toString() {
	    return "Fixed Deposit Account Information\n" +
	            "Account Number: " + getAccountNumber() +
	            "\nBSB Code: " + getBSBCode() +
	            "\nBank Name: " + getBankName() +
	            "\nBalance: " + getBalance() +
	            "\nOpening Date: " + getOpenDate() +
	            "\nDeposit Amount: " + getDepoAmmount() +
	            "\nTenure: " + getDepoTenure() + " years";
	}

	@Override
	public double calculateInterest() {
		
		 return 0.08 * getBalance() * getDepoTenure();
	}

	

}
