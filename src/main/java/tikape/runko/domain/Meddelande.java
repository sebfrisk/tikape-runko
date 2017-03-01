
package tikape.runko.domain;

import java.util.Date;


public class Meddelande {
    private Integer id;
    private String namn;
    private String substans;
    private Date tid;
    
    public Meddelande(String namn, String substans) {
        this.namn = namn;
        this.substans = substans;
    }
    
    
}
