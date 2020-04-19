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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.services.CustomerService;


@Path("/customers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class CustomerResource {

	private CustomerService customerService = new CustomerService();

	
	//Send header response to the OPTIONS request from browser's CORS pre-flight initially triggered after the POST method
	@OPTIONS
	public Response sendOptions() {
		return Response.ok("Sent back allowed CORS pre-flight headers")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST")
				.header("Access-Control-Allow-Headers", "Content-Type")
				.build();
	}
	

	//Get customer upon login
	@GET
	public Response getFilteredCustomer(@QueryParam("email") String email, 
			@QueryParam("password") String password){

		
		if(email != null && password != null) {
			Customer customer =  customerService.getFilteredCustomer(email, password);
			return Response.status(Status.OK)
					.entity(customer)
					.header("Access-Control-Allow-Origin", "*")
					.build();	
		}else {
			LinkedList<Customer> customers = customerService.getAllCustomers();
			return Response.status(Status.OK)
					.entity(customers)
					.header("Access-Control-Allow-Origin", "*")
					.build();	
		}
	
	}


	@GET
	@Path("/{customerId}")
	public Response getCustomer(@PathParam("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		return Response.status(Status.OK)
				.entity(customer)
				.header("Access-Control-Allow-Origin", "*")
				.build();	

	}	

	
	@POST
	public Response addCustomer(Customer c) {
		Customer customer = customerService.addCustomer(c);
		return Response.status(Status.OK)
				.entity(customer)
				.header("Access-Control-Allow-Origin", "*")
				.build();	

	}


	@DELETE
	@Path("/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId)
	{
		Customer customer = customerService.removeCustomer(customerId);
		return Response.status(Status.OK)
				.entity(customer)
				.header("Access-Control-Allow-Origin", "*")
				.build();	
	}
	
	@Path("/{customerId}/accounts")
	public AccountResource getAccounts() {
		return new AccountResource();
	}


}
