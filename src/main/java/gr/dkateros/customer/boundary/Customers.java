package gr.dkateros.customer.boundary;

import java.net.URI;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import gr.dkateros.customer.control.CustomerDao;
import gr.dkateros.customer.entity.Customer;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("customers")
public class Customers {
	
	@EJB CustomerDao dao;
	
	@POST
	public Response add(JsonObject customer, @Context UriInfo info) {
		Customer d = dao.add(customer);
		URI uri = info.getAbsolutePathBuilder().path("/" + d.getId()).build();
		return Response.created(uri).entity(dao.json(d)).build();
	}
	
	@GET
	@Path("{id}")
	public Response find(@PathParam("id") Long id) {
		Customer customer = dao.find(id);
		if (customer == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(id + " not found").build();
		}
		return Response.ok(dao.json(customer)).build();
	}
	
	@PUT
	public Response update(JsonObject customer) {
		Customer d = dao.update(customer);
		return Response.ok(dao.json(d)).build();
	}

}
