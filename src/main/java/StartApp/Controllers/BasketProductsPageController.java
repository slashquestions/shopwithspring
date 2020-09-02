package StartApp.Controllers;

import StartApp.Entities.DefaultInterfaceForMachine;
import StartApp.Entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/basketproducts")
public class BasketProductsPageController {
    private static final Logger logger = LogManager.getLogger();
// старый!!!!!!!!!!!
    @GetMapping
    public String toBasketProductsPage(HttpServletRequest request, Authentication authentication){
        logger.debug("User with name: '" + authentication.getName()+ "' on '/basketproducts'");
        List<DefaultInterfaceForMachine> basketProducts  = (List<DefaultInterfaceForMachine>) request.getSession().getAttribute("basketProducts");
        return "BasketProducts";
    }



}
