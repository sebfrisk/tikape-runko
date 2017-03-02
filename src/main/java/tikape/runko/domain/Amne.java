package tikape.runko.domain;

import java.sql.Timestamp;
import java.util.*;

public class Amne {

    private Integer id;
    private String namn;
    private int antal;
    private Timestamp date;

    public Amne(String namn) {
        this.namn = namn;
        antal = 0;
        date = null;
    }

    public String getId() {
        return this.namn;
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
