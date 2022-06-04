package com.hc.kotlinstudyexample.mockito;

import com.hc.kotlinstudyexample.test.MySon;
import com.hc.kotlinstudyexample.test.Person;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hcw  on 2020/10/31
 * 类描述：
 * all rights reserved
 */
public class MockitoTest {



    //mockito 使用方式 一，常规方法
//    @Test
//    public void testIsNotNull(){
//        Person mPerson = mock(Person.class); //<--使用mock方法
//        assertNotNull(mPerson);
//    }
//
//


 //mockito，使用方式 二、使用注解
// @Mock
// Person mPerson;
//
//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this); //<--初始化
//    }
//
//    @Test
//    public void testIsNotNull(){
//        assertNotNull(mPerson);
//    }


    //mockito ,使用方式 三，运行器方法
    //@RunWith(MockitoJUnitRunner.class) //<--使用MockitoJUnitRunner
    //public class MockitoJUnitRunnerTest {
    //
    //    @Mock //<--使用@Mock注解
    //    Person mPerson;
    //
    //    @Test
    //    public void testIsNotNull(){
    //        assertNotNull(mPerson);
    //    }
    //
    //}



    //MOCKITO 使用方式 四、MockitoRule 方法

    @Mock
    MySon mPerson;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }


    @Test
    public void testGetMySonName(){
        when(mPerson.getName()).thenReturn("不过如此");

        System.out.println(mPerson.getName());
    }


    @Test
    public void testPersonVerifyAfter(){
        when(mPerson.getAge()).thenReturn(18);
        //延时1s验证
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
        verify(mPerson, after(1000)).getAge();
        System.out.println(System.currentTimeMillis());

        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtLeast(){
        mPerson.getAge();
        mPerson.getAge();
        //至少验证2次
        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtMost(){
        mPerson.getAge();
        //至多验证2次
        verify(mPerson, atMost(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes2(){
        mPerson.getAge();
        mPerson.getAge();
        //验证方法调用2次
        verify(mPerson, times(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes(){
        mPerson.getAge();
        mPerson.getAge();
        //验证方法在100ms超时前调用2次
        verify(mPerson, timeout(100).times(2)).getAge();
    }

//    @Test
//    public void testPersonAny(){
//        when(mPerson.eat(any(String.class))).thenReturn("米饭");
//        //或
//        //when(mPerson.eat(anyString())).thenReturn("米饭");
//
//        //输出米饭
//        System.out.println(mPerson.eat("面条"));
//
//    }


}
