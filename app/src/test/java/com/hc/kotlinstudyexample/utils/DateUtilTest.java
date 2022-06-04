package com.hc.kotlinstudyexample.utils;

import android.provider.ContactsContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static kotlinx.coroutines.flow.FlowKt.startWith;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.hamcrest.core.CombinableMatcher;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hcw  on 2020/10/18
 * 类描述：
 * all rights reserved
 */
public class DateUtilTest {

    private String time = "2017-10-15 16:00:02";
    private long timeStamp = 1508054402000L;
    private Date mDate;

    @Before
    public void setUp() throws Exception{
        System.out.println("测试开始");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void testFinish(){
        System.out.println("测试结束");
    }

    @Test
    public void dateToStamp() {
    }

    @Test
    public void stampToDate() {
        assertNotEquals("预期时间",DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws ParseException {
        assertNotEquals(4,DateUtil.dateToStamp(time));
    }

    @Test(expected =  ParseException.class)
    public void dateToTampTest2() throws ParseException {
        DateUtil.dateToStamp("2020");
    }

    @Test
    public void testAssertThat() throws Exception{
        assertThat("Hello",both(startsWith("hello")).and(endsWith("out")));

    }
}