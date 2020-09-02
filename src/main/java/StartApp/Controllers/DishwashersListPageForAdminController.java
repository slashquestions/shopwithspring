package StartApp.Controllers;

import StartApp.Entities.Dishwasher;
import StartApp.Repositories.DishwashersRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin/listproducts/dishwashers")
public class DishwashersListPageForAdminController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DishwashersRepo dishwasherRepo;

    @GetMapping
    public String toAdminPageDishwashers(Map<String,Object> model){
        Iterable<Dishwasher> listDishwasher = dishwasherRepo.findAll();
        model.put("dishwashers",listDishwasher);
        return "DishwashersPageForAdmin";
    }

    @PostMapping
    public String addMethod(@RequestParam String type,@RequestParam String maker,
                            @RequestParam String counter,@RequestParam String price,Map<String,Object> model){
        Dishwasher newDishwasher = new Dishwasher();

        newDishwasher.setType(type);
        newDishwasher.setMaker(maker);
        newDishwasher.setCounter(Integer.valueOf(counter));
        newDishwasher.setPrice(Integer.valueOf(price));
        dishwasherRepo.save(newDishwasher);
        logger.info("Admin added: "+type+" "+maker+" "+counter+" "+price+".");
        return "redirect:/admin/listproducts/dishwashers";
    }

}
