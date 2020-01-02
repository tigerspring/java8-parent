package stream;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        //n->n+1 => 做代表参数 右代表行为
        List<Integer> list = Stream.iterate(0, n->n+1).limit(10).collect(Collectors.toList());
        System.out.println(list);

        //收集到映射表中
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String,String> map = locales.filter(e-> !Objects.isNull(e.getDisplayCountry())).collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage,
                        l->l.getDisplayLanguage(l),
                        (existValue, newValue)-> existValue
                )
        );
        System.out.println(map);

        Stream<Locale> locales1 = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> mapSet = locales1.filter(e-> !StringUtils.isEmpty(e.getDisplayCountry())).collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l-> Collections.singleton(l.getDisplayLanguage(l)),
                        (a, b)-> {
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }
                )
        );
        System.out.println(mapSet);


        //群组和分区
        locales1 = Stream.of(Locale.getAvailableLocales());
        Map<String,List<Locale>> map1 = locales1.filter(e-> !StringUtils.isEmpty(e.getCountry())).collect(Collectors.groupingBy(Locale::getCountry));
        System.out.println(map1);
        System.out.println(map1.get("CH"));

        locales1 = Stream.of(Locale.getAvailableLocales());
        Map<Boolean,List<Locale>> map2 = locales1.filter(e-> !StringUtils.isEmpty(e.getCountry())).collect(Collectors.partitioningBy(e->e.getLanguage().equals("en")));
        System.out.println(map2.get(true));
        System.out.println(map2.get(false));
    }
}
