package cz.cvut.fel.aos.db;

import cz.cvut.fel.aos.db.entities.DestinationEntity;
import cz.cvut.fel.aos.db.entities.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that simulates in-memory database.
 */
public class AOSMemoryDB {
    
    private static Map<String, UserEntity> usersByNickName = new HashMap<String, UserEntity>();
    private static Map<String, DestinationEntity> destionationsByName = new HashMap<String, DestinationEntity>();
    
    public static boolean addUser(UserEntity user){
        String nick = user.getNick();
        if(usersByNickName.containsKey(nick)){
            return false;
        }
        usersByNickName.put(nick, user);
        return true;
    }
    
    public static UserEntity getUserByNick(String nick){
        return usersByNickName.get(nick);
    }
    
    public static AOSMemoryDBQueryset getAllUsers(){
        return new AOSMemoryDBQueryset(usersByNickName.values());
    }
   
    ///////////////////////destination
    
    public static DestinationEntity addDestination(DestinationEntity dest){
        String name = dest.getName();
        if(destionationsByName.containsKey(name)){
            return null;
        }
        destionationsByName.put(name, dest);
        return dest;
    }
    
    public static DestinationEntity getDestinationByName(String name){
        return destionationsByName.get(name);
    }
    
    public static Collection<DestinationEntity> getAllDestinations(){
        //TODO
        return (ArrayList<DestinationEntity>) destionationsByName.values();
    }
    
}
