
package tikape.runko.domain;

import java.util.*;

public class Amne {
    private String namn;
    private int antal;
    private Date senaste;
    
    public Amne(String namn) {
        this.namn = namn;
        this.antal = 0;
    }
    
    public Amne(String namn, int antal, Date senaste) {
        this.namn = namn;
        this.antal = antal;
        this.senaste = senaste;
    }
    
    public String getId() {
        return this.namn;
    }
    
    public int getAntal() {
        return this.antal;
    }
    
    public Date getDate() {
        return this.senaste;
    }
}
