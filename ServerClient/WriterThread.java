package com.example.runproject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class WriterThread implements Runnable{
    ObjectOutputStream oos;
    String name;
    Thread t;

    WriterThread(String name, ObjectOutputStream oos){
        this.oos = oos;
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run(){
        Scanner str = new Scanner(System.in);

        while (true) {
            try {
                String msg = str.nextLine();
                if(msg.equalsIgnoreCase("exit")) break;

                //Send to Server...
                oos.writeObject(msg);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
