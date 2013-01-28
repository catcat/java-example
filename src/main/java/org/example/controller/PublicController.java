package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.example.dao.SimpleDao;



@Controller
public class PublicController {

    //private static final Logger log = Logger.getLogger(PublicController.class);


    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private SimpleDao simpleDao;

    @RequestMapping("/devices.htm")
    public ModelAndView devicesPage() throws Exception {
        ModelAndView info =  new ModelAndView("devices");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("rows", simpleDao.getDevices());
        return info;
    }

    @RequestMapping("/device_edit/{id}.htm")
    public ModelAndView deviceEditPage(@PathVariable("id") Long id) throws Exception {
        ModelAndView info =  new ModelAndView("device_edit");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("row", simpleDao.getDevice(id));
        return info;
    }

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
