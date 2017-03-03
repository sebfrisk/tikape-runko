
package tikape.runko.domain;

import java.sql.Timestamp;




public class Trad {
    
    private int id;
    private String namn;
    private int antal;
    private Timestamp tid;
    
    public Trad(int id, String namn, int antal, Timestamp tid) {
        this.id = id;
        this.namn = namn;
        this.antal = antal;
        this.tid = tid;
    }
    
    public Trad(String namn) {
        this.namn = namn;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int newId) {
        this.id = newId;
    }
    
    public String getNamn() {
        return namn;
    }
    
    public void setNamn(String newName) {
        this.namn = newName;
    }
    
    public int getAntal() {
        return antal;
    } 
    
    public Timestamp getTid() {
        return tid;
    }
    
}
