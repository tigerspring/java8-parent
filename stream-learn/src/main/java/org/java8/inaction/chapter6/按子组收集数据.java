package org.java8.inaction.chapter6;

import org.java8.inaction.chapter5.Dish;
import org.java8.inaction.chapter5.MenuList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * groupingBy(f) f是分类函数 => groupingBy(f,toList())
 */
public class 按子组收集数据 {
    public static void main(String[] args) {

        List<Dish> list = MenuList.getDishMenuTypeList();
        List<Dish> list1 = MenuList.getDishMenuDishTypeList();

        按子组收集数据.收集器的结果转换为另个类型(list);
        按子组收集数据.其他收集器例子(list);
        按子组收集数据.mapping收集器(list1);
        按子组收集数据.mapping收集器2(list1);
    }

    /**
     * 获取每个菜品分类下最大卡路里的菜品
     * @param list
     */
    public static void 收集器的结果转换为另个类型(List<Dish> list){
        Map<Enum<Dish.CaluliLevel>,Dish> map = list.stream().collect(
                Collectors.groupingBy(Dish::getCaluType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Dish::getCaluli)),
                                Optional::get)));

        System.out.println(map);
    }

    public static void 其他收集器例子(List<Dish> list){
        Map<Enum<Dish.CaluliLevel>,Integer> map = list.stream().collect(Collectors.groupingBy(
                Dish::getCaluType,
                Collectors.summingInt(Dish::getCaluli)
        ));
        System.out.println(map);
    }

    /**
     * mapping接受两个参数
     *  参数1：将流中（指的是groupingBy的下游流->groupby的结果）的元素做变换
     *  参数2：将变换的结果收集起来
     * @param list
     */
    public static void mapping收集器(List<Dish> list){
        Map<Enum<Dish.DishType>, Set<Dish.CaluliLevel>> map = list.stream().collect(
                Collectors.groupingBy(
                        Dish::getDishType,
                        Collectors.mapping(dish->{
                            if(dish.getCaluli()>=100){
                                return Dish.CaluliLevel.FAT;
                            }else if(dish.getCaluli() < 50){
                                return Dish.CaluliLevel.DIET;
                            }else{
                                return Dish.CaluliLevel.NORMAL;
                            }
                        },Collectors.toSet())
                )
        );
        System.out.println(map);
    }

    /**
     * mapping接受两个参数
     *  参数1：将流中（指的是groupingBy的下游流->groupby的结果）的元素做变换
     *  参数2：将变换的结果收集起来,制定set的类型
     * @param list
     */
    public static void mapping收集器2(List<Dish> list){
        Map<Enum<Dish.DishType>, Set<Dish.CaluliLevel>> map = list.stream().collect(
                Collectors.groupingBy(
                        Dish::getDishType,
                        Collectors.mapping(dish->{
                            if(dish.getCaluli()>=100){
                                return Dish.CaluliLevel.FAT;
                            }else if(dish.getCaluli() < 50){
                                return Dish.CaluliLevel.DIET;
                            }else{
                                return Dish.CaluliLevel.NORMAL;
                            }
                        },Collectors.toCollection(HashSet::new))
                )
        );
        System.out.println(map);
    }
}
