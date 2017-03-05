package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Trad;

public class TradDao implements Dao<Trad, Integer> {

    private Database database;

    public TradDao(Database database) {
        this.database = database;
    }

    public List<Trad> findAll(String id) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trad WHERE amne = ? ORDER BY senaste DESC LIMIT 10");
        stmt.setObject(1, id);
        ResultSet rs = stmt.executeQuery();
        List<Trad> tradar = new ArrayList<>();
        while (rs.next()) {
            Integer tunnus = rs.getInt("id");
            String nimi = rs.getString("namn");
            int antal = count(Integer.toString(tunnus));
            Timestamp tid = rs.getTimestamp("senaste");
            tradar.add(new Trad(tunnus, nimi, antal, tid));
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

//        Trad o = new Trad(id, nimi, 0);
        rs.close();
        stmt.close();
        connection.close();

        return null;
    }
    
    public Trad findOne(String id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trad WHERE id = ?");
        stmt.setObject(1, id);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String namn = rs.getString("namn");

        Trad o = new Trad(namn);

        rs.close();
        stmt.close();
        connection.close();
        return o;
    }

    public Integer addTrad(String amneId, String namn) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Trad (amne, namn) VALUES (?, ?)");
        stmt.setObject(1, amneId);
        stmt.setObject(2, namn);
        stmt.execute();
        stmt.close();
        PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) AS x FROM Trad");
        ResultSet rs = count.executeQuery();
        Integer antal = rs.getInt("x");
        rs.close();
        count.close();
        connection.close();
        return antal;
    }

    @Override
    public List<Trad> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trad");

        ResultSet rs = stmt.executeQuery();
        List<Trad> tradar = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("namn");

//            tradar.add(new Trad(id, nimi, 0));
        }

        rs.close();
        stmt.close();
        connection.close();

        return tradar;
    }

    public int count(String id) throws SQLException {
        MeddelandeDao temp = new MeddelandeDao(this.database);
        return temp.findAll(id).size();
    }

}
