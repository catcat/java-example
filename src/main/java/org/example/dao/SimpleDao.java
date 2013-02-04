package org.example.dao;

import org.example.mappings.Device;
import org.example.mappings.Tag;
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
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> customers = session.createQuery("FROM Customer").list();
        for(Customer c: customers) {
            Hibernate.initialize(c.getDevices());
        }
        return customers;
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
    public List<Tag> getTags() {
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery("FROM Tag").list();
        return tags;
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
        Hibernate.initialize(device);
        Hibernate.initialize(device.getTags());
        return device;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer getCustomer(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = (Customer)session.load(Customer.class, id);
        Hibernate.initialize(customer);
        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDevice(Device row) {
        Session session = sessionFactory.getCurrentSession();
        session.save(row);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDevice(Device row) {
        Session session = sessionFactory.getCurrentSession();
        Device oldDevice = getDevice(row.getId());
        row.setTags(oldDevice.getTags());//todo: find what is the standard workaround
        session.merge(row);
    }


}
