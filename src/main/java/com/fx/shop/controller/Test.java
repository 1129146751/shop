package com.fx.shop.controller;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        String  str="1234.jpg";
        int i=str.lastIndexOf(".");
        System.out.println(str.substring(i));
        BigDecimal b=new BigDecimal(1);
        System.out.println(b.add(new BigDecimal("12.69")));
    }
}
