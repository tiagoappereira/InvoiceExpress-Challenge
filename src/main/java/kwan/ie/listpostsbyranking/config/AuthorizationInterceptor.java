package kwan.ie.listpostsbyranking.config;

import kwan.ie.listpostsbyranking.exception.AuthorizationException;
import kwan.ie.listpostsbyranking.presentation.AuthorizationRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(AuthorizationRequired.class)) {
            String[] authHeader = request.getHeader("Authorization").split(" ");
            if (!authHeader[0].equals("Basic") || authHeader.length < 2)
            throw new AuthorizationException("Access was denied because the required authorization was not granted");
        }
        return true;
    }
}
