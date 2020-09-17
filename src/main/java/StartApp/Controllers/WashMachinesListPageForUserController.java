package StartApp.Controllers;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.OrderItem;
import StartApp.Entities.WashMachine;
import StartApp.Repositories.OrderRepo;
import StartApp.Repositories.WashMachinesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products/washmachines")

public class WashMachinesListPageForUserController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private WashMachinesRepo washMachinesRepo;
    @GetMapping
    public String toUserPageWashMachines(HttpServletRequest request, Model model, Authentication authentication){
        logger.info("User with name '"+authentication.getName()+"' entered on" + request.getRequestURI());
        List<OrderItem> basketProducts  = (List<OrderItem>) request.getSession().getAttribute("basketProducts");
        System.err.println(basketProducts);
        Iterable<WashMachine> listWashmachines =  washMachinesRepo.findAll();
        model.addAttribute("washmachines",listWashmachines);
        return "WashMachinesPageForUser";
    }


    @PostMapping
    public String addWashMachineToBasketProducts(HttpServletRequest request,@RequestParam Integer id,Authentication authentication){
        Optional<WashMachine> findedWashMach = washMachinesRepo.findById(id);
        if(findedWashMach.isPresent()) {
            WashMachine findWashMach = findedWashMach.get();
            int oldCounterProductFromDB = findWashMach.getCounter();
            if(oldCounterProductFromDB>0){
                request.getSession().setAttribute("messageAboutProduct",null);
                List<OrderItem> basketProducts = (List<OrderItem>) request.getSession().getAttribute("basketProducts");
                for(OrderItem product: basketProducts){
                    if(product.getProduct().getId() == id){
                        int oldCounter = product.getCounter();
                        oldCounter++;
                        product.setCounter(oldCounter);
                        findWashMach.setCounter(--oldCounterProductFromDB);
                        washMachinesRepo.save(findWashMach);
                        logger.info("User with name '"+authentication.getName()+"' added in basket :"+ product.toString());
                        return "redirect:/products/washmachines";
                    }
                }
                WashMachine newWashMachineForSession = new WashMachine().createClone(findWashMach);
                basketProducts.add(new OrderItem(1,newWashMachineForSession));
                request.getSession().setAttribute("basketProducts",basketProducts);
                findWashMach.setCounter(--oldCounterProductFromDB);
                washMachinesRepo.save(findWashMach);
                logger.info("User with name '"+authentication.getName()+"' added in basket :"+ newWashMachineForSession.toString());
                return "redirect:/products/washmachines";
            }
            else{
                String messageAboutProductOver = "Product with id " + id + " ended";
                request.getSession().setAttribute("messageAboutProduct",messageAboutProductOver);
                logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product ended");
                return "redirect:/products/washmachines";
            }
        }else {
            logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product not exist");
            return "redirect:/products/washmachines";
        }

    }
}
