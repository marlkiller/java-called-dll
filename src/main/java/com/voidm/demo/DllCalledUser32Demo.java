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
public class DllCalledUser32Demo {
    /*
   user32.dll
   MessageBoxA
   参数名	类型	传址	数组	备注
   hWnd	整数型			窗口句柄
   lpText	文本型			提示内容
   lpCaption	文本型			提示标题
   uType	整数型			提示图标和按钮类型
   */
    public interface Dll extends Library {
        // 调用系统DLL
        int MessageBoxA(int a, String b, String c, int d);
        Dll instance = (Dll) Native.load("user32", Dll.class);
    }
    public static void main(String[] args) {
        System.out.println(Dll.instance.MessageBoxA(0,"context","title", (int) 0x00000000L));

    }

}