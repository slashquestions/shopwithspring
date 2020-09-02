package StartApp.Controllers;

import StartApp.Entities.User;
import StartApp.Repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/listusers")
public class UsersListPageForAdminController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String toListUsers(Map<String,Object> model){
        List<User> users = userRepo.findAll();
        model.put("users",users);
        return "ListUsersPageForAdmin";
    }

    @PostMapping
    public String deleteUserFromDB(@RequestParam String id) {
        userRepo.deleteById(Integer.valueOf(id));
        logger.info("Admin deleted user with id "+id);
        return "redirect:/admin/listusers";
    }
}
