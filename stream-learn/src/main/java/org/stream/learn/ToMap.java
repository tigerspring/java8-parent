package org.stream.learn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMap {
    public static void main(String[] args) {
        List<Map<String,Object>> rsList = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("name","jxf"+(i+1));
            map.put("uuName","jxf"+ UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
            map.put("prividerName","provider"+(i%10+1));
            rsList.add(map);
        }
        System.out.println(rsList);
        Map<Object,Long> map = rsList.stream().collect(
                Collectors.groupingBy(
                        e->e.get("prividerName"),
                        Collectors.counting()
                )
        );
        System.out.println(map);

        Map<Object,Map<String,Object>> map1 = rsList.stream().collect(
                Collectors.toMap(
                        e->e.get("id"),Function.identity()
                )
        );
        System.out.println(map1);
    }
}
