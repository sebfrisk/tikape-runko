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
        PreparedStatement stmt = connection.prepareStatement("SELECT innehall, anvandarnamn FROM Meddelande");

        ResultSet rs = stmt.executeQuery();
        List<Meddelande> meddelanden = new ArrayList<>();
        while (rs.next()) {
            String innehall = rs.getString("innehall");
            String namn = rs.getString("anvandarnamn");
            Date tid = null;

            meddelanden.add(new Meddelande(namn, innehall, tid));
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

    public void addMessage(String innehall, String namn) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Meddelande (trad_id, anvandarnamn, innehall) VALUES (id, "+namn+", "+innehall+")");
        stmt.close();
        connection.close();
    }

}
