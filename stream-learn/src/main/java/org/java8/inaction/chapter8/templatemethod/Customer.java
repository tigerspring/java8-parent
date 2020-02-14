package org.java8.inaction.chapter8.templatemethod;

public class Customer {
    private Integer id;
    private String name;
    private String other;

    public Customer(Integer id) {
        this.id = id;
        this.name="jxf";
        this.other="是否开心";
    }

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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
