package StartApp.Controllers;


import StartApp.Entities.User;
import StartApp.Enums.Role;
import StartApp.Repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationPageController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "RegistrationPage";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User findedUser = userRepo.findByUsername(user.getUsername());
        if(findedUser!= null){
            model.put("message","User exists");
            return "RegistrationPage";
        }
        user.setActive(true);
        user.setUserOnSite(false);
        user.setRole(Collections.singleton(Role.USER));
        userRepo.save(user);
        logger.info("User with a name: '"+user.getUsername()+ "', registered on the site");
        return "redirect:/login";
    }
}
