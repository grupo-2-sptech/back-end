package collectiva.org.collecta.domain.campanha.interceptor;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.UUID;
@RequiredArgsConstructor
public class CampanhaViewInterceptor implements HandlerInterceptor {

    private static final String CAMPAIGN_URI_PREFIX = "/campanhas/";
    private static final String VISUALIZATION_COOKIE_NAME = "Viewed";
    private static final String VISUALIZATION_COOKIE_VALUE = "checked";

    private final CampanhaService campanhaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith(CAMPAIGN_URI_PREFIX)) {
            String campanhaId = extractCampanhaId(requestURI);

            if (isValidUUID(campanhaId)) {
                UUID id = UUID.fromString(campanhaId);
                Campanha campanha = campanhaService.buscarExisteCampanha(id);

                if (campanha != null) {
                    if (isVisualizationCookiePresent(request)) {
                        return true;
                    }

                    campanhaService.incrementarVisualizacao(campanha);

                    addVisualizationCookie(response);
                }
            }
        }

        return true;
    }

    private String extractCampanhaId(String requestURI) {
        String campanhaId;
        int index = CAMPAIGN_URI_PREFIX.length();
        int endIndex = requestURI.indexOf("/", index);

        if (endIndex == -1) {
            campanhaId = requestURI.substring(index);
        } else {
            campanhaId = requestURI.substring(index, endIndex);
        }

        return campanhaId;
    }

    private boolean isValidUUID(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isVisualizationCookiePresent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return cookies != null && Arrays.stream(cookies)
                .anyMatch(cookie -> VISUALIZATION_COOKIE_NAME.equals(cookie.getName()) && VISUALIZATION_COOKIE_VALUE.equals(cookie.getValue()));
    }

    private void addVisualizationCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(VISUALIZATION_COOKIE_NAME, VISUALIZATION_COOKIE_VALUE);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
