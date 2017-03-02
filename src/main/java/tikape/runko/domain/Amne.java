
package tikape.runko.domain;

import java.util.*;

public class Amne {
    private int id;
    private String namn;
    
    public Amne(String namn) {
        this.namn = namn;
    }
    
    public Amne(int id, String namn) {
        this.id = id;
        this.namn = namn;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNamn() {
        return this.namn;
    }

}
