package com.imooc.filter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/center/*",filterName = "myFilter")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("111111111");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chan) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("2222222222111111");
        HttpHeaders headers = new HttpHeaders();
        String authorization = req.getHeader("Authorization");
        String name = request.getParameter("name");
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        System.out.println(authorization);
        System.out.println(name);
        System.out.println(requestURI);
        System.out.println(method);
        String s = "123";
        chan.doFilter(req,res);

    }

    @Override
    public void destroy() {
        System.out.println("333333333333333");
    }
}
