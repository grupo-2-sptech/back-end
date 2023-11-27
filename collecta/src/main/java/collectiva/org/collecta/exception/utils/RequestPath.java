package collectiva.org.collecta.exception.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestPath {
    public static String getRequestPath(HttpServletRequest request) {
        if (request != null) {
            return request.getRequestURI();
        }
        return "/";
    }
}
