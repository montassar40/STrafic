package services.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Bus;
import domain.Driver;
import domain.Station;

@Local
@Path("/drivers")
public interface DriverServicesLocal {

	Driver findDriverById(Integer id);

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Bus> findBusesByDriverId(@PathParam("id") Integer idDriver);


	@GET
	@Produces("application/json")
	List<Driver> findAllBuses();

}
