package StartApp.Controllers;

import StartApp.Entities.Refrigerator;
import StartApp.Repositories.RefrigeratorsRepo;
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
@RequestMapping("/admin/listproducts/refrigerators")
public class RefrigeratorsListPageForAdminController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private RefrigeratorsRepo refrigeratorsRepo;

    @GetMapping
    public String toAdminPageRefrigerators(Map<String,Object> model){

        Iterable<Refrigerator> listRefrigerators = refrigeratorsRepo.findAll();
        model.put("refrigerators",listRefrigerators);
        return "RefrigeratorsPageForAdmin";
    }

    @PostMapping
    public String addMethod(@RequestParam String type,@RequestParam String maker,
                          @RequestParam String counter,@RequestParam String price,Map<String,Object> model){
        Refrigerator newRefrigerator = new Refrigerator();

        newRefrigerator.setType(type);
        newRefrigerator.setMaker(maker);
        newRefrigerator.setCounter(Integer.valueOf(counter));
        newRefrigerator.setPrice(Integer.valueOf(price));
        refrigeratorsRepo.save(newRefrigerator);
        logger.info("Admin added: "+type+" "+maker+" "+counter+" "+price+".");

        return "redirect:/admin/listproducts/refrigerators";
    }

}
