package com.example.exhibition.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebFilter(urlPatterns = "/user/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-------------------------------------------------------------------");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpSession session=((HttpServletRequest) request).getSession();
        if(session.getAttribute("userId")==null){
            HttpServletResponse httpServletResponse=(HttpServletResponse) response;
            ((HttpServletResponse) response).sendRedirect("/");
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("-------------------------------------------------------------------");
    }
}
