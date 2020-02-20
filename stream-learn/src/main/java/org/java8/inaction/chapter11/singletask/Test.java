package org.java8.inaction.chapter11.singletask;

import org.java8.inaction.chapter11.singletask.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Test {
    static final List<Shop> shops;
    static{
        shops = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("1111"),
                new Shop("2222"),
                new Shop("3333"),
                new Shop("4444"),
                new Shop("5555"),
                new Shop("BuyItAll"),
                new Shop("1"),
                new Shop("2"),
                new Shop("3"),
                new Shop("4"),
                new Shop("5"),
                new Shop("6"),
                new Shop("7"),
                new Shop("8"),
                new Shop("9"),
                new Shop("11"),
                new Shop("12"),
                new Shop("13"),
                new Shop("14"),
                new Shop("15"),
                new Shop("16"),
                new Shop("17")
        );
    }

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r)-> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) {
        //c串行测试
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //并行测试
        start = System.nanoTime();
        System.out.println(findPricesParallel("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //CompletableFuture 并行测试
        start = System.nanoTime();
        System.out.println(findPricesCompletableFuture("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //CompletableFuture 并行测试
        start = System.nanoTime();
        System.out.println(findPricesCompletableFutureTheadPool("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        System.out.println( Runtime.getRuntime().availableProcessors());
    }

    /**
     * 串行
     * @param product
     * @return
     */
    static List<String> findPrices(String product){
        return shops
                .stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 并行
     * @param product
     * @return
     */
    static List<String> findPricesParallel(String product){
        return shops
                .parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    static List<String> findPricesCompletableFuture(String product){
        List<CompletableFuture<String>> list = shops.stream()
                .map(
                        shop -> CompletableFuture.supplyAsync(
                                ()->String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
                        )
                ).collect(Collectors.toList());

        return list.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    static List<String> findPricesCompletableFutureTheadPool(String product){
        List<CompletableFuture<String>> list = shops.stream()
                .map(
                        shop -> CompletableFuture.supplyAsync(
                                ()->String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)),
                                executor
                        )
                ).collect(Collectors.toList());

        return list.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }



}
