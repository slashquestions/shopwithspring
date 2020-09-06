package StartApp.Controllers;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.Refrigerator;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.MainSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.List;

@Controller
@RequestMapping("/search/")
public class SearchController {

    private Logger logger = LogManager.getLogger();

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping
    public String toSearchPage(@RequestParam int id, Model model){

        DefaultClassForMachine finded = entityManager.find(DefaultClassForMachine.class,id);
        if(finded==null){
            return "ErrorPageProduct";
        }
        model.addAttribute("product",finded);

        return "ProductPage";
    }

}
