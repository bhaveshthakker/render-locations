package com.pubmatic.loginext.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pubmatic.loginext.bean.LocationPoints;
import com.pubmatic.loginext.services.LocationPointsService;

//@Service
@Component
@Path("/location")
public class LocationPointsResource {
	
	@Autowired
	private LocationPointsService locationPointsService;

	@GET
    @Produces("application/json")
	@Path("{id}")
	public LocationPoints get(@PathParam("id") int id){
		System.out.println("LocationPointsResource.get()");
		return null;
	}
	
	@GET
    @Produces("application/json")
	public List<LocationPoints> getAll() throws Exception {
		System.out.println("LocationPointsResource.get()");
		List<LocationPoints> locationPoints = locationPointsService.getAllLocations();
		return locationPoints;
	}
	
	@POST
	@Produces("application/json")
	public LocationPoints post(LocationPoints locationPoint) throws Exception {
		System.out.println("LocationPointsResource.post()");
		locationPointsService.insertLocations(locationPoint);
		return null;
	}
	
	@POST
	@Produces("application/json")
	public LocationPoints post(List<LocationPoints> locationPoints) throws Exception {
		System.out.println("LocationPointsResource.post()");
		locationPointsService.insertMultipleLocations(locationPoints);
		return null;
	}
}
