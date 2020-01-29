package org.java8.inaction.chapter5;

public class Dish {

    public enum CaluliLevel{
        DIET,NORMAL,FAT
    }

    public Dish() {
    }

    public Dish(String name, Integer caluli) {
        this.name = name;
        this.caluli = caluli;
    }


    public Dish(String name, Integer caluli, Enum caluType) {
        this.name = name;
        this.caluli = caluli;
        this.caluType = caluType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaluli() {
        return caluli;
    }

    public void setCaluli(Integer caluli) {
        this.caluli = caluli;
    }

    private String name;

    private Integer caluli;

    public Enum getCaluType() {
        return caluType;
    }

    public void setCaluType(Enum caluType) {
        this.caluType = caluType;
    }

    private Enum caluType;


    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", caluli=" + caluli +
                '}';
    }
}
