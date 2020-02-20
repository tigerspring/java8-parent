package org.java8.inaction.chapter11.paralleltask;

/**
 * 报价服务
 */
public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, Double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s){
        String ss[] = s.split(":");
        return new Quote(ss[0],Double.parseDouble(ss[1]),Discount.Code.valueOf(ss[2]));
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
