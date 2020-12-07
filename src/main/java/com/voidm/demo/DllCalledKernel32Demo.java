package com.voidm.demo;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef;
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
public class DllCalledKernel32Demo {
    @Structure.FieldOrder({"dwSize", "cntUsage", "th32ProcessID", "th32DefaultHeapID",
            "th32ModuleID", "cntThreads", "th32ParentProcessID", "pcPriClassBase",
            "dwFlags", "szExeFile"})
    public static class LPPROCESSENTRY32 extends Structure {


        // typedef struct tagPROCESSENTRY32 {
        //     DWORD     dwSize;
        //     DWORD     cntUsage;
        //     DWORD     th32ProcessID;
        //     ULONG_PTR th32DefaultHeapID;
        //     DWORD     th32ModuleID;
        //     DWORD     cntThreads;
        //     DWORD     th32ParentProcessID;
        //     LONG      pcPriClassBase;
        //     DWORD     dwFlags;
        //     CHAR      szExeFile[MAX_PATH];
        // } PROCESSENTRY32;

        public int dwSize;
        public int cntUsage;
        public int th32ProcessID;
        public long th32DefaultHeapID;
        public int th32ModuleID;
        public int cntThreads;
        public int th32ParentProcessID;
        public int pcPriClassBase;
        public int dwFlags;
        public char[] szExeFile = new char[WinDef.MAX_PATH];

        public LPPROCESSENTRY32() {
            dwSize = size();
        }

        public LPPROCESSENTRY32(Pointer memory) {
            super(memory);
            read();
        }

        public String getName() {
            return Native.toString(this.szExeFile);
        }
    }

    // https://docs.microsoft.com/zh-cn/windows/desktop/api/tlhelp32/ns-tlhelp32-tagprocessentry32
    public interface Kernel32 extends StdCallLibrary {
        Kernel32 instance = (Kernel32) Native.load("Kernel32", Kernel32.class, W32APIOptions.UNICODE_OPTIONS);

        // HANDLE CreateToolhelp32Snapshot(
        //         DWORD dwFlags,
        //         DWORD th32ProcessID
        // );
        int CreateToolhelp32Snapshot(int dwFlags, int th32ProcessID);

        // BOOL Process32First(
        //         HANDLE           hSnapshot,
        //         LPPROCESSENTRY32 lppe
        // );
        boolean Process32First(int handle, LPPROCESSENTRY32 lppe);
        boolean Process32Next (int handle, LPPROCESSENTRY32 lppe);

    }

    public static void main(String[] args) {

        // Kernel32 instance = Kernel32.instance;
        int handle = Kernel32.instance.CreateToolhelp32Snapshot(2, 0);
        if (handle != 0) {
            LPPROCESSENTRY32 lppe = new LPPROCESSENTRY32();
            boolean falg = Kernel32.instance.Process32First(handle, lppe);
            if (falg) {
                System.out.println(lppe.getName());
                lppe = new LPPROCESSENTRY32();
                while (Kernel32.instance.Process32Next(handle, lppe)) {
                    System.out.println(lppe.getName());
                    lppe = new LPPROCESSENTRY32();
                }
            }
        }
    }

}