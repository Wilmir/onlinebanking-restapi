package nci.wilmir.onlinebanking.resources;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import nci.wilmir.onlinebanking.models.Account;
import nci.wilmir.onlinebanking.models.Link;
import nci.wilmir.onlinebanking.services.AccountService;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class AccountResource {
	
	private AccountService accountService = new AccountService();

	//Send header response to the OPTIONS request from browser's CORS pre-flight initially triggered after the POST method
	@OPTIONS
	public Response sendOptions() {
		return Response.ok("Sent back allowed CORS pre-flight headers")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST")
				.header("Access-Control-Allow-Headers", "Content-Type")
				.build();
	}
	
	
	//Return all the accounts of a customer
	@GET
	public Response getAccounts(@PathParam("customerId") int customerId, @Context UriInfo uriInfo) {
		LinkedList<Account> accounts = accountService.getAllAccounts(customerId);
		for(Account account : accounts) {
			addURIs(customerId, uriInfo, account);
		}
		
		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(accounts)
				.build();	}


	//Create a new account
	@POST
	public Response addNewAccount(@PathParam("customerId") int customerId, Account newAccount, @Context UriInfo uriInfo) {
		Account account = accountService.addAccount(customerId, newAccount);
		addURIs(customerId, uriInfo, account);


		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(account)
				.build();
	}


	//Deactivate an account
	@DELETE
	@Path("/{accountNumber}")
	public Response removeAccount(@PathParam("customerId") int customerId, 
			@PathParam("accountNumber") int accountNumber) {
		Account account = accountService.removeAccount(customerId, accountNumber);
		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(account).build();

	}

	//Implement the transaction sub-resource
	@Path("/{accountNumber}/transactions")
	public TransactionResource getTransactionResource() {
		return new TransactionResource();
	}
	
	
	//Below are helper methods to set the HATEOAS URIs
	private void addURIs(int customerId, UriInfo uriInfo, Account account) {
		account.setLinks(new LinkedList<Link>());
		account.addLinks(getUriForSelf(customerId, uriInfo, account), "self");
		account.addLinks(getUriForCustomer(customerId, uriInfo, account), "customer");
		account.addLinks(getUriForTransaction(customerId, uriInfo, account), "transactions");
	}
	
	private String getUriForSelf(int customerId, UriInfo uriInfo, Account account) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(CustomerResource.class)
			.path(CustomerResource.class,"getAccountResource")
			.path(Integer.toString(account.getAccountNumber()))
			.resolveTemplate("customerId", customerId)
			.toString();
		return uri;
	}
	
	private String getUriForCustomer(int customerId, UriInfo uriInfo, Account account) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(CustomerResource.class)
			.path(Integer.toString(customerId))
			.toString();
		
		return uri;
	}

	private String getUriForTransaction(int customerId, UriInfo uriInfo, Account account) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(CustomerResource.class)
			.path(CustomerResource.class,"getAccountResource")
			.path(AccountResource.class,"getTransactionResource")
			.resolveTemplate("customerId", customerId)
			.resolveTemplate("accountNumber", account.getAccountNumber())
			.toString();
		return uri;
	}
	 
}
