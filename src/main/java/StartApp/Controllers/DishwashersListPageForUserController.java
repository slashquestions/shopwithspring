package StartApp.Controllers;

import StartApp.Entities.DefaultInterfaceForMachine;
import StartApp.Entities.Dishwasher;
import StartApp.Repositories.DishwashersRepo;
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
@RequestMapping("/products/dishwashers")

public class DishwashersListPageForUserController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DishwashersRepo dishwasherRepo;
    @GetMapping
    public String toUserPageDishwasher(HttpServletRequest request, Model model, Authentication authentication){
        logger.info("User with name '"+ authentication.getName()+"' entered on" + request.getRequestURI());
        List<DefaultInterfaceForMachine> basketProducts  = (List<DefaultInterfaceForMachine>) request.getSession().getAttribute("basketProducts");
        System.err.println(basketProducts);
        Iterable<Dishwasher> listDishwashers =  dishwasherRepo.findAll();
        model.addAttribute("dishwashers",listDishwashers);
        return "DishwashersPageForUser";
    }

    @PostMapping
    public String addDishwasherToBasketProducts(HttpServletRequest request,@RequestParam Integer id,Authentication authentication){
        Optional<Dishwasher> findedDishwasher = dishwasherRepo.findById(id);
        if(findedDishwasher.isPresent()) {
            Dishwasher findDish = findedDishwasher.get();
            int oldCounterProductFromDB = findDish.getCounter();
            if(oldCounterProductFromDB>0){
                request.getSession().setAttribute("messageAboutProduct",null);

                List<DefaultInterfaceForMachine> basketProducts = (List<DefaultInterfaceForMachine>) request.getSession().getAttribute("basketProducts");
                for(DefaultInterfaceForMachine product: basketProducts){
                    if(product.getId() == id){
                        int oldCounter = product.getCounter();
                        oldCounter++;
                        product.setCounter(oldCounter);
                        findDish.setCounter(--oldCounterProductFromDB);
                        dishwasherRepo.save(findDish);
                        logger.info("User with name '"+authentication.getName()+"' added in basket :"+ product.toString());
                        return "redirect:/products/dishwashers";
                    }
                }
                Dishwasher newDishwasherForSession = new Dishwasher().createClone(findDish);
                newDishwasherForSession.setCounter(1);
                basketProducts.add(newDishwasherForSession);
                request.getSession().setAttribute("basketProducts",basketProducts);
                findDish.setCounter(--oldCounterProductFromDB);
                dishwasherRepo.save(findDish);
                logger.info("User with name '"+authentication.getName()+"' added in basket :"+ newDishwasherForSession.toString());
                return "redirect:/products/dishwashers";
            }
            else{
                String messageAboutProductOver = "Product with id " + id + " ended";
                request.getSession().setAttribute("messageAboutProduct",messageAboutProductOver);
                logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product ended");
                return "redirect:/products/dishwashers";
            }
        }else {
            logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product not exist");
            return "redirect:/products/dishwashers";
        }

    }
}

