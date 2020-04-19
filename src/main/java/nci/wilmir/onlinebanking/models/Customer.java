package nci.wilmir.onlinebanking.models;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Customer {
	private int customerId;
	private String name;
	private String address;
	private String email;
	private String password;
	private LinkedList<Account> accounts;
	
	
	public Customer() {
		
	}
	
	public Customer(int customerId, String name, String address, String email, String password, LinkedList<Account> accounts) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.accounts = accounts;
	}
	
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public LinkedList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}