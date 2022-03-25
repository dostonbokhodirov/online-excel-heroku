package uz.excel.onlineexcel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.excel.onlineexcel.property.OpenApiProperties;
import uz.excel.onlineexcel.property.ServerProperties;


@SpringBootApplication
@EnableConfigurationProperties(
        {
                OpenApiProperties.class,
                ServerProperties.class
        }
)
@OpenAPIDefinition
public class OnlineExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExcelApplication.class, args);
    }

}
