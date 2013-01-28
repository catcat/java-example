package org.example.dao;

import org.example.mappings.Device;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.example.mappings.Customer;

import java.util.List;


@Service
public class SimpleDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    public int getCount() {
        Session session = sessionFactory.getCurrentSession();

        Customer newcomer = new Customer();
        /*
        newcomer.setName("New"+Math.random());
        newcomer.setDiscountAmount((int)(Math.random()*100.));
        session.save(newcomer);
        */
        //session.flush();

        List<Customer> customers = session.createQuery("FROM Customer").list();


        return 100+customers.size();
    }

    @Transactional(propagation = Propagation.REQUIRED)
         public List<Customer> getCustomersWithDevices() {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> customers = session.createQuery("FROM Customer").list();
        for(Customer c: customers) {
            Hibernate.initialize(c.getDevices());
        }
        return customers;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Device> getDevices() {
        Session session = sessionFactory.getCurrentSession();
        List<Device> devices = session.createQuery("FROM Device").list();
        return devices;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Device getDevice(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Device device = (Device)session.load(Device.class, id);
        return device;
    }
}
