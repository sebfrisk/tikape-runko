package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import java.sql.Timestamp;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AmneDao;
import tikape.runko.database.Database;
import tikape.runko.database.MeddelandeDao;
import tikape.runko.database.TradDao;
import tikape.runko.domain.Amne;

public class Main {

    public static void main(String[] args) throws Exception {
        // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }
         // käytetään oletuksena paikallista sqlite-tietokantaa
        String jdbcOsoite = "jdbc:sqlite:forum.db";
        // jos heroku antaa käyttöömme tietokantaosoitteen, otetaan se käyttöön
        if (System.getenv("DATABASE_URL") != null) {
            jdbcOsoite = System.getenv("DATABASE_URL");
        } 
        
        Database database = new Database(jdbcOsoite);

        AmneDao amnen = new AmneDao(database);
        TradDao tradar = new TradDao(database);
        MeddelandeDao meddelanden = new MeddelandeDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("amnen", amnen.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/amne/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            String id = req.params("id");
            map.put("tradar", tradar.findAll(id));
            map.put("amne", amnen.findOne(id));

            return new ModelAndView(map, "trad");
        }, new ThymeleafTemplateEngine());

        get("/trad/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            String id = req.params("id");
            map.put("meddelanden", meddelanden.findAll(id));
            map.put("trad", tradar.findOne(id));

            return new ModelAndView(map, "meddelande");

        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            amnen.addAmne(req.queryParams("Namn"));
            res.redirect("/");
            return "ok";
        });

        post("/amne/:id", (req, res) -> {
            String amneId = req.params("id");
            String namn = req.queryParams("Name");
            String innehall = req.queryParams("innehall");
            String signatur = req.queryParams("Signatur");
            int id = tradar.addTrad(amneId, namn);
            meddelanden.addMessage(Integer.toString(id), innehall, signatur);
            res.redirect("/trad/" + id);
            return "";
        });

        post("/trad/:id", (req, res) -> {
            String tradId = req.params("id");
            String innehall = req.queryParams("innehall");
            String namn = req.queryParams("Signatur");
            meddelanden.addMessage(tradId, innehall, namn);
            res.redirect("/trad/" + tradId);
            return "";
        });

    }
}
