/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.db;

import cz.cvut.fel.aos.db.entities.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author lubos
 */
public class AOSMemoryDBQueryset extends ArrayList<UserEntity>{

    AOSMemoryDBQueryset(Collection<UserEntity> values) {
        super(values);
    } 
    
    public AOSMemoryDBQueryset orderBy(String name) {
        String direction = "asc";
        if (name != null && name.contains(":")) {
            String [] params = name.split(":", 2);
            direction   = params[1];
            name        = params[0];
        }
        Collections.sort(this, new OrderingComparator(name, direction));
        return this;
    }
    
    class OrderingComparator implements Comparator<UserEntity> {

        private String field;
        private String direction;
        
        public OrderingComparator(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }

        public int compare(UserEntity o1, UserEntity o2) {
            int ord = direction.equals("asc") ? 1 : -1;
            if (this.field != null && this.field.endsWith("nickname")){
                return o1.getNick().compareTo(o2.getNick()) * ord;
            }
            return (int) (o1.getId() - o2.getId()) * ord;
         }
        
    }
}
