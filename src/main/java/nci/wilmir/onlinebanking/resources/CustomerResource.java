package nci.wilmir.onlinebanking.resources;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import nci.wilmir.onlinebanking.models.Customer;
import nci.wilmir.onlinebanking.models.Link;
import nci.wilmir.onlinebanking.services.CustomerService;


@Path("/customers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class CustomerResource {

	private CustomerService customerService = new CustomerService();


	//Get customer upon login
	@GET
	public Response getFilteredCustomer(@QueryParam("email") String email, 
			@QueryParam("password") String password,
			@Context UriInfo uriInfo){

		
		if(email != null && password != null) {
			Customer customer =  customerService.getFilteredCustomer(email, password);
			addURIs(uriInfo, customer);

			return Response.status(Status.OK).entity(customer).build();	
		}else {
			LinkedList<Customer> customers = customerService.getAllCustomers();
			for(Customer customer: customers) {
				addURIs(uriInfo, customer);
			}
			return Response.status(Status.OK).entity(customers).build();	
		}
	
	}


	//Get customer by ID
	@GET
	@Path("/{customerId}")
	public Response getCustomer(@PathParam("customerId") int customerId, @Context UriInfo uriInfo) {
		Customer customer = customerService.getCustomer(customerId);
		addURIs(uriInfo, customer);
		return Response.status(Status.OK).entity(customer).build();	
	}	

	//Add customer upon registration
	@POST
	public Response addCustomer(Customer c, @Context UriInfo uriInfo) {
		Customer customer = customerService.addCustomer(c);
		addURIs(uriInfo, customer);
		return Response.status(Status.OK).entity(customer).build();	
	}

	//Remove customer upon deactivation
	@DELETE
	@Path("/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId)
	{
		Customer customer = customerService.removeCustomer(customerId);
		return Response.status(Status.OK).entity(customer).build();	
	}
	
	
	//Implements the accounts subresource
	@Path("/{customerId}/accounts")
	public AccountResource getAccountResource() {
		return new AccountResource();
	}
	
	
	//Below are helper methods to return the HATEOAS URI for Self
	private void addURIs(UriInfo uriInfo, Customer customer) {
		customer.setLinks(new LinkedList<Link>());
		customer.addLink(getUriForSelf(uriInfo, customer), "self");
		customer.addLink(getUriForAccounts(uriInfo, customer), "accounts");
	}
	
	
	private String getUriForSelf(UriInfo uriInfo, Customer customer) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(CustomerResource.class)
			.path(Integer.toString(customer.getCustomerId()))
			.toString();
		return uri;
	}

	
	private String getUriForAccounts(UriInfo uriInfo, Customer customer) {
		String uri = uriInfo.getBaseUriBuilder()
					.path(CustomerResource.class)
					.path(CustomerResource.class,"getAccountResource")
					.resolveTemplate("customerId", customer.getCustomerId())
					.toString();
		return uri;
	}


	
	
	
}
