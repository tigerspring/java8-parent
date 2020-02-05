package org.java8.inaction.chaper2;

public class Apple {

    private Integer id;

    private String color;

    private Double weight;

    public Apple() {
    }

    public Apple(Integer id, String color, Double weight) {
        this.id = id;
        this.color = color;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
