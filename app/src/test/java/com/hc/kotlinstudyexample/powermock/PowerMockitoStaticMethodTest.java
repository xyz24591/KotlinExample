package com.hc.kotlinstudyexample.powermock;

import com.hc.kotlinstudyexample.test.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by hcw  on 2020/11/7
 * 类描述：
 * all rights reserved
 */
@RunWith(PowerMockRunner.class)
public class PowerMockitoStaticMethodTest {

    @Test
    @PrepareForTest({Banana.class})
    public void testStaticMethod() {
        PowerMockito.mockStatic(Banana.class); //<-- mock静态类
        Mockito.when(Banana.getColor()).thenReturn("绿色");
        Assert.assertEquals("绿色", Banana.getColor());
    }
}