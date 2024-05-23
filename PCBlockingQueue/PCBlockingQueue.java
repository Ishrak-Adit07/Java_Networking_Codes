package com.example.samplethredings;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable{
    Thread t;
    BlockingQueue<String> q;
    String name;

    Producer(BlockingQueue<String> q, String name){
        this.q = q;
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run(){
        int i=1;
        while (true){
            try{
                if(q.size()>=4){
                    System.out.println(name+": Queue is full...");
                    Thread.sleep(1000);
                }
                q.put("Cake"+i);
                System.out.println(name + " created cake- "+ i++);
                Thread.sleep(200);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    Thread t;
    String name;
    BlockingQueue<String> q;

    Consumer(BlockingQueue<String> q, String name){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.q = q;
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run(){
        try{
            if(q.size()==0){
                System.out.println(name+": Quere is empty...");
                Thread.sleep(2000);
            }
            while (true){
                System.out.println(name+ " consumed-"+ q.take());
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class PCBlockingQueue {
    public static void main(String [] args) throws Exception{
        BlockingQueue<String> q = new ArrayBlockingQueue<>(4);
        Producer producer1 = new Producer(q, "Monica");
        Consumer consumer1 = new Consumer(q, "Joey");
        Consumer consumer2 = new Consumer(q, "Ross");

        producer1.t.join();
        consumer1.t.join();
        consumer2.t.join();
    }
}
