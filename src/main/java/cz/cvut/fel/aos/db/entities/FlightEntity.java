/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.db.entities;

import java.util.Date;

/**
 *
 * @author buben
 */
public class FlightEntity {
    
    private long id;   // - identifikátor (Long)
    private String name; //- název letu (unikátní)
    private Date dateOfdeparture; // - datum a èas odletu
    private float distance;  //- vzdálenost v km
    private int seats; // - poèet míst v letadle
    private float price; // - cena letu
    private DestinationEntity from;  //- zdrojová destinace - pøi vytváøení se posílá pouze id letu
    private DestinationEntity to;  //- cílová destinace - pøi vytváøení se posílá pouze id letu

    public FlightEntity() {
    }

    public FlightEntity(String name, Date dateOfdeparture, float distance, int seats, float price, DestinationEntity from, DestinationEntity to) {
        this.name = name;
        this.dateOfdeparture = dateOfdeparture;
        this.distance = distance;
        this.seats = seats;
        this.price = price;
        this.from = from;
        this.to = to;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfdeparture() {
        return dateOfdeparture;
    }

    public void setDateOfdeparture(Date dateOfdeparture) {
        this.dateOfdeparture = dateOfdeparture;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DestinationEntity getFrom() {
        return from;
    }

    public void setFrom(DestinationEntity from) {
        this.from = from;
    }

    public DestinationEntity getTo() {
        return to;
    }

    public void setTo(DestinationEntity to) {
        this.to = to;
    }
    
    
    
    
}
