package nci.wilmir.onlinebanking.services;

import java.util.LinkedList;

import nci.wilmir.onlinebanking.database.DatabaseClass;
import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.models.Transaction;


public class TransactionService {
	
	DatabaseClass db = new DatabaseClass();
	
	private LinkedList<Customer> customers = db.getAllCustomers();
	
	//Get all the transactions
	public LinkedList<Transaction> getAllTransactions(int customerId, int accountNumber){
		Account account = findAccount(customerId, accountNumber);	
		
		return account.getTransactions();
	}
	

	
	//Get the balance from an account
	public Double getBalance(int customerId, int accountNumber) {
		Account account = findAccount(customerId, accountNumber);	
		
		addTransaction(account, false, false, "Checked Balance", 0);
				
		return account.getCurrentBalance();
	}
		
	
	//Lodge money to an account
	public Double lodgeMoney(int customerId, int accountNumber, double amount) {
		Account account = findAccount(customerId, accountNumber);	
				
		Double newBalance = account.getCurrentBalance() + amount;
		
		account.setCurrentBalance(newBalance);
		
		addTransaction(account, false, true, ("Lodged " + amount + " euros"), amount);
		
		return newBalance;
		
	}
	
	
	//Withdraw money from an account
	public Double withdrawMoney(int customerId, int accountNumber, double amount) {
		Account account = findAccount(customerId, accountNumber);	
		
		Double newBalance = account.getCurrentBalance() - amount;
		
		account.setCurrentBalance(newBalance);
		
		addTransaction(account, true, false, ("Withdrawn " + amount + " euros"), amount);
		
		return newBalance;
		
	}
	
	
	//Transfer money to another account
	public Double transferMoney(int customerId, int accountNumber, int recipientAccountNumber, double amount) {
		System.out.println("Trying to transfer money");
		System.out.println("Customer Number: " + customerId);
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Recipient Account Number: " + recipientAccountNumber);

		System.out.println("Trying to find account");
		Account account = findAccount(customerId, accountNumber);	
		System.out.println("Account found:" + account.getId());

		System.out.println("Trying to find recipient account");
		Account recipientAccount = findRecipientAccount(recipientAccountNumber);
		System.out.println("Recipient Account found:" + recipientAccount.getId());


		Double newBalance = account.getCurrentBalance() - amount;
		account.setCurrentBalance(newBalance);
				
		Double recipientNewBalance = recipientAccount.getCurrentBalance() + amount;
		recipientAccount.setCurrentBalance(recipientNewBalance);
		
		addTransaction(account, true, false, ("Transferred " + amount + " euros to " + recipientAccountNumber), amount);
		addTransaction(recipientAccount, false, true, ("Received " + amount + " euros from " + accountNumber), amount);
		
		return newBalance;
		
	}
	
	//Helper method to add any type of transaction
	private void addTransaction(Account account, boolean isDebit, boolean isCredit, String description, double transactionAmount) {
		
		int id;
		if(account.getTransactions().size() > 0) {
			id = account.getTransactions().getLast().getId() + 1;
		}else {
			id = 1;
		}
		
		Transaction transaction = new Transaction(id,isDebit,isCredit,description, transactionAmount);		
		
		transaction.setPostTransactionBalance(account.getCurrentBalance());
		
		
		account.getTransactions().add(transaction);

	}

	
	//Helper method to find a specific account from a customer
	private Account findAccount(int customerId, int accountNumber) {		
		for(Customer customer:customers) {
			if(customer.getCustomerId() == customerId) {
				for(Account account : customer.getAccounts()) {
					if(account.getAccountNumber() == accountNumber) {
						return account;
					}
				}
				
			}
		}
		
		return null;
	}
	
	
	//Helper method to find the recipient account
	private Account findRecipientAccount(int accountNumber) {
		for(Customer customer:customers) {
			for(Account account:customer.getAccounts()) {
				if(account.getAccountNumber() == accountNumber) {
					return account;
				}
			}
		}
		
		return null;
	}

}