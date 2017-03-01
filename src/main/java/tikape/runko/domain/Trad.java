
package tikape.runko.domain;




public class Trad {
    private int id;
    private String namn;
    private int antal;
    
    public Trad(int id, String namn) {
        this.id = id;
        this.namn = namn;
        this.antal = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int newId) {
        this.id = newId;
    }
    
    public String getName() {
        return namn;
    }
    
    public void setName(String newName) {
        this.namn = newName;
    }
    
    public int getAntal() {
        return antal;
    } 
    
    public void addPost() {
        antal++;
    }
    
    
}
