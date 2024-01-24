package com.bankingSys;

import java.io.Serializable;
import java.time.LocalDate;


public  abstract class BankAccount implements Serializable{

	private long nextAccountNum=100000;
	private long AccountNumber;
	private long BSBCode;
	private String BankName;
	private double Balance;
	private String OpenDate;
	
	
	
	public BankAccount(long BSBCode,String BankName,double Balance) {
		this.AccountNumber=getNextAccountNum();
		this.BSBCode=BSBCode;
		this.BankName=BankName;
		this.Balance=Balance;
		this.OpenDate=getOpenDate();
		
	}

	@Override
	public String toString() {
		return "BankAccount [AccountNumber=" + AccountNumber + ", BSBCode=" + BSBCode + ", BankName=" + BankName
				+ ", Balance=" + Balance + ", OpenDate=" + OpenDate + "]";
	}

	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public long getBSBCode() {
		return BSBCode;
	}

	public void setBSBCode(long bSBCode) {
		BSBCode = bSBCode;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}

	
	public String getOpenDate() {
		
		  LocalDate date = LocalDate.now();
		  
		return date.toString();
	}
	
	public abstract double calculateInterest();

	public long getNextAccountNum() {
		return nextAccountNum++;
	}

	public void setNextAccountNum(long nextAccountNum) {
		this.nextAccountNum = nextAccountNum;
	}
	
}

