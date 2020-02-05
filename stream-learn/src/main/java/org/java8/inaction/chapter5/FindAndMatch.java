package org.java8.inaction.chapter5;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindAndMatch {

    public static void main(String[] args) {
        List<String> menuList = MenuList.getMenuList();
        FindAndMatch.matchTest(menuList);
        FindAndMatch.findTest(menuList);
    }

    static void matchTest(List<String> menuList){
        //menuList中只要有肉就可以,返回true
        if(menuList.stream().anyMatch(菜-> 菜.contains("肉") )){
            System.out.println("有肉菜");
        }
        //menuList所有的菜都包含肉,返回true
        if(menuList.stream().allMatch(菜-> 菜.contains("肉") )){
            System.out.println("所有肉菜");
        }
        //menuList所有的菜都不包含韭菜，返回true
        if(menuList.stream().noneMatch(菜-> 菜.contains("韭菜") )){
            System.out.println("没有韭菜");
        }
    }

    static void findTest(List<String> menuList){
        Optional<String> opt =
                 menuList.stream()
                .filter(menu-> menu.contains("肉"))
                .findAny();
        System.out.println("有肉菜 比如："+opt.get());

        List<Integer> list = menuList.stream()
                .map(s->s.length())
                .collect(Collectors.toList());
    }
}


