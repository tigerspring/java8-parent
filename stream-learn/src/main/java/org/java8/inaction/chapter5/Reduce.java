package org.java8.inaction.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Reduce {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1 ; i <= 5000000 ;i ++){
            list.add(i);
        }

        long start = System.currentTimeMillis();
        System.out.println(Reduce.getSumBingxing(list));
        System.out.println((System.currentTimeMillis()-start)+"ms");
        System.out.println(Reduce.getSum(list));
        System.out.println((System.currentTimeMillis()-start)+"ms");


        System.out.println(Reduce.sumOpt(list));
        System.out.println(Reduce.getMin(list));
        System.out.println(Reduce.getMax(list));
        System.out.println(Reduce.countDishes());
    }

    /**
     * Reduce有初始值返回基本类型
     * @param list
     * @return
     */
    public static Integer getSum(List<Integer> list){
        Integer sum = list.stream().reduce(0,(a, b)->a+b);
        return sum;
    }

    /**
     * Reduce有初始值返回基本类型
     * @param list
     * @return
     */
    public static Integer getSumBingxing(List<Integer> list){
        Integer sum = list.parallelStream().reduce(0,(a, b)->a+b);
        return sum;
    }

    /**
     * Reduce没有初始值返回optional类型
     * @param list
     * @return
     */
    public static Integer sumOpt(List<Integer> list){
        Optional<Integer> optional = list.stream().reduce((a, b)->a+b);
        return optional.get();
    }

    /**
     * 最小值
     * @param list
     * @return
     */
    public static Integer getMin(List<Integer> list){
        Optional<Integer> minOpt = list.stream().reduce(Integer::min);
        return minOpt.get();
    }

    /**
     * 最大值
     * @param list
     * @return
     */
    public static Integer getMax(List<Integer> list){
        Optional<Integer> minOpt = list.stream().reduce(Integer::max);
        return minOpt.get();
    }

    public static Integer countDishes(){
        List<Dish> dishList = MenuList.getDishMenuList();
        Integer dishCount = dishList.stream().map(dish -> 1).reduce(0,(a,b)->a+b);
        return dishCount;
    }
}
