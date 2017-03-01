package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AmneDao;
import tikape.runko.database.Database;
import tikape.runko.database.TradDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:forum.db");
//        database.init();

        AmneDao amnen = new AmneDao(database);
        TradDao tradar = new TradDao(database);
<<<<<<< HEAD
        
=======
>>>>>>> 64520b3f598b8bab09154be6b19f111f4a70fdd9

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("amnen", amnen.findAll());
            

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/:id", (req, res) -> {
            HashMap map = new HashMap<>();
<<<<<<< HEAD
            map.put("tradar", tradar.findAll());
=======
            map.put("opiskelijat", tradar.findAll());
>>>>>>> 64520b3f598b8bab09154be6b19f111f4a70fdd9

            return new ModelAndView(map, "opiskelijat");
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
        
        post("/:id", (req, res) -> {
            tradar.addTrad(req.queryParams("Name"));
//            res.redirect("/");
            return "ok";
        });
    }
}
