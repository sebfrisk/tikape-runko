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
import tikape.runko.domain.Amne;
import tikape.runko.domain.Trad;

public class AmneDao implements Dao<Amne, Integer> {

    private Database database;

    public AmneDao(Database database) {
        this.database = database;
    }

    public Amne findOne(String id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Amne WHERE id = ?");
        stmt.setObject(1, id);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String namn = rs.getString("namn");

        Amne o = new Amne(namn);

        rs.close();
        stmt.close();
        connection.close();
        return o;
    }

    @Override
    public List<Amne> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Amne LEFT JOIN (SELECT tid, amne FROM Meddelande, Trad where Meddelande.trad = Trad.id) ON amne = Amne.id GROUP BY namn ORDER BY namn");

        ResultSet rs = stmt.executeQuery();
        List<Amne> amnen = new ArrayList<>();
        while (rs.next()) {
            String id = Integer.toString(rs.getInt("id"));
            String namn = rs.getString("namn");
            int antal = count(id);
            Timestamp tid = rs.getTimestamp("tid");
            amnen.add(new Amne(id, namn, antal, tid));
        }
        rs.close();
        stmt.close();
        connection.close();


        return amnen;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    public void addAmne(String nyttamne) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Amne (namn) VALUES ('"+nyttamne+"')");
        stmt.execute();
        stmt.close();
        connection.close();
    }
    
    public int count(String id) throws SQLException {
        TradDao temp = new TradDao(this.database);
        int antal = 0;
        for (Trad a: temp.findAll(id)) {
            antal += temp.count(Integer.toString(a.getId()));
        }
        return antal;
    }
    
//    public Timestamp getLatestPost(String id) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT tid FROM Meddelande, Trad WHERE Trad.amne = ? AND Meddelande.trad = Trad.id ORDER BY tid DESC LIMIT 1");
//        stmt.setObject(1, id);
//        ResultSet rs = stmt.executeQuery();
//        Timestamp latest = rs.getTimestamp("tid");
//        rs.close();
//        stmt.close();
//        connection.close();
//        return latest;
//        
//    }

    @Override
    public Amne findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

