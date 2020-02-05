package org.java8.inaction.chapter6;

import org.java8.inaction.chapter5.Dish;
import org.java8.inaction.chapter5.MenuList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 分组 {

    public static void main(String[] args) {
        List<Dish> typeList = MenuList.getDishMenuTypeList();
        List<Dish> list = MenuList.getDishMenuTypeList();

        分组.分组函数(typeList);
        分组.分组函数2(list);
    }

    public static void 分组函数(List<Dish> list){
        Map<Enum,List<Dish>> map = list.stream().collect(
                Collectors.groupingBy(Dish::getCaluType)
        );
        System.out.println(map);
    }

    public static void 分组函数2(List<Dish> list){
        Map<Enum,List<Dish>> map = list.stream().collect(
                Collectors.groupingBy(dish->{
                    if(dish.getCaluli()>=100){
                        return Dish.CaluliLevel.FAT;
                    }else{
                        return Dish.CaluliLevel.DIET;
                    }
                })
        );
        System.out.println(map);
    }
}
