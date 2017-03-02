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
import tikape.runko.domain.Amne;

public class AmneDao implements Dao<Amne, Integer> {

    private Database database;

    public AmneDao(Database database) {
        this.database = database;
    }

    @Override
    public Amne findOne(Integer key) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Amne WHERE namn = ?");
//        stmt.setObject(1, key);
//
//        ResultSet rs = stmt.executeQuery();
//        boolean hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
//
//        Integer id = rs.getInt("id");
//        String nimi = rs.getString("nimi");
//
//        Opiskelija o = new Opiskelija(id, nimi);
//
//        rs.close();
//        stmt.close();
//        connection.close();
// @{~/amne/{id}(id = ${amne.id})}
        return null;
    }

    @Override
    public List<Amne> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Amne");

        ResultSet rs = stmt.executeQuery();
        List<Amne> amnen = new ArrayList<>();
        while (rs.next()) {
            String id = Integer.toString(rs.getInt("id"));
            String namn = rs.getString("namn");

            amnen.add(new Amne(id, namn));
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

}

