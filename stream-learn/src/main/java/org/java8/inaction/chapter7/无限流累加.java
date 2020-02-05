package org.java8.inaction.chapter7;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 无限流累加并测量性能
 */
public class 无限流累加 {

    public static void main(String[] args) {
        Stream<Long> 无限流 = Stream.iterate(1l,i->i+1);
        Stream<Long> 无限流2 = Stream.iterate(1l,i->i+1);


        int limit = 1000000000;


        LongStream 无限流3 = LongStream.rangeClosed(1,limit);
        LongStream 无限流4 = LongStream.rangeClosed(1,limit);

        long start = System.currentTimeMillis();
//        无限流累加.并行累加值(无限流,limit);
        System.out.println("并行: " +(System.currentTimeMillis()-start)+"ms");
        start = System.currentTimeMillis();
//        无限流累加.串行累加值(无限流2,limit);
        System.out.println("串行: " +(System.currentTimeMillis()-start)+"ms");

        start = System.currentTimeMillis();
        无限流累加.优化并行累加值(无限流3);
        System.out.println("优化并行: " +(System.currentTimeMillis()-start)+"ms");

        start = System.currentTimeMillis();
        无限流累加.优化串行累加值(无限流4);
        System.out.println("优化串行: " +(System.currentTimeMillis()-start)+"ms");
    }

    /**
     * 通过测试此方法并行性能没有提升
     * 原因：
     *
     *  1.执行过程有自动拆箱装箱过程
     *  2.stream iterate分成多个独立块来并行执行
     *
     * @param stream
     * @param limit
     */
    public static void 并行累加值(Stream<Long> stream,int limit){
        Long count = stream
                .limit(limit)
                .parallel()
                .reduce(0l,(a,b)->a+b);
        System.out.println(count);
    }

    /**
     * 可优化原因
     *      1.LongStream.rangeClosed返回的就是原始long型，没有自动装箱，拆箱开销
     *      2.LongStream.rangeClosed会生成数字范围，很容易拆成独立的小块，如
     *      范围1-20, 分为1-5,6-10,11-15，16-20
     * @param stream
     */
    public static void 优化并行累加值(LongStream stream){
        long count = stream.parallel().reduce(0,(a,b)->a+b);
        System.out.println(count);
    }



    public static void 串行累加值(Stream<Long> stream,int limit){
        Long count = stream
                .limit(limit)
                .reduce(0l,(a,b)->a+b);
        System.out.println(count);
    }

    public static void 优化串行累加值(LongStream stream){
        long count = stream.reduce(0,(a,b)->a+b);
        System.out.println(count);
    }
}
