/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tikape.runko.domain.Meddelande;

public class MeddelandeDao implements Dao<Meddelande, Integer> {

    private Database database;

    public MeddelandeDao(Database database) {
        this.database = database;
    }

    @Override
    public List findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Meddelande");

        ResultSet rs = stmt.executeQuery();
        List<Meddelande> meddelanden = new ArrayList<>();
        while (rs.next()) {
            String tradId = rs.getString("trad_id");
            String innehall = rs.getString("innehall");
            String namn = rs.getString("anvandarnamn");
            Timestamp tid = rs.getTimestamp("tidpunkt");

            meddelanden.add(new Meddelande(tradId, namn, innehall, tid));
        }

        rs.close();
        stmt.close();
        connection.close();

        return meddelanden;
    }

    @Override
    public Meddelande findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMessage(String tradId, String innehall, String namn) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Meddelande (trad_id, anvandarnamn, innehall, tidpunkt) VALUES (?, ?, ?, ?)");
        stmt.setString(1, tradId);
        stmt.setString(2, namn);
        stmt.setString(3, innehall);
        stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        
        stmt.execute();
        stmt.close();
        connection.close();
    }

}
