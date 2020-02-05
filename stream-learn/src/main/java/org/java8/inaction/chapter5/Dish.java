package org.java8.inaction.chapter5;

public class Dish {

    public enum CaluliLevel{
        DIET,NORMAL,FAT
    }

    public enum DishType{
        MEAT,FISH,SUCAI
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

    public Dish(String name, Integer caluli, Enum caluType , Enum dishType) {
        this.name = name;
        this.caluli = caluli;
        this.caluType = caluType;
        this.dishType=dishType;
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

    private Enum dishType;

    public Enum getDishType() {
        return dishType;
    }

    public void setDishType(Enum dishType) {
        this.dishType = dishType;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", caluli=" + caluli +
                '}';
    }
}
