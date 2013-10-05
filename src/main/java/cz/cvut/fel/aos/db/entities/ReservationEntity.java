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
public class ReservationEntity {
    private long id; //- identifikátor (Long)
    private FlightEntity flight; //- let na který je rezervován - pøi vytváøení se posílá pouze id letu
    private Date created; // - datum a èas vytvoøení rezervace
    private String password; // - náhodnì vygenerované heslo, které klient používá pro pøístup k rezervaci a jejím úpravám
    private int seats; // - poèet zarezervovaných míst
    private StateChoices state; // - stav rezervace (new, canceled, paid)

    public ReservationEntity(FlightEntity flight, Date created, String password, int seats, StateChoices state) {
        this.flight = flight;
        this.created = created;
        this.password = password;
        this.seats = seats;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public StateChoices getState() {
        return state;
    }

    public void setState(StateChoices state) {
        this.state = state;
    }
    
}
