package com.voidm.demo;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author voidm
 * @date 2020/12/7
 */

// <dependency>
    // <groupId>net.java.dev.jna</groupId>
    // <artifactId>jna-platform</artifactId>
    // <version>5.2.0</version>
// </dependency>
public class DllCalledMyDLLDemo {
    /*
   MyDll-Project.dll

    int echoAge()
    {
        MessageBox(NULL, TEXT("Hello,world!"), TEXT(""), MB_OK);
        return 12;
    }
   */
    public interface Dll extends Library {

        // 调用自定义 DLL
        Dll instance = (Dll) Native.load("MyDll-Project", Dll.class);
        int echoAge(int age );

    }
    public static void main(String[] args) {
        System.out.println(Dll.instance.echoAge(122));
    }
}