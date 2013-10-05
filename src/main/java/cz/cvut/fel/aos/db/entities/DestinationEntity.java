/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.db.entities;

/**
 *
 * @author buben
 */
public class DestinationEntity {
    
    private static long maxId = 0;
    
    private String name;
    private long id;

    public DestinationEntity(String name) {
        this.name = name;
        this.id = maxId++;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

}
