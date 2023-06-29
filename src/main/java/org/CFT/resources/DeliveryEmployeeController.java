package org.CFT.resources;

import io.swagger.annotations.Api;
import org.CFT.api.DeliveryEmployeeService;


@Api("DeliveryEmployees")
@Path("/api")

public class DeliveryEmployeeController {


    private DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

    @GET
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployees() {
        try {
            return Response.ok(deliveryEmployeeService.getAllDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeesException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id) {
        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeById(id)).build();
        } catch (FailedToGetDeliveryEmployeesException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (DeliveryEmployeeDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @POST
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) {
        try {
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(deliveryEmployee)).build();
        } catch (FailedToCreateDeliveryEmployeeException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (InvalidDeliveryEmployeeException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployeeById(@PathParam("id") int id, DeliveryEmployeeRequest deliveryEmployee) {
        try {
            deliveryEmployeeService.updateDeliveryEmployee(id, deliveryEmployee);
            return Response.ok().build();
        } catch (DeliveryEmployeeDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (InvalidDeliveryEmployeeException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (FailedToUpdateDeliveryEmployeeException e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployeeById(@PathParam("id") int id) {
        try {
            deliveryEmployeeService.deleteDeliveryEmployee(id);
            return Response.ok().build();
        } catch (DeliveryEmployeeDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (FailedToDeleteDeliveryEmployeeException e) {
            throw new RuntimeException(e);
        }
    }
}
