package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


import org.example.dao.SimpleDao;



@Controller
public class PublicController {

    //private static final Logger log = Logger.getLogger(PublicController.class);


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    private WebApplicationContext ctx;




    @RequestMapping("/document.htm")
    public ModelAndView documentPage(@RequestParam("key") String key) throws Exception {
        ModelAndView info =  new ModelAndView("info");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("message", key + "Hello");
        info.addObject("customers", simpleDao.getCustomersWithDevices());
        return info;
    }


    @RequestMapping("/ya.htm")
    public void ya(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resource webpage = ctx.getResource("http://ya.ru");
        FileCopyUtils.copy(webpage.getInputStream(), response.getOutputStream());
    }

    private ModelAndView getErrorMav(String message) {
        ModelAndView error =  new ModelAndView("error");
        error.addObject("message", message);
        return error;
    }

}
