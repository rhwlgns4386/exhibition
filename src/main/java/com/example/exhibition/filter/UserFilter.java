package com.example.exhibition.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebFilter(urlPatterns = "/post")
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
            ((HttpServletResponse) response).sendError(404);
        }
    }

    @Override
    public void destroy() {
        System.out.println("-------------------------------------------------------------------");
    }
}
