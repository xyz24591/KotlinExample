package com.hc.kotlinstudyexample.Basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import kotlinx.android.synthetic.main.activity_basic_main.*

/**
 * Created by hcw  on 2020/7/21
 * 类描述：
 * all rights reserved
 */

// 1、https://github.com/MindorksOpenSource/from-java-to-kotlin/blob/master/README-ZH.md
// 2、https://www.jianshu.com/p/f46eac4550f5
// 3、https://www.jianshu.com/p/bb53cba6c8f4

class BasicMainActivity :AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_main)

        basicGrammar()


    }

    /**
     * 基础语法
     */
    private fun basicGrammar() {
        //打印日志
        println("这里打印人日志，相当于 System.out.println")

        //常量与变量
        var name = "Variable"
        val name2 = "Value"

        //null 值处理
        textView?.let {
            val length = textView.length()
        }
        //或者
        val length = textView?.length()

        //字符串拼接(字符串模板)
        val firstName = "First"
        val lastName = "Last"
        val my_name = "My name is : $firstName $lastName"


        //字符串换行
        val textStr ="""
            |First Line
            |Second Line
            |Third Line
        """.trimMargin()

        textView.setText(textStr)


        //三元表达式
        val text  = if(textView.length() > 5)
            "textView 长度 > 5"
            else "textView 长度小于 5"


        //位运算符
        //final int andResult  = a & b;
        //final int orResult   = a | b;
        //final int xorResult  = a ^ b;
        //final int rightShift = a >> 2;
        //final int leftShift  = a << 2;
        //final int unsignedRightShift = a >>> 2;

        val a = 1;
        val b = 2;
        val andResult = a and b
        val orResult = a or b
        val oxorResult = a xor b
        val rightShift = a shr 2
        val leftShift = a shl 2
        val unsignedRightShift = a shr 2


        //类型判断和转换
        var theObject  = BasicMainActivity@this
        if (theObject is AppCompatActivity){
            //声明式
            var appAct = theObject as AppCompatActivity
            //隐式
           // var act = (AppCompatActivity)theObject
        }



        //区间条件
        var score = 20
        if (score in 0..100)
            println("yes")


        //when 代替 java 中的 switch
        var grade = when (score){
            in 90..100 -> "Excellent"
            in 60..80 -> "good"
            in 0..60 -> "bad"
            else -> "Fail"
        }


        //for 循环
        //for (int i = 1; i <= 10; i++){}
        for( i in 1..10){}

        //for(int i = 1; i <10; i++)
        for(i in 1 until  10 ){}

        //for(int i = 10; i >= 0; i--){}
        for(i in 10 downTo 0){}

        //for(int i = 1; i <= 10; i+=2)
        for(i in 10 downTo 0 step  2){}

        //for(int i = 10; i>=0 ; i-= 2)
        for(i in 10 downTo 0 step 2){}

        //for(String item : collection){}
        //for(item in collection){}

        //for(Map.Entry<String,String> entry : map.entrySet()){}
        //for((key,value) in map)



        //构造集合
        val listOfNumber = listOf(1,2,3,4)
        val keyValue = mapOf(1 to "first",
                             2 to "second",
                             3 to  "third"
            )

        listOfNumber.forEach{
            println(it)
        }

        listOfNumber.filter { it > 5 }.forEach{println(it)}

    }

    //发方法定义
    fun doSomething(vararg numbers:Int){
        println(numbers)
    }

    //带返回值
    fun getScore():Int{
        return 10
    }


    //java bean   data class Developer(val name: String, val age: Int)




}