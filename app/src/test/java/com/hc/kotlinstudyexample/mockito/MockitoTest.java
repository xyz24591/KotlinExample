package com.hc.kotlinstudyexample.mockito;

import com.hc.kotlinstudyexample.test.Person;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

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
    Person mPerson;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }




}
