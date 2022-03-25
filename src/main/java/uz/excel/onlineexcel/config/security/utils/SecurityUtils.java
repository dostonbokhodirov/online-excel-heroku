package uz.excel.onlineexcel.config.security.utils;

public class SecurityUtils {
    public final static String[] WHITE_LIST = {
            "/**",
            "/auth/token",
            "/auth/refresh-token",
            "/auth/register",
            "/swagger-ui/**",
            "/api-docs/**",
            "/api/v1/log/**"
    };
}
