package main.java.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Blog {
    private Integer id;
    private String name;
    private Address address;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date birth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", birth=" + birth +
                '}';
    }
}
