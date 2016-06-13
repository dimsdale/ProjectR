package My;

import My.config.WebAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;

import java.util.logging.Logger;

@SpringBootApplication
@EnableCaching(mode = AdviceMode.PROXY)
public class TestApplication extends SpringBootServletInitializer {

    public static final Logger logger = Logger.getLogger(TestApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{TestApplication.class, WebAppConfig.class}, args);
    }
}
