package com.github.baby.owspace.util;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * owspace
 */
public class TimeUtil {
    public static long getCurrentSeconds(){
        long ls = System.currentTimeMillis()/1000;
        return ls;
    }
}
