package com.socialmedia.app.config;

import com.socialmedia.app.model.User;
import com.socialmedia.app.service.LoginRecordService;
import com.socialmedia.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final LoginRecordService loginRecordService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService, LoginRecordService loginRecordService) {
        this.userService = userService;
        this.loginRecordService = loginRecordService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                       Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Optional<User> userOptional = userService.findByUsername(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String ipAddress = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            
            // Record the login
            loginRecordService.recordLogin(user, ipAddress, userAgent);
        }
        
        // Redirect to home page after successful login
        response.sendRedirect("/");
    }
}
