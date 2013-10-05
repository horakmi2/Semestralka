package cz.cvut.fel.aos.resources;

import cz.cvut.fel.aos.db.AOSMemoryDB;
import cz.cvut.fel.aos.db.entities.UserEntity;
import cz.cvut.fel.aos.exceptions.BadRequestException;
import cz.cvut.fel.aos.resources.mapping.MappingUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/users/")
public class UsersResource {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Collection<MappingUser> getUsers(@HeaderParam("x-Order") String order) {
        List<MappingUser> mappingUsers = new ArrayList<MappingUser>();
        for (UserEntity user: AOSMemoryDB.getAllUsers().orderBy(order)) {
            mappingUsers.add(new MappingUser(user, uriInfo.getAbsolutePathBuilder()));
        }
        return mappingUsers;
    }
    
    @GET
    @Path("/{nickname}/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingUser getUser(@PathParam("nickname") String nickname) {
        UserEntity userEntity = AOSMemoryDB.getUserByNick(nickname);
        return new MappingUser(userEntity, uriInfo.getAbsolutePathBuilder());
    }
    
    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MappingUser add(MappingUser newUser){
        UserEntity userEntity = new UserEntity(newUser.getNickname(), newUser.getPassword(), newUser.getEmails());
        boolean ret = AOSMemoryDB.addUser(userEntity);
        
        if (!ret) throw new BadRequestException("Cannot create user");
        return new MappingUser(userEntity, uriInfo.getAbsolutePathBuilder());
    }
    
}
