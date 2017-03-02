package tikape.runko.domain;

import java.sql.Timestamp;

public class Meddelande {

    private String tradId;
    private String anvandarnamn;
    private String innehall;
    private Timestamp tid;

    public Meddelande(String tradId, String namn, String substans, Timestamp tid) {
        this.tradId = tradId;
        this.anvandarnamn = namn;
        this.innehall = substans;
        this.tid = tid;
    }

    public Meddelande(String namn, String substans) {
        this.anvandarnamn = namn;
        this.innehall = substans;
    }
    
    public String getInnehall() {
        return this.innehall;
    }
    
    public String getAnvandarnamn() {
        return this.anvandarnamn;
    }
    
    public String getTradId() {
        return this.tradId;
    }
    
    public Timestamp getTid() {
        return this.tid;
    }

}
