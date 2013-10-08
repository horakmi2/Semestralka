package cz.cvut.fel.aos.resources.mapping;

import cz.cvut.fel.aos.db.entities.FlightEntity;
import java.util.Date;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//DB -> XML(JSON) adapter
@XmlRootElement(name="aos-flight")
public class MappingFlight {
    
    private String name; //- název letu (unikátní)
    private Date dateOfdeparture; // - datum a èas odletu
    private float distance;  //- vzdálenost v km
    private int seats; // - poèet míst v letadle
    private float price; // - cena letu
    private String from;  //- zdrojová destinace - pøi vytváøení se posílá pouze id letu
    private String to;  //- cílová destinace - pøi vytváøení se posílá pouze id letu
    private String uri;
    
    public MappingFlight(){}
    
    //maps db entity to the class that is annotated by JAXB and can be transformed to JSON or XML
    public MappingFlight(FlightEntity de, UriBuilder ub){
        if(de != null){
            this.name = de.getName();
        }
        this.uri = ub.path(this.name).build().getPath();
    }

    @XmlElement(required=true)
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

 }
