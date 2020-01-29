package org.java8.inaction.chapter6;

import org.java8.inaction.chapter5.Dish;
import org.java8.inaction.chapter5.MenuList;

import javax.swing.text.html.Option;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * groupingBy(f) f是分类函数 => groupingBy(f,otList())
 */
public class 按子组收集数据 {
    public static void main(String[] args) {

        List<Dish> list = MenuList.getDishMenuTypeList();

        按子组收集数据.收集器的结果转换为另个类型(list);
        按子组收集数据.其他收集器例子(list);
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
}
