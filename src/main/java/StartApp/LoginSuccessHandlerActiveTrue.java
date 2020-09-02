package StartApp;

import StartApp.Entities.DefaultInterfaceForMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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


public class LoginSuccessHandlerActiveTrue implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger();


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
            List<DefaultInterfaceForMachine> basketProducts = new ArrayList<>();
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
