package ru.itmo.wp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itmo.wp.controller.ApiController;
import ru.itmo.wp.domain.Access;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.AUser;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String BEARER = "Bearer";

    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (ApiController.class.isAssignableFrom(method.getDeclaringClass())) {
                String authorization = request.getHeader("Authorization");
                Optional<User> user = Optional.empty();
                if (authorization != null && authorization.startsWith(BEARER)) {
                    String token = authorization.substring(BEARER.length()).trim();
                    user = jwtService.find(token);
                    user.ifPresent(value -> request.setAttribute("user", value));
                }

                if (method.getAnnotation(Guest.class) != null) {
                    return true;
                }

                if (user.isPresent()) {
                    if (method.getAnnotation(AUser.class) != null) {
                        return true;
                    }

                    AnyRole anyRole = method.getAnnotation(AnyRole.class);
                    if (anyRole == null) {
                        return true;
                    }
                    for (Access.Name name : anyRole.value()) {
                        for (Access access : user.get().getAccess()) {
                            if (access.getName().equals(name)) {
                                return true;
                            }
                        }
                    }
                }
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }
}
