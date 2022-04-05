package uz.excel.onlineexcel.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "service.prop")
public class ServerProperties {

    private String port;

    private String ip;

    private String url;

    private String protocol;

    public String getServerUrl() {
//        return this.protocol + "://" + this.ip + ":" + this.port;
        return "https://online-excel-heroku.herokuapp.com";
    }
}
