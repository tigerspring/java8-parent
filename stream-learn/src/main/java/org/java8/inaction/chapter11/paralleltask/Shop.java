package org.java8.inaction.chapter11.paralleltask;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 商铺
 */
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    Random random = new Random();


    public String getPrice(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name,price,code);
    }

    private double calculatePrice(String product){
        delay();

        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
