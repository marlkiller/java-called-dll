package com.voidm.demo;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author voidm
 * @date 2020/12/7
 */

// <dependency>
    // <groupId>net.java.dev.jna</groupId>
    // <artifactId>jna-platform</artifactId>
    // <version>5.2.0</version>
// </dependency>
public class DllCalledMyDLLDemo2 {

    public interface MyDll extends StdCallLibrary {
        MyDll instance = (MyDll) Native.load("MyDll-Project", MyDll.class, W32APIOptions.UNICODE_OPTIONS);
        int echoAge(int age );

    }
    public static void main(String[] args) {
        int age = MyDll.instance.echoAge(21);
        System.out.println(age);
    }
}