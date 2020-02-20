package org.java8.inaction.chapter11.singletask;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Shop {
    private String product;

    private String name;

    public String getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 同步处理
     * @param product
     * @return
     */
    public double getPrice(String product){
        return calculatePrice(product);
    }

    /**
     * 异步处理
     * 通过new CompletableFuture 来返回异步结果
     * 无错误处理
     * @param product
     * @return
     */
    public CompletableFuture getPricesASync(String product){
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            double dd = calculatePrice(product);
            completableFuture.complete(dd);
        });
        return completableFuture;
    }

    /**
     * 异步处理
     * 通过new CompletableFuture 来返回异步结果
     * 有错误处理
     * @param product
     * @return
     */
    public CompletableFuture getPricesASyncWithException(String product){
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            try{
                double dd = calculatePrice(product);
                completableFuture.complete(dd);
            }catch (Exception e){
                completableFuture.completeExceptionally(e);
            }

        });
        return completableFuture;
    }

    /**
     * 异步处理
     * 通过工厂方法 + lambda 创建，并返回异步结果
     * 此方法自带异常处理
     * @param product
     * @return
     */
    public CompletableFuture getPricesASyncWithFactory(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }


    public double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0)+product.charAt(1);
    }

    public void delay(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
