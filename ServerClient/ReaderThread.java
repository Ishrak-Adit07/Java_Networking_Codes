package com.example.runproject;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReaderThread implements Runnable{
    ObjectInputStream ois;
    String name;
    Thread t;

    ReaderThread(String name, ObjectInputStream ois){
        this.ois = ois;
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run(){
        while(true){
            try{
                Object msgFromServer = ois.readObject();
                if(msgFromServer==null) break;
                System.out.println(name+ " Received " + (String)msgFromServer);
            }catch (ClassNotFoundException | IOException e){
                e.printStackTrace();
            }
        }
    }
}
