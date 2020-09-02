package StartApp.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/listproducts")
public class ListAllProductsPageForAdminController {

    @GetMapping
    public String toListProducts(Model model){
        return "PageProductsForAdmin";
    }
}
