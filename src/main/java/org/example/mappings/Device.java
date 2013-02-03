package org.example.mappings;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ag
 * Date: 12/20/12
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Device implements Serializable {
    private long id;

    @javax.persistence.Column(name = "id")
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @NotNull
    @Size(max=8)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int score;

    @javax.persistence.Column(name = "score")
    @Basic
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    private Date created;

    @javax.persistence.Column(name = "created")
    @Basic
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private boolean active;

    @javax.persistence.Column(name = "active")
    @Basic
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id != device.id) return false;
        if (score != device.score) return false;
        if (!name.equals(device.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + score;
        return result;
    }

    private Customer customer;

    @ManyToOne
    @JoinColumn(name="customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
