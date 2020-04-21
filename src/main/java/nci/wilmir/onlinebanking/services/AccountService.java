package nci.wilmir.onlinebanking.services;

import java.util.LinkedList;
import java.util.Random;

import nci.wilmir.onlinebanking.database.DatabaseClass;
import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.models.Transaction;



public class AccountService {
	private DatabaseClass db = new DatabaseClass();
	private LinkedList<Customer> customers = db.getAllCustomers();
	
	
	public AccountService() {
		System.out.println("A new Account Service isntance has been created");
	}
	
	
	//Get all accounts from a specific customer
	public LinkedList<Account> getAllAccounts(int customerId){
		Customer customer = findCustomer(customerId);
		return customer.getAccounts();
	}
	
	
	//Create a new account for the customer
	public Account addAccount(int customerId, Account account) {
		Customer customer = findCustomer(customerId);
		
		int id;
		if(customer.getAccounts().size() > 0) {
			id = customer.getAccounts().getLast().getId() + 1;
		}else {
			id = 1;
		}
		
		account.setId(id);
		account.setSortCode(Account.SORTCODE);
		account.setAccountNumber(generateAccountNumber(customerId));
		account.setTransactions(new LinkedList<Transaction>());
		
		
		customer.getAccounts().add(account);
		return account;
			
	}
	
	//Close an account 
	public Account removeAccount(int customerId, int accountNumber) {
		Customer customer = findCustomer(customerId);
		
		LinkedList<Account> customerAccounts = customer.getAccounts();		
		
		for(Account account : customerAccounts) {
			if(account.getAccountNumber() == accountNumber) {
				Account acc = account;
				customerAccounts.remove(account);
				return acc;
			}
		}
		
		return null;
	}

	
	//Helper method to find a specific customer
	private Customer findCustomer(int customerId) {
		for(Customer customer:customers) {
			if(customer.getCustomerId() == customerId) {
				return customer;
			}
		}
		
		return null;
	}
	
	//Helper method to generate random account numbers
	private int generateAccountNumber(int customerId) {
		Random random = new Random();
		int accountNumber = random.nextInt(999999999) + customerId + 999999999;
		return accountNumber;
	}

	

}
