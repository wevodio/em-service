package com.abnamro.emservice.filter;

import com.abnamro.emservice.entities.RolesEnum;
import com.abnamro.emservice.exception.ErrorMessageException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static com.abnamro.emservice.utils.EmployeeUtils.isRoleValid;
import static com.abnamro.emservice.utils.EmployeeUtils.validateAndRetrieveRoleEnum;

@Component
@Slf4j
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final String ROLE_PREFIX = "ROLE_";

    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestApiKey = request.getHeader("x-api-key");
        String role = request.getHeader("Role");

        if(!apiKey.equals(requestApiKey)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized. API KEY is incorrect");
        }
        else if(!isRoleValid(role)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized. Unknown role");
        } else {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken("apiKeyUser", null,
                            Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX.concat(role))));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }




}