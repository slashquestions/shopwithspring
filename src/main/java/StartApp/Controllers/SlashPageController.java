package StartApp.Controllers;

import StartApp.StartApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class SlashPageController {

    @GetMapping
    public String toSlashPage(Map<String,Object> model){
        return "SlashPage";
    }
}
