/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.resources;

import cz.cvut.fel.aos.db.AOSMemoryDB;
import cz.cvut.fel.aos.db.entities.DestinationEntity;
import cz.cvut.fel.aos.exceptions.BadRequestException;
import cz.cvut.fel.aos.resources.mapping.MappingDestination;
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
@Path("/destination/")
public class DestinationResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Collection<MappingDestination> getDestinations(@HeaderParam("x-Order") String order) {
       List<MappingDestination> mappingDestinations = new ArrayList<MappingDestination>();
        for (DestinationEntity dest: AOSMemoryDB.getAllDestinations()) {
            mappingDestinations.add(new MappingDestination(dest, uriInfo.getAbsolutePathBuilder()));
        }
        return mappingDestinations;
    }
    
    @GET
    @Path("/{id}/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingDestination getDestination(@PathParam("id") String name) {
        DestinationEntity dest = AOSMemoryDB.getDestinationByName(name);
        return new MappingDestination(dest, uriInfo.getAbsolutePathBuilder());
    }
    
    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingDestination add(MappingDestination newDest){
        DestinationEntity destEntity = new DestinationEntity(newDest.getName());
        
        DestinationEntity ret = AOSMemoryDB.addDestination(destEntity);
        
        if (ret == null) throw new BadRequestException("Cannot create destination");
        return new MappingDestination(destEntity, uriInfo.getAbsolutePathBuilder());
    }
    
    @PUT
    @Path("/{id}/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingDestination edit(MappingDestination newDest){

        DestinationEntity destEntity = new DestinationEntity(newDest.getName());
        DestinationEntity ret = AOSMemoryDB.editDestination(destEntity);
        
        if (ret == null) throw new BadRequestException("Cannot update destination");
        return new MappingDestination(destEntity, uriInfo.getAbsolutePathBuilder());
    }
    
    @DELETE
    @Path("/{id}/")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void delete(@PathParam("id") String name){

        int ret = AOSMemoryDB.deleteDestination(name); 
        if (ret == -1) throw new BadRequestException("Cannot delete destination");
        
    }
}
