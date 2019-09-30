package config;

import com.lesson3.hometask.DAO.DAO;
import com.lesson3.hometask.Service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan({"com"})
@EnableWebMvc
public class AppContext implements WebMvcConfigurer {
    @Bean(name = "service")
    public Service service(){
        return new Service();
    }

    @Bean(name = "DAO")
    public DAO dao(){
        return new DAO();
    }
}
