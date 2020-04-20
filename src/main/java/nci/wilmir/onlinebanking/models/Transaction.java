package nci.wilmir.onlinebanking.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	private int id;
	private boolean isDebit;
	private boolean isCredit;
	private Date createdDate;
	private String description;
	private double transactionAmount;
	
	public Transaction(){

	}

	public Transaction(int id, boolean isDebit, boolean isCredit, String description, double transactionAmount) {
		this.id = id;
		this.isDebit = isDebit;
		this.isCredit = isCredit;
		this.createdDate = new Date();
		this.description = description;
		this.transactionAmount = transactionAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDebit() {
		return isDebit;
	}

	public void setDebit(boolean isDebit) {
		this.isDebit = isDebit;
	}

	public boolean isCredit() {
		return isCredit;
	}

	public void setCredit(boolean isCredit) {
		this.isCredit = isCredit;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
}