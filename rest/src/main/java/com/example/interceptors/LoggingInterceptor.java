package com.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

public class LoggingInterceptor implements HandlerInterceptor{

    private static Logger logger = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uniqueID = UUID.randomUUID().toString();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String template = "RequestURI: %s | QueryParams: %s | RequestID: %s";
        String log = String.format(template, uri, queryString,uniqueID);
        logger.info(log);

        return true;
    }

}
