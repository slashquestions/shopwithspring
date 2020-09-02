package StartApp.Controllers;

import StartApp.Entities.DefaultInterfaceForMachine;
import StartApp.Entities.Dishwasher;
import StartApp.Entities.Refrigerator;
import StartApp.Repositories.RefrigeratorsRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
@RequestMapping("/products/refrigerators")

public class RefrigeratorsListPageForUserController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private RefrigeratorsRepo refrigeratorsRepo;
    @GetMapping
    public String toUserPageRefrigerators(HttpServletRequest request, Model model, Authentication authentication){
        logger.info("User with name '"+authentication.getName()+"' entered on" + request.getRequestURI());
        List<DefaultInterfaceForMachine> basketProducts  = (List<DefaultInterfaceForMachine>) request.getSession().getAttribute("basketProducts");
        System.err.println(basketProducts);
        Iterable<Refrigerator> listRefrigerators = refrigeratorsRepo.findAll();
        model.addAttribute("refrigerators",listRefrigerators);
        return "RefrigeratorsPageForUser";
    }



    @PostMapping
    public String addRefrigeratorToBasketProducts(HttpServletRequest request,@RequestParam Integer id,Authentication authentication){
        Optional<Refrigerator> findedRefrigerator = refrigeratorsRepo.findById(id);
        if(findedRefrigerator.isPresent()) {
            Refrigerator findRefr = findedRefrigerator.get();
            int oldCounterProductFromDB = findRefr.getCounter();
            if(oldCounterProductFromDB>0){
                request.getSession().setAttribute("messageAboutProduct",null);

                List<DefaultInterfaceForMachine> basketProducts = (List<DefaultInterfaceForMachine>) request.getSession().getAttribute("basketProducts");
                for(DefaultInterfaceForMachine product: basketProducts){
                    if(product.getId() == id){
                        int oldCounter = product.getCounter();
                        oldCounter++;
                        product.setCounter(oldCounter);
                        findRefr.setCounter(--oldCounterProductFromDB);
                        refrigeratorsRepo.save(findRefr);
                        logger.info("User with name '"+authentication.getName()+"' added in basket :"+ product.toString());
                        return "redirect:/products/refrigerators";
                    }
                }
                Refrigerator newRefrigeratorForSession = new Refrigerator().createClone(findRefr);
                newRefrigeratorForSession.setCounter(1);
                basketProducts.add(newRefrigeratorForSession);
                request.getSession().setAttribute("basketProducts",basketProducts);
                findRefr.setCounter(--oldCounterProductFromDB);
                refrigeratorsRepo.save(findRefr);
                logger.info("User with name '"+authentication.getName()+"' added in basket :"+ newRefrigeratorForSession.toString());
                return "redirect:/products/refrigerators";
            }
            else{
                String messageAboutProductOver = "Product with id " + id + " ended";
                request.getSession().setAttribute("messageAboutProduct",messageAboutProductOver);
                logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product ended");
                return "redirect:/products/refrigerators";
            }
        }else {
            logger.info("User with name '"+authentication.getName()+"' tries to add in basket :"+"product with id " + id+ " but product not exist");
            return "redirect:/products/refrigerators";
        }

    }

    //КОД ВНИЗУ РАБОТАЕТ НО НЕ ОЧЕНЬ ВЕРНО (ТОЛЬКО ДЛЯ ХОЛОДИЛЬНИКОВ)
//    @PostMapping
//    public String addRefrigerator(HttpServletRequest request,@RequestParam String id,Model model) {
//        Optional<Refrigerator> findedRefrigerator = refrigeratorsRepo.findById(Integer.valueOf(id));
//        System.err.println(findedRefrigerator.isPresent());
//        if (findedRefrigerator.isPresent()) {
//            Refrigerator ourRefrigerator = findedRefrigerator.get();
//            List<Refrigerator> list = (List<Refrigerator>) request.getSession().getAttribute("basketproducts");
//            if (list == null) {
//                list = new ArrayList<>();
//                list.add(ourRefrigerator);
//                request.getSession().setAttribute("basketproducts", list);
//            } else {
//                list.add(ourRefrigerator);
//                request.getSession().setAttribute("basketproducts", list);
//            }
//            return "redirect:/products/refrigerators";
//
//        }else{
//            return "redirect:/products/refrigerators";
//        }
//
//
//    }
    }
