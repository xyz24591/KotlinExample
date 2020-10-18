package com.hc.kotlinstudyexample.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Created by hcw  on 2020/10/18
 * 类描述：
 * all rights reserved
 */
@RunWith(Parameterized.class)
public class DateFormatTest {
    private String time;

    public DateFormatTest(String time){
        this.time = time;
    }

    @Parameters
    public static Collection primeNumbers(){
        return Arrays.asList(new String[]{
                "2017-10-15",
                "2017-10-15 16:00:02",
                "2017年10月15日 16时00分15秒",

        });
    }
    @Rule
    public MyRule rule = new MyRule();

    @Test(expected = ParseException.class)
    public void dateToStampTest() throws Exception{
        DateUtil.dateToStamp(time);
    }
}
