
package tikape.runko.domain;




public class Trad {
    
    private int id;
    private String namn;
    private int antal;
    
    public Trad(int id, String namn, int antal) {
        this.id = id;
        this.namn = namn;
        this.antal = antal;
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
    
}
