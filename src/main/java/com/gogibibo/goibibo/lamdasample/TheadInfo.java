package com.gogibibo.goibibo.lamdasample;

public class TheadInfo {

    public static void main(String[] args) {



        Runnable runnable=() -> {
                for(int i=0;i<10;i++)
                {
                    System.out.println(i);
                }
            };

        Thread t1=new Thread(runnable);
        t1.start();



    }
}
