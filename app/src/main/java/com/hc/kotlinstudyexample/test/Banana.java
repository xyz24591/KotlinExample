package com.hc.kotlinstudyexample.test;

/**
 * Created by hcw  on 2020/11/7
 * 类描述：
 * all rights reserved
 */
public class Banana extends Fruit{

    private static String COLOR = "黄色的";

    public Banana() {}

    public static String getColor() {
        return COLOR;
    }

    public String getBananaInfo() {
        return flavor() + getColor();
    }

    private String flavor() {
        return "甜甜的";
    }

    public final boolean isLike() {
        return true;
    }
}