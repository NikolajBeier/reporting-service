package reporting.service.adapter.rest;

import reporting.service.DTO.CustomerReportDTO;
import reporting.service.DTO.ManagerReport;
import reporting.service.DTO.MerchantReportDTO;
import reporting.service.ReportingService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("/reports")
public class ReportingResource {

	ReportingServiceFactory factory = new ReportingServiceFactory();
	ReportingService service = factory.getService();

	@GET
	@Path("/manager")
	@Produces("application/json")
	public Response createManagerReport() {
		try {
			ManagerReport report = service.createManagerReport().join();
			//TODO: Might need to be a list instead, don't know how this will look
			return Response.ok(report).build();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Interrupted while processing").build();
		}
	}

	@GET
	@Path("/customer/{customerId}")
	@Produces("application/json")
	public Response createCustomerReport(@PathParam("customerId") String customerId) {
		try {
			List<CustomerReportDTO> report = service.createCustomerReport(customerId).get();
			return Response.ok(report).build();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Interrupted while processing").build();
		} catch (ExecutionException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in processing report").build();
		}
	}

	@GET
	@Path("/merchant/{merchantId}")
	@Produces("application/json")
	public Response createMerchantReport(@PathParam("merchantId") String merchantId) {
		try {
			List<MerchantReportDTO> report = service.createMerchantReport(merchantId).get();
			return Response.ok(report).build();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Interrupted while processing").build();
		} catch (ExecutionException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in processing report").build();
		}
	}
}
