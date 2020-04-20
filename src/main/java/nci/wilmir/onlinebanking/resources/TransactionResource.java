package nci.wilmir.onlinebanking.resources;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import nci.wilmir.onlinebanking.models.Link;
import nci.wilmir.onlinebanking.models.Transaction;
import nci.wilmir.onlinebanking.services.TransactionService;


@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class TransactionResource {

	private TransactionService transactionService = new TransactionService();

	@GET
	public Response getAllTransactions(@PathParam("customerId") int customerId, 
			@PathParam("accountNumber") int accountNumber,
			@Context UriInfo uriInfo) 
	{
		LinkedList<Transaction> transactions = transactionService.getAllTransactions(customerId, accountNumber);
		for(Transaction transaction : transactions) {
			addURIs(customerId, accountNumber, uriInfo, transaction);			
		}
		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(transactions)
				.build();
	}


	@GET
	@Path("/{type}")
	public Response getTransactions(@PathParam("customerId") int customerId, 
			@PathParam("accountNumber") int accountNumber, 
			@PathParam("type") String type,
			@QueryParam("recipient") int recipient,
			@QueryParam("amount") double amount) 
	{
		Double balance;
		
		switch (type.toLowerCase()) {
			case "balance":
				balance = transactionService.getBalance(customerId, accountNumber);
				break;
			case "withdrawal":
				balance = transactionService.withdrawMoney(customerId, accountNumber, amount);
				break;
			case "lodgement":
				balance = transactionService.lodgeMoney(customerId, accountNumber, amount);
				break;
			case "transfer":
				balance = transactionService.transferMoney(customerId, accountNumber, recipient, amount);
				break;
			default:
				balance = transactionService.withdrawMoney(customerId, accountNumber, amount);
				break;
		}
		
		
		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(balance)
				.build();
	}
	
	
	//Below are helper methods to set HATEOAS URIs
	private void addURIs(int customerId, int accountNumber, UriInfo uriInfo, Transaction transaction) {
		transaction.setLinks(new LinkedList<Link>());
		transaction.addLinks(getURIForSelf(customerId, accountNumber, uriInfo, transaction), "self");
		transaction.addLinks(getURIForAccount(customerId, accountNumber, uriInfo, transaction), "account");
		transaction.addLinks(getURIForCustomer(customerId, accountNumber, uriInfo, transaction), "customer");
	}

	private String getURIForSelf(int customerId, int accountNumber, UriInfo uriInfo, Transaction transaction) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(CustomerResource.class)
			.path(CustomerResource.class,"getAccountResource")
			.path(AccountResource.class, "getTransactionResource")
			.path(Integer.toString(transaction.getId()))
			.resolveTemplate("customerId", customerId)
			.resolveTemplate("accountNumber", accountNumber)
			.toString();
		return uri;
	}
	
	
	private String getURIForAccount(int customerId, int accountNumber, UriInfo uriInfo, Transaction transaction) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(CustomerResource.class)
				.path(CustomerResource.class,"getAccountResource")
				.path(Integer.toString(accountNumber))
				.resolveTemplate("customerId", customerId)
				.toString();
		
		return uri;
	}
	
	
	private String getURIForCustomer(int customerId, int accountNumber, UriInfo uriInfo, Transaction transaction) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(CustomerResource.class)
				.path(Integer.toString(customerId))
				.toString();
		
		return uri;
	}
}