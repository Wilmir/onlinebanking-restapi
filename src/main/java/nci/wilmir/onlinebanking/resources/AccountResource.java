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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import nci.wilmir.onlinebanking.models.Account;
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
	
	@GET
	public Response getAccounts(@PathParam("customerId") int customerId) {
		LinkedList<Account> accounts = accountService.getAllAccounts(customerId);

		return Response.status(Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(accounts)
				.build();	}

	//Create new account
	@POST
	public Response addNewAccount(@PathParam("customerId") int customerId, Account newAccount) {
		Account account = accountService.addAccount(customerId, newAccount);
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

	
	@Path("/{accountNumber}/transactions")
	public TransactionResource getTransactions() {
		return new TransactionResource();
	}
	 
}
