package org.example.controller;

import org.apache.commons.lang.StringUtils;
import org.example.mappings.Customer;
import org.example.mappings.Device;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.example.dao.SimpleDao;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Controller
public class PublicController implements ApplicationContextAware {

    //private static final Logger log = Logger.getLogger(PublicController.class);


    //@Autowired
    private WebApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = (WebApplicationContext)applicationContext;
    }

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    private JmsTemplate jmsTemplate;

    @InitBinder("device")
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {

        /*
        binder.setDisallowedFields(new String[] {"id"});
        binder.setAllowedFields(new String[] {"score"});
        */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

        binder.setRequiredFields(new String[] {"name"});

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

    @RequestMapping(value = "/txtEntity.htm")
    @ResponseBody
    public ResponseEntity<String> txtPageEntity() throws Exception {
        String rawText = "Hello\n World!";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(rawText, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/txt.htm", produces = "text/plain")
    @ResponseBody
    public String txtPage() throws Exception {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<100;i++) {
            sb.append(String.format("i=%d\n", i));
        }
        return sb.toString();
    }


    @RequestMapping("/")
    public ModelAndView home() throws Exception {
        return devicesPage();
    }

    @RequestMapping("/devices.htm")
    public ModelAndView devicesPage() throws Exception {
        ModelAndView info =  new ModelAndView("devices");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("rows", simpleDao.getDevices());
        return info;
    }
    @RequestMapping(value = "/simpleform.htm", method = RequestMethod.GET)
    public ModelAndView simpleformPageGet() throws Exception {
        ModelAndView info =  new ModelAndView("simpleform");
        //info.addObject("message", key+simpleDao.getCount());
        //info.addObject("msg", "None");
        return info;
    }

    @RequestMapping(value = "/simpleform.htm", method = RequestMethod.POST)
    public ModelAndView simpleformPagePost(final @RequestParam("msg") String msg ) throws Exception {
        ModelAndView info =  new ModelAndView("simpleform");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("msg", msg);

        if(!StringUtils.isEmpty(msg)) {
            for(int i=0;i<100;i++) {
                jmsTemplate.send("My.msgQueue,My.otherQueue,topic://My.firstTopic", new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        Integer value = (int)(Math.random()*1000.);
                        Message  message =  session.createTextMessage(msg + ":" + value);
                        message.setIntProperty("MYWEIGHT", value);
                        return message;
                    }
                });
            }
        }

        return info;
    }

    @RequestMapping("/device_edit/{id}.htm")
    public ModelAndView deviceEditPage(@PathVariable("id") Long id) throws Exception {
        return deiceForm(simpleDao.getDevice(id));
    }

    @RequestMapping(value = "/device_edit_post.htm", method = RequestMethod.POST)
    public ModelAndView deviceEditPost(@Valid @ModelAttribute("device")Device device, BindingResult result) throws Exception {
        DeviceValidator validator = new DeviceValidator();
        validator.validate(device, result);
        if(result.hasErrors()) {
            return deiceForm(device);
        }
        try {
            simpleDao.updateDevice(device);
        } catch (StaleObjectStateException ex){
            result.reject("validation.version");
            return deiceForm(device);
            //return deiceForm(simpleDao.getDevice(device.getId()));
        }

        return new ModelAndView("redirect:devices.htm");
    }

    private ModelAndView deiceForm(Device device) {
        ModelAndView info =  new ModelAndView("device_edit");
        //info.addObject("message", key+simpleDao.getCount());
        info.addObject("customers", simpleDao.getCustomers());
        info.addObject("device", device);

        info.addObject("currentTags", device.getTags());
        info.addObject("allTags", device.getTags());
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


class DeviceValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return Device.class.isAssignableFrom(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Device device = (Device)target;

        if(device.getScore()<0 || device.getScore()>1000) {
            errors.rejectValue("score", "validation.score.range", "SCORE RANGE INVALID");
        }

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "First name can't be blank");
    }
}
