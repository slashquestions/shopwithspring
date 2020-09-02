package StartApp.Controllers;

import StartApp.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/logout")
public class LogoutPageController {


    @GetMapping
    public String logoutGet(Map<String,Object> model){
        return "LogoutPage";
    }
    @PostMapping
    public String logoutPost(Map<String,Object> model){

        return "SlashPage";
    }
}
