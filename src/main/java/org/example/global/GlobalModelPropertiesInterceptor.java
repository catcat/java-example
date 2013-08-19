package org.example.global;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalModelPropertiesInterceptor extends HandlerInterceptorAdapter {
    //after the handler is executed
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        if( modelAndView!=null) {
            modelAndView.addObject("globalModelAttr1", "Hello"+Math.random());
        }
    }
}
