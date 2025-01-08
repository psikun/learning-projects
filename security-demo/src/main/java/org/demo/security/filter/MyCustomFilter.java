package org.demo.security.filter;

import jakarta.servlet.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class MyCustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before MyCustomFilter");


        TestingAuthenticationToken authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

        System.out.println("After MyCustomFilter");
    }


}
