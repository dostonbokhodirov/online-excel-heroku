package uz.excel.onlineexcel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.excel.onlineexcel.property.OpenApiProperties;
import uz.excel.onlineexcel.property.ServerProperties;
import uz.excel.onlineexcel.service.AuthService;


@SpringBootApplication
@EnableConfigurationProperties(
        {
                OpenApiProperties.class,
                ServerProperties.class
        }
)
@OpenAPIDefinition
public class OnlineExcelApplication {

    @Autowired
    AuthService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OnlineExcelApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run() {
//        return args -> {
//            userService.create(new AuthUserCreateDto("123", passwordEncoder.encode("123"),
//                    "Saydali", "+998973130080", "Saydali@gmail.com"));
//        };
//    }

}
