package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Trad;

public class TradDao implements Dao<Trad, Integer> {

    private Database database;

    public TradDao(Database database) {
        this.database = database;
    }

    public List<Trad> findAll(String amne) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trad WHERE amne = ?");
        stmt.setString(1, amne);

        ResultSet rs = stmt.executeQuery();
        List<Trad> tradar = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("namn");

            tradar.add(new Trad(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return tradar;
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }

    @Override
    public Trad findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trad WHERE namn = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Trad o = new Trad(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }
    
    public void addTrad(String namn) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Trad (namn) VALUES ('"+namn+"')");
        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public List<Trad> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
