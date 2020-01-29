package org.java8.inaction.chapter5.testing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestMain {
    static List<Transaction> transactions;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    public static void main(String[] args) {
        System.out.println(TestMain.get2011Bail());
        System.out.println(TestMain.getCities());
        System.out.println(TestMain.getCambridgeTrader());
        System.out.println(TestMain.getTraderNameString());
        System.out.println(TestMain.getAnyMatchTrader("Milan"));
        System.out.println(TestMain.getFindAnyTrader("Milan"));
        System.out.println(TestMain.getCambridgeTotalValue());
        System.out.println(TestMain.getMaxValue());
        System.out.println(TestMain.getMinValue());

        System.out.println("------------------------");
        TestMain.getCambridgeAllValue();
    }
    static List get2011Bail(){
         return TestMain.transactions.stream()
                .filter(bail->bail.getYear() == 2011)
//                .sorted((bail1,bail2)->bail2.getValue()-bail1.getValue())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    static List getCities(){
        return TestMain.transactions.stream()
                .map(bail->bail.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    static List getCambridgeTrader(){
        return TestMain.transactions.stream()
                .map(bail->bail.getTrader())
                .filter(trader->trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    static String getTraderNameString(){
        return TestMain.transactions.stream()
                .map(bail->bail.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1,n2)->n1+n2);
    }

    static Trader getFindAnyTrader(String city){
        return TestMain.transactions.stream()
                .map(bail->bail.getTrader())
                .filter(trader->trader.getCity().equals(city))
                .findAny()
                .get();
    }

    static boolean getAnyMatchTrader(String city){
        return TestMain.transactions.stream()
                .map(bail->bail.getTrader())
                .anyMatch(trader->trader.getCity().equals(city))
                ;
    }

    static void getCambridgeAllValue(){
        TestMain.transactions.stream()
                .filter(bail->bail.getTrader().getCity().equals("Cambridge"))
                .sorted((a,b)->b.getValue()-a.getValue())
                .forEach(bail-> System.out.println(bail.getValue()));
    }

    static Integer getCambridgeTotalValue(){
        return TestMain.transactions.stream()
                .filter(bail->bail.getTrader().getCity().equals("Cambridge"))
                .map(bail->bail.getValue())
                .reduce((a,b)->a+b)
                .get();
    }

    static Object getMaxValue(){
        return TestMain.transactions.stream()
                .map(bail->bail.getValue())
                .reduce(Integer::max)
                .get();
    }

    static Object getMinValue(){
        return TestMain.transactions.stream()
                .map(bail->bail.getValue())
                .reduce(Integer::min)
                .get();
    }
}
