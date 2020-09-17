package StartApp.Controllers;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.Order;
import StartApp.Entities.OrderItem;
import StartApp.Entities.User;
import StartApp.Repositories.OrderRepo;
import StartApp.Repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/makeorder")
public class MakeOrderController {


    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;


    @GetMapping
    public String formOrder(HttpServletRequest request){

        return "MakeOrderPage";
    }

    @PostMapping
    public String copmleteOrder(HttpServletRequest request,
                                @RequestParam String fullName,
                                @RequestParam String phoneNumber,
                                Authentication auth ){
        User user =  userRepo.findByUsername(auth.getName());
        Order newOrder = new Order();
        //newOrder.setNumberOrder(43);
        newOrder.setFullName(fullName);
        newOrder.setPhoneNumber(phoneNumber);
        newOrder.setIdClient(user);
        newOrder.setBasketOfItems((List<OrderItem>) request.getSession().getAttribute("basketProducts"));
        newOrder.setPrice((int)request.getSession().getAttribute("overPrice"));
//        newOrder.setNumberOrder((fullName.hashCode()+phoneNumber.hashCode()/newOrder.getPrice())/2);
        StringBuilder numberOrder = new StringBuilder();
        numberOrder.append(fullName.charAt(3));
        numberOrder.append(phoneNumber.charAt(3));
        numberOrder.append(phoneNumber.charAt(4));
        numberOrder.append(newOrder.getBasketOfItems().size());
        newOrder.setNumberOrder(numberOrder.toString());
        orderRepo.save(newOrder);
        logger.info("User with name '"+ auth.getName() +"' create Order with number :" + newOrder.getNumberOrder());
        request.getSession().setAttribute("basketProducts",null);
        return "MakeOrderPage";


    }
}
