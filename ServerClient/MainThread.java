package com.example.samplethredings;

public class MainThread {
    public static void main(String[] args) throws  Exception{
        Thread t = Thread.currentThread();
        System.out.println("Current thread: "+t);

        //Change thread name...
        t.setName("MyCurrentThread");
        System.out.println("Current Thread: "+t.getName());

        for(int i=0; i<10; i++){
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
}
