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

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(new Timestamp(System.currentTimeMillis()));
        Database database = new Database("jdbc:sqlite:forum.db");

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
            map.put("tradar", tradar.findAll());

            return new ModelAndView(map, "trad");
        }, new ThymeleafTemplateEngine());
        
        get("/trad", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("meddelanden", meddelanden.findAll());

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
            tradar.addTrad(req.queryParams("Name"));
            res.redirect("/");
            return "ok";
        });
        
        post("/trad/:id", (req, res) -> {
            String tradId = req.params("id");
            String innehall = "";
            String namn = "";
            meddelanden.addMessage(tradId, innehall, namn);
            return "";
        });
               
    }
}
