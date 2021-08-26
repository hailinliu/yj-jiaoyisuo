package com.sskj.lib.bean;

public class RateBean {
    private String name;
    private String rate;
    private String simple;

    public String getSimple() {
       switch (name){
           case "CNY":
               return "$";
           case "EUR":
              return "€";
           case "HKD":
               return "HK$";
           case "RUB":
               return "P";
           case "KRW":
               return "₩";
           case "MYR":
               return "M$";
           case "TWD":
               return "NT$";
           case "USD":
               return "$";

       }
        return "$";
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
