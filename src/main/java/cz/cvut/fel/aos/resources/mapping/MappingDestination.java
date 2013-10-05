package cz.cvut.fel.aos.resources.mapping;

import cz.cvut.fel.aos.db.entities.DestinationEntity;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//DB -> XML(JSON) adapter
@XmlRootElement(name="aos-destination")
public class MappingDestination {
    
    private String name;
    private String uri;
    
    public MappingDestination(){}
    
    //maps db entity to the class that is annotated by JAXB and can be transformed to JSON or XML
    public MappingDestination(DestinationEntity de, UriBuilder ub){
        if(de != null){
            this.name = de.getName();
        }
        this.uri = ub.path(this.name).build().getPath();
    }

    @XmlElement(required=true)
    public String getName() {
        return name;
    }

    public void setName(String nickname) {
        this.name = nickname;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return this.uri;
    }
    
}
