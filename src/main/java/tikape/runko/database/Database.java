package tikape.runko.database;

import java.sql.*;
import java.util.*;
import java.net.*;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;

        init();
    }

    public Connection getConnection() throws SQLException {
        if (this.databaseAddress.contains("postgres")) {
            try {
                URI dbUri = new URI(databaseAddress);

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                return DriverManager.getConnection(dbUrl, username, password);
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
                t.printStackTrace();
            }
        }

        return DriverManager.getConnection(databaseAddress);

    }

    public void init() {
        List<String> lauseet = null;
        if (this.databaseAddress.contains("postgres")) {
            lauseet = postgreLauseet();
        } else {
            lauseet = sqliteLauseet();
        }

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> postgreLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        
        // heroku käyttää SERIAL-avainsanaa uuden tunnuksen automaattiseen luomiseen
        lista.add("CREATE TABLE Amne (id SERIAL PRIMARY KEY, namn varchar(255));");
        lista.add("CREATE TABLE Trad (id SERIAL PRIMARY KEY, amne integer, namn varchar(64) NOT NULL, senaste timestamp, FOREIGN KEY (amne) REFERENCES Amne(id));");
        lista.add("CREATE TABLE Meddelande(trad integer, skrivare varchar(32) NOT NULL, text varchar(1023) NOT NULL, tid timestamp, FOREIGN KEY (trad) REFERENCES Trad(id));");

        return lista;
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE Amne (id integer PRIMARY KEY, namn varchar(255));");
        lista.add("CREATE TABLE Trad (id integer PRIMARY KEY, amne integer, namn varchar(64) NOT NULL, senaste timestamp, FOREIGN KEY (amne) REFERENCES Amne(id));");
        lista.add("CREATE TABLE Meddelande (trad integer, skrivare varchar(32) NOT NULL, text varchar(1023) NOT NULL, tid timestamp, FOREIGN KEY (trad) REFERENCES Trad(id));");

        return lista;
    }
}
