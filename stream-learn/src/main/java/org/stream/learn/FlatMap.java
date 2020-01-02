package org.stream.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

    public static void main(String[] args) {
        //使用flatMap把结果合并
        List<String> list = new ArrayList<>();
        list.add("1@2@3@4@5");
        list.add("6@7@8@9");
        list.add("10@11@12");
        System.out.println(list);
        Stream<String> stream = list.stream().flatMap(e->Stream.of(e.split("@")));
        System.out.println( stream.collect(Collectors.toList()));

        List<List<String>> list1 = new ArrayList<>();
        list1.add(Stream.of("1@2@3@4@5".split("@")).collect(Collectors.toList()));
        list1.add(Stream.of("6@7@8@9".split("@")).collect(Collectors.toList()));
        list1.add(Stream.of("10@11@12".split("@")).collect(Collectors.toList()));
        Stream<String> stream1 = list1.stream().flatMap(e-> e.stream() );
        System.out.println( stream1.collect(Collectors.toList()));
    }
}
