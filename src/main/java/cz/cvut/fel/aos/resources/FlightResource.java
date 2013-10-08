/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.resources;

import cz.cvut.fel.aos.db.AOSMemoryDB;
import cz.cvut.fel.aos.db.entities.DestinationEntity;
import cz.cvut.fel.aos.db.entities.FlightEntity;
import cz.cvut.fel.aos.exceptions.BadRequestException;
import cz.cvut.fel.aos.resources.mapping.MappingFlight;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author buben
 */
@Path("/flight/")
public class FlightResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Collection<MappingFlight> getFlights(@HeaderParam("x-Order") String order) {
       List<MappingFlight> mappingFlights = new ArrayList<MappingFlight>();
        for (FlightEntity dest: AOSMemoryDB.getAllFlights()) {
            mappingFlights.add(new MappingFlight(dest, uriInfo.getAbsolutePathBuilder()));
        }
        return mappingFlights;
    }
    
    @GET
    @Path("/{id}/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingFlight getDestination(@PathParam("id") String name) {
        FlightEntity flight = AOSMemoryDB.getFlightByName(name);
        return new MappingFlight(flight, uriInfo.getAbsolutePathBuilder());
    }
    
    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingFlight add(MappingFlight f){
        DestinationEntity from = AOSMemoryDB.getDestinationByName(f.getFrom());
        DestinationEntity to = AOSMemoryDB.getDestinationByName(f.getTo());
        FlightEntity fe = new FlightEntity(f.getName(), f.getDateOfdeparture(), f.getDistance(), f.getSeats(), f.getPrice(), from, to);
        
        FlightEntity ret = AOSMemoryDB.addFlight(fe);
        
        if (ret == null) throw new BadRequestException("Cannot create flight");
        return new MappingFlight(fe, uriInfo.getAbsolutePathBuilder());
    }
    
    @PUT
    @Path("/{id}/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingFlight edit(MappingFlight f){

        DestinationEntity from = AOSMemoryDB.getDestinationByName(f.getFrom());
        DestinationEntity to = AOSMemoryDB.getDestinationByName(f.getTo());
        FlightEntity fe = new FlightEntity(f.getName(), f.getDateOfdeparture(), f.getDistance(), f.getSeats(), f.getPrice(), from, to);
       
        FlightEntity ret = AOSMemoryDB.editFlight(fe);
        
        if (ret == null) throw new BadRequestException("Cannot update flight");
        return new MappingFlight(fe, uriInfo.getAbsolutePathBuilder());
    }
    
    @DELETE
    @Path("/{id}/")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void delete(@PathParam("id") String name){

        int ret = AOSMemoryDB.deleteFlight(name); 
        if (ret == -1) throw new BadRequestException("Cannot delete flight");
        
    }
    
}
