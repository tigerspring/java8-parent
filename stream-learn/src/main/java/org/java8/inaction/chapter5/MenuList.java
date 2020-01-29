package org.java8.inaction.chapter5;

import java.util.ArrayList;
import java.util.List;

public class MenuList {

    static final List<String> menuList = new ArrayList<>();
    static final List<Dish> dishMenuList = new ArrayList<>();
    static final List<Dish> dishMenuTypeList = new ArrayList<>();

    static {
        menuList.add("五花肉");
        menuList.add("糖醋鱼");
        menuList.add("果仁菠菜");
        menuList.add("麻婆豆腐");
        menuList.add("锅包肉白醋加糖");

        dishMenuList.add(new Dish("五花肉",100));
        dishMenuList.add(new Dish("糖醋鱼",70));
        dishMenuList.add(new Dish("果仁菠菜",30));
        dishMenuList.add(new Dish("麻婆豆腐",40));
        dishMenuList.add(new Dish("锅包肉白醋加糖",120));

        dishMenuTypeList.add(new Dish("五花肉",100, Dish.CaluliLevel.FAT));
        dishMenuTypeList.add(new Dish("糖醋鱼",70, Dish.CaluliLevel.NORMAL));
        dishMenuTypeList.add(new Dish("果仁菠菜",30,Dish.CaluliLevel.DIET));
        dishMenuTypeList.add(new Dish("麻婆豆腐",40,Dish.CaluliLevel.DIET));
        dishMenuTypeList.add(new Dish("锅包肉白醋加糖",120, Dish.CaluliLevel.FAT));
    }

    public static List<String> getMenuList(){
        return menuList;
    }

    public static List<Dish> getDishMenuList(){
        return dishMenuList;
    }
    public static List<Dish> getDishMenuTypeList(){
        return dishMenuTypeList;
    }
}
