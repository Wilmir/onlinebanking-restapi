package nci.wilmir.onlinebanking.models;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Account {
	private int id;
	private int sortCode;
	private int accountNumber;
	private double currentBalance;
	private boolean isCurrentAccount;
	private boolean isSavingsAccount;
	private LinkedList<Transaction> transactions;
	
	public Account() {
		
	}
	
	public Account(int id, int sortCode, int accountNumber, double currentBalance, boolean isCurrentAccount,
			boolean isSavingsAccount, LinkedList<Transaction> transactions) {
		this.id = id;
		this.sortCode = sortCode;
		this.accountNumber = accountNumber;
		this.currentBalance = currentBalance;
		this.isCurrentAccount = isCurrentAccount;
		this.isSavingsAccount = isSavingsAccount;
		this.transactions = transactions;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSortCode() {
		return sortCode;
	}

	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}


	public boolean isCurrentAccount() {
		return isCurrentAccount;
	}


	public void setCurrentAccount(boolean isCurrentAccount) {
		this.isCurrentAccount = isCurrentAccount;
	}


	public boolean isSavingsAccount() {
		return isSavingsAccount;
	}


	public void setSavingsAccount(boolean isSavingsAccount) {
		this.isSavingsAccount = isSavingsAccount;
	}


	public LinkedList<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(LinkedList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
	
}