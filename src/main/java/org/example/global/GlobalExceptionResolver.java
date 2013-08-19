package org.example.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    private final ResponseStatusExceptionResolver annotatedExceptionHandler = new ResponseStatusExceptionResolver();

    private final DefaultHandlerExceptionResolver defaultExceptionHandler = new DefaultHandlerExceptionResolver();

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception originalException) {

        String url = request.getRequestURL().toString();
        try {
            if (originalException instanceof EmptyResultDataAccessException) {
                LOG.info("EmptyResultDataAccessException causes 404 response: "+url, originalException);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return new ModelAndView();
            } else {
                LOG.warn("Unexpected exception: "+url, originalException);
            }
        } catch (Exception ex) {
            LOG.warn("Can't process exception ", ex);
        }

        ModelAndView result = annotatedExceptionHandler.resolveException(request, response, handler, originalException);
        if (result != null) {
            return result;
        }
        return defaultExceptionHandler.resolveException(request, response, handler, originalException);
    }

}
