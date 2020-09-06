package StartApp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.support.Repositories;
import org.springframework.security.core.userdetails.User;




@SpringBootApplication
@EntityScan(basePackages = {"StartApp.Entities"})

//@EnableJpaRepositories("StartApp.Repositories")
public class StartApp {
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.warn("Start App");
        SpringApplication.run(StartApp.class,args);
    }
}
