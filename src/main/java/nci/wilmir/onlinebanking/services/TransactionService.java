package nci.wilmir.onlinebanking.services;

import java.util.LinkedList;

import nci.wilmir.onlinebanking.database.DatabaseClass;
import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.models.Transaction;


public class TransactionService {
	
	DatabaseClass db = new DatabaseClass();
	
	private LinkedList<Customer> customers = db.getAllCustomers();
	private LinkedList<Account> accounts = db.getAllAccounts();

	
	//Get all the transactions
	public LinkedList<Transaction> getAllTransactions(int customerId, int accountNumber){
		Account account = findAccount(customerId, accountNumber);	
		
		return account.getTransactions();
	}
	

	
	//Get the balance from an account
	public Double getBalance(int customerId, int accountNumber) {
		Account account = findAccount(customerId, accountNumber);	
		
		addTransaction(account, false, false, "Checked Balance");
				
		return account.getCurrentBalance();
	}
		
	
	//Lodge money to an account
	public Double lodgeMoney(int customerId, int accountNumber, double amount) {
		Account account = findAccount(customerId, accountNumber);	
		
		addTransaction(account, true, false, ("Lodged " + amount + " euros"));
		
		Double newBalance = account.getCurrentBalance() + amount;
		
		account.setCurrentBalance(newBalance);
		
		return newBalance;
		
	}
	
	
	//Withdraw money from an account
	public Double withdrawMoney(int customerId, int accountNumber, double amount) {
		Account account = findAccount(customerId, accountNumber);	
		
		addTransaction(account, false, true, ("Withdrawn " + amount + " euros"));

		Double newBalance = account.getCurrentBalance() - amount;
		
		account.setCurrentBalance(newBalance);
		
		return newBalance;
		
	}
	
	
	//Transfer money to another account
	public Double transferMoney(int customerId, int accountNumber, int recipientAccountNumber, double amount) {
		Account account = findAccount(customerId, accountNumber);	
		Account recipientAccount = findRecipientAccount(recipientAccountNumber);
		
		addTransaction(account, false, true, ("Transferred " + amount + " euros to " + recipientAccountNumber));
		addTransaction(recipientAccount, true, false, ("Received " + amount + " euros from " + accountNumber));
		
		Double newBalance = account.getCurrentBalance() - amount;
		account.setCurrentBalance(newBalance);
		
		Double recipientNewBalance = recipientAccount.getCurrentBalance() + amount;
		recipientAccount.setCurrentBalance(recipientNewBalance);
		
		return newBalance;
		
	}
	
	
	
	//Helper method to add any type of transaction
	private void addTransaction(Account account, boolean isDebit, boolean isCredit, String description) {
		
		int id;
		if(account.getTransactions().size() > 0) {
			id = account.getTransactions().getLast().getId() + 1;
		}else {
			id = 1;
		}
		
		Transaction transaction = new Transaction(id,isDebit,isCredit,description);		
		
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
		for(Account account:accounts) {
			if(account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		
		return null;
	}

}