package org.example.controller;

import org.example.mappings.Customer;
import org.example.mappings.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.example.dao.SimpleDao;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;


@Controller
public class PublicController {

    //private static final Logger log = Logger.getLogger(PublicController.class);


    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private SimpleDao simpleDao;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Customer.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if("NONE".equals(text)){
                    super.setValue(null);
                } else {
                    Customer customer = simpleDao.getCustomer(Long.parseLong(text));
                    super.setValue(customer);
                }
            }
        });
    }

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
        info.addObject("customers", simpleDao.getCustomers());
        info.addObject("command", simpleDao.getDevice(id));
        return info;
    }

    @RequestMapping(value = "/device_edit_post.htm", method = RequestMethod.POST)
    public String deviceEditPost(@ModelAttribute("device")Device device, BindingResult result) {
        simpleDao.updateDevice(device);
        return "redirect:devices.htm";
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
