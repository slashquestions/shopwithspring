package StartApp.Controllers;

import StartApp.Entities.OrderItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/basketproducts")
public class BasketProductsPageController {
    private static final Logger logger = LogManager.getLogger();
// старый!!!!!!!!!!!
@GetMapping
public String toBasketProductsPage(HttpServletRequest request, Model model, Authentication authentication){
    logger.debug("User with name: '" + authentication.getName()+ "' on '/basketproducts'");
    List<OrderItem> basketProducts  = (List<OrderItem>) request.getSession().getAttribute("basketProducts");
    int overPrice = 0;
    for(OrderItem item:basketProducts){
        overPrice += item.getProduct().getPrice()*item.getCounter();
    }
    request.getSession().setAttribute("overPrice",overPrice);
    return "BasketProducts";
}


}
