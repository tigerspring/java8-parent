package org.java8.inaction.chapter5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {

    static List<String> wordList = new ArrayList<>();

    static{
        wordList.add("Hello");
        wordList.add("World!");
    }

    public static void main(String[] args) {
       List<String[]> list = wordList.stream()
               .map(word->word.split(""))
               .distinct()
               .collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list.size());

        List<Stream<String>> list1 = wordList
                .stream()
                .map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list1);

        List<String> list2 = wordList
                .stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list2);


        List<String> ss = Arrays.asList("Goodbye".split("")) ;
        System.out.println(ss);

        System.out.println(平方数(Stream.of(2,3,4,5,6).collect(Collectors.toList())));
        对数();
    }

    static Object 平方数(List<Integer> list){
        return list.stream().map(obj-> obj * obj).collect(Collectors.toList());
    }

    /**
     * flatMap 和 map 对比总结
     *  1.flatMap 接受返回Stream类型的行为参数，map可以接受返回任何类型的行为参数
     *  2.flatMap 讲行为参数返回的stream映射并解析成Stream的内容，map将行为参数返回的参数映射为stream
     *  3.flatMap 可以折叠数组内容
     */
    static void 对数(){
        List<Integer> list1 = Stream.of(1,2,3,6).collect(Collectors.toList());
        List<Integer> list2 = Stream.of(3,4,5).collect(Collectors.toList());
        List<List<Integer>> aa = list1.stream()
                .flatMap(
                        first ->
                                list2.stream()
                                        .map(second -> {
                                            List<Integer> tmpList = new ArrayList();
                                            tmpList.add(first);
                                            tmpList.add(second);
                                            return tmpList;
                                        })
                )
                .collect(Collectors.toList());

        List<Stream<int[]>> aaa = list1.stream()
                .map(
                        first ->
                                list2.stream()
                                        .map(second -> new int[]{first, second})
                )
                .collect(Collectors.toList());


        List<List<Integer>> aa3 = list1.stream()
                .flatMap(
                        first ->
                                list2.stream()
                                        .map(second -> {
                                            List<Integer> tmpList = new ArrayList();
                                            if(first % 3==0 && second % 3==0){
                                                tmpList.add(first);
                                                tmpList.add(second);
                                            }else{
                                                return null;
                                            }
                                            return tmpList;
                                        })
                ).filter(obj-> Objects.nonNull(obj))
                .collect(Collectors.toList());

        System.out.println("aaa:"+aa);
        System.out.println("aaa:"+aaa);
        System.out.println("被3整除的对数:"+aa3);

    }
}

