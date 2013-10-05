package cz.cvut.fel.aos.resources.mapping;

import cz.cvut.fel.aos.db.entities.UserEntity;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//DB -> XML(JSON) adapter
@XmlRootElement(name="aos-user")
public class MappingUser {
    
    private String nickname;
    private String password;
    //not required
    private List<String> emails;
    private String uri;
    
    public MappingUser(){}
    
    //maps db entity to the class that is annotated by JAXB and can be transformed to JSON or XML
    public MappingUser(UserEntity userEntity, UriBuilder ub){
        if(userEntity != null){
            this.nickname = userEntity.getNick();
            this.password = userEntity.getPass();
            this.emails   = new LinkedList<String>(userEntity.getEmails());
        }
        this.uri = ub.path(this.nickname).build().getPath();
    }

    @XmlElement(required=true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<String> getEmails() {
        if (emails == null) {
            return new ArrayList<String>();
        }
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return this.uri;
    }
    
}
