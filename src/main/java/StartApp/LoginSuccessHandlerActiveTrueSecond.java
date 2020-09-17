package StartApp;

import StartApp.Entities.DefaultClassForMachine;
import StartApp.Entities.OrderItem;
import StartApp.Entities.User;
import StartApp.Repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LoginSuccessHandlerActiveTrueSecond implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger();

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String urlrequest = request.getRequestURI();
        String username = authentication.getName();
        request.getSession().setAttribute("loginName",username);
        try {
            Connection driver = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=sexy&serverTimezone=UTC");
            Statement stmt = driver.createStatement();
            stmt.execute("Update users set user_on_site = true where username="+"'"+username+"';");
            logger.info("User with a name: '"+username+ "', on the site");
            List<OrderItem> basketProducts = new ArrayList<>();
            request.getSession().setAttribute("basketProducts",basketProducts);
            driver.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!username.equals("admin")){
            response.sendRedirect("/");
        }else {
            response.sendRedirect("/admin");
        }
    }
}
