package cz.cvut.fel.aos.db;

import cz.cvut.fel.aos.db.entities.DestinationEntity;
import cz.cvut.fel.aos.db.entities.FlightEntity;
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
    private static Map<String, DestinationEntity> destinationsByName = new HashMap<String, DestinationEntity>();
    private static Map<String, FlightEntity> flightsByName = new HashMap<String, FlightEntity>();
    
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
        if(destinationsByName.containsKey(name)){
            return null;
        }
        destinationsByName.put(name, dest);
        return dest;
    }
    
    public static DestinationEntity getDestinationByName(String name){
        return destinationsByName.get(name);
    }
    
    public static DestinationEntity editDestination(DestinationEntity dest){
        if(destinationsByName.containsKey(dest.getName())){
            destinationsByName.put(dest.getName(), dest);
        }
        return null;
    }
    
    public static int deleteDestination(String name){
        if(destinationsByName.containsKey(name)){
            destinationsByName.remove(name);
            return 0;
        }
        return -1;
    }
    
    public static Collection<DestinationEntity> getAllDestinations(){
        //TODO
        return (ArrayList<DestinationEntity>) destinationsByName.values();
    }
    
    ///////////////////////flight
    
    public static FlightEntity addFlight(FlightEntity flight){
        String name = flight.getName();
        if(flightsByName.containsKey(name)){
            return null;
        }
        flightsByName.put(name, flight);
        return flight;
    }
    
    public static FlightEntity getFlightByName(String name){
        return flightsByName.get(name);
    }
    
    public static FlightEntity editFlight(FlightEntity flight){
        if(flightsByName.containsKey(flight.getName())){
            flightsByName.put(flight.getName(), flight);
        }
        return null;
    }
    
    public static int deleteFlight(String name){
        if(flightsByName.containsKey(name)){
            flightsByName.remove(name);
            return 0;
        }
        return -1;
    }
    
    public static Collection<FlightEntity> getAllFlights(){
        return (ArrayList<FlightEntity>) flightsByName.values();
    }
    
}
