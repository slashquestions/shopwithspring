package StartApp;

import StartApp.Repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LogoutSuccessHandlerActiveFalse implements LogoutSuccessHandler {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.getSession().setAttribute("loginName",null);
        try {
            Connection driver = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=sexy&serverTimezone=UTC");
            Statement stmt = driver.createStatement();
            String username = authentication.getName();
            stmt.execute("Update users set user_on_site = false where username="+"'"+username+"';");
            logger.info("User with a name: '"+username+ "' leave the site");
            driver.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/");
    }
}
