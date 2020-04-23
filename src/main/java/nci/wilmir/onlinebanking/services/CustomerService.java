package nci.wilmir.onlinebanking.services;

import java.util.LinkedList;

import nci.wilmir.onlinebanking.database.DatabaseClass;
import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Customer;


public class CustomerService {
	DatabaseClass db = new DatabaseClass();
	LinkedList<Customer> customers = db.getAllCustomers();
	
	
	public CustomerService() {
	}
	
	
	public LinkedList<Customer> getAllCustomers(){
		return customers;
	}
	
	
	public Customer getCustomer(int customerId) {
		Customer customer = findCustomer(customerId);
		return customer;
	}
	
	
	public Customer getFilteredCustomer(String email, String password) {
				
		for(Customer customer:customers) {
			String customerEmail = customer.getEmail();
			String customerPassword = customer.getPassword();
			
			if(customerEmail.equalsIgnoreCase(email) && customerPassword.equals(password)){
				return customer;
			}
		}
		
		return null;
	}
	
	public Customer addCustomer(Customer customer) {
		int id = customers.getLast().getCustomerId() + 1;
		customer.setCustomerId(id);
		customer.setAccounts(new LinkedList<Account>());
		customers.add(customer);	
		
		return customer;
	}
	
	
	public Customer updateCustomer(int customerId, Customer customerUpdate) {		
		Customer customer = findCustomer(customerId);
		customer = customerUpdate;
		return customer;
		
	}
	
	
	public Customer removeCustomer(int customerId) {
		Customer customer = findCustomer(customerId);
		customers.remove(customer);
		return customer;
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
	
	
}
