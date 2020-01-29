package org.java8.inaction.chapter6;

import org.java8.inaction.chapter5.Dish;
import org.java8.inaction.chapter5.MenuList;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class 归约和汇总 {



    public static void main(String[] args) {
        List<Dish> list = MenuList.getDishMenuList();

        归约和汇总.maxBy(list);
        归约和汇总.sumInt(list);
        归约和汇总.averaging(list);
        归约和汇总.summarizing(list);
    }

    static void maxBy(List<Dish> list){
        Comparator<Dish> comparator = Comparator.comparing(Dish::getCaluli);
        Optional<Dish> opt = list.stream().collect(Collectors.maxBy(comparator));
        if(opt.isPresent()){
            System.out.println(opt.get());
        }
    }

    static void sumInt(List<Dish> list){
        System.out.println(list
                .stream()
                .collect(
                        Collectors.summingInt(Dish::getCaluli)
                )
        );
    }

    static void averaging(List<Dish> list){
        System.out.println(
                list
                .stream()
                .collect(
                        Collectors.averagingInt(
                                Dish::getCaluli
                        )
                )
        );
    }

    static void summarizing(List<Dish> list){
        System.out.println(
                list.stream()
                .collect(
                        Collectors.summarizingInt(
                                Dish::getCaluli
                        )
                )
        );
    }
}
