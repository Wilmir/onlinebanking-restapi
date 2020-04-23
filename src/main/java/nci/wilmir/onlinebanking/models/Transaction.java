package nci.wilmir.onlinebanking.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	private int id;
	private boolean isDebit;
	private boolean isCredit;
	private String createdDate;
	private String description;
	private double transactionAmount;
	private double postTransactionBalance;
	private LinkedList<Link> links;
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm z");
	
	public Transaction(){

	}

	public Transaction(int id, boolean isDebit, boolean isCredit, String description, double transactionAmount) {
		this.id = id;
		this.isDebit = isDebit;
		this.isCredit = isCredit;
		this.createdDate = formatDate.format(new Date());
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = formatDate.format(createdDate);
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

	public double getPostTransactionBalance() {
		return postTransactionBalance;
	}

	public void setPostTransactionBalance(double postTransactionBalance) {
		this.postTransactionBalance = postTransactionBalance;
	}

	public LinkedList<Link> getLinks() {
		return links;
	}

	public void setLinks(LinkedList<Link> links) {
		this.links = links;
	}
	
	public void addLinks(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
	
}