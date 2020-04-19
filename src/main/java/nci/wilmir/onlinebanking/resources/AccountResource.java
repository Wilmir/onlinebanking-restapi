package nci.wilmir.onlinebanking.resources;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

	@GET
	public LinkedList<Account> getAccounts(@PathParam("customerId") int customerId) {
		LinkedList<Account> accounts = accountService.getAllAccounts(customerId);

		return accounts;
	}

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
