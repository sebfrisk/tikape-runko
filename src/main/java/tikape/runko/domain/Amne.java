package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.*;

public class Amne {

    private String id;
    private String namn;
    private int antal;
    private Timestamp tid;

    public Amne(String id, String namn, int antal, Timestamp tid) {
        this.id = id;
        this.namn = namn;
        this.antal = antal;
        this.tid = tid;
    }

    public String getId() {
        return this.id;
    }

    public String getNamn() {
        return this.namn;
    }

    public int getAntal() {
        return this.antal;
    }

    public Timestamp getTid() {
        return this.tid;
    }
    
    public void setTid(Timestamp tid) {
        this.tid = tid;
    }
}
