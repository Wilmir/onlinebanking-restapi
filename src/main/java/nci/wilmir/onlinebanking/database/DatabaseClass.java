package nci.wilmir.onlinebanking.database;

import java.util.LinkedList;

import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.models.Transaction;


public class DatabaseClass {

	private static LinkedList<Customer> customers = new LinkedList<>();
	private static LinkedList<Account> accounts = new LinkedList<>();
	private static LinkedList<Transaction> transactions = new LinkedList<>();
	
	private static boolean init = false;
	
	public DatabaseClass() {
		if(!init) {
			
			
			Customer customer1 = new Customer(1, "Wilmir Nicanor", "Dublin, Ireland", "x19167521@student.ncirl.ie", "password", getAllAccounts());
			customers.add(customer1);
			
			
			Account account1 = new Account(1, 22224444, 10000, true, false, getAllTransactions());
			accounts.add(account1);
			
			Transaction transaction1 = new Transaction(1, true, false, "Deposited 10000 euros", 10000);
			transaction1.setPostTransactionBalance(account1.getCurrentBalance());
			transactions.add(transaction1);
			
			
			init = true;
			
		}
	}
	
	public LinkedList<Customer> getAllCustomers(){
		return customers;
	}
	
	
	public LinkedList<Account> getAllAccounts(){
		return accounts;
	}
	
	
	public LinkedList<Transaction> getAllTransactions(){
		return transactions;
	}
	
	
}