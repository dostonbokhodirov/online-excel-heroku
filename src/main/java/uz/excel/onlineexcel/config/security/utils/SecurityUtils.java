package uz.excel.onlineexcel.config.security.utils;

public class SecurityUtils {
    public final static String[] WHITE_LIST = {
            "/api/login",
            "/api/token/refresh",
            "/swagger-ui/**",
            "/api/docs/**",
            "/api/v1/auth/login",
            "/excel/get"
    };
}
