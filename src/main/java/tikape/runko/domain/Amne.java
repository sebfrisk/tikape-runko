package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.*;

public class Amne {

    private String id;
    private String namn;
    private int antal;
    private Timestamp date;

    public Amne(String id, String namn) {
        this.id = id;
        this.namn = namn;
        antal = 0;
        date = null;
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

    public Timestamp getDate() {
        return this.date;
    }
}
