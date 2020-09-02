package StartApp.Controllers;

import StartApp.Entities.WashMachine;
import StartApp.Repositories.WashMachinesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin/listproducts/washmachines")
public class WashMachinesListPageForAdminController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WashMachinesRepo washMachinesRepo;

    @GetMapping
    public String toAdminPageWashMachines(Model model){
        Iterable<WashMachine> washMachines = washMachinesRepo.findAll();
        model.addAttribute("washmachines",washMachines);
        return "WashMachinesPageForAdmin";
    }

    @PostMapping
    public String addMethod(@RequestParam String type, @RequestParam String maker,
                            @RequestParam String counter, @RequestParam String price, Map<String,Object> model){
        WashMachine newWashMachine = new WashMachine();

        newWashMachine.setType(type);
        newWashMachine.setMaker(maker);
        newWashMachine.setCounter(Integer.valueOf(counter));
        newWashMachine.setPrice(Integer.valueOf(price));
        washMachinesRepo.save(newWashMachine);
        logger.info("Admin added: "+type+" "+maker+" "+counter+" "+price+".");
        return "redirect:/admin/listproducts/washmachines";
    }

}