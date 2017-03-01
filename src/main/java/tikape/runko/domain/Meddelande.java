
package tikape.runko.domain;

import java.util.Date;


public class Meddelande {
    private Integer id;
    private String anvandarnamn;
    private String innehall;
    private Date tid;
    
    public Meddelande(String namn, String substans, Date tid) {
        this.anvandarnamn = namn;
        this.innehall = substans;
        this.tid = tid;
        
    }
    
    
    
    
}
