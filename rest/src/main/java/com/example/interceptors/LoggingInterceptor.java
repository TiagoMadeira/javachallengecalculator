package com.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

public class LoggingInterceptor implements HandlerInterceptor{

    private static Logger logger = LogManager.getLogger("lognow");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        MDC.put("Request.id",  UUID.randomUUID().toString());
        MDC.put("Request.URI", request.getRequestURI());
        MDC.put("Request.QueryParams",request.getQueryString());
        logger.info("[Rest][Request][Input] received!");

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //Add info to MDC
        MDC.put("Response.status", String.valueOf(response.getStatus()));

        //Append MDC request.id to the response header
        response.addHeader("Request.id", MDC.get("Request.id"));

        logger.info("[Rest][Request][Output] resolved!");

        //Clear the MDC
        MDC.clear();
    }
}
