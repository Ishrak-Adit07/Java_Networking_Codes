package com.example.samplethredings;

class NewThread implements Runnable{
    Thread t;
    int ThreadNo;

    NewThread(int ThreadNo){
        t = new Thread(this, "Runnable Thread");
        this.ThreadNo = ThreadNo;
        t.start();
    }

    @Override
    public void run(){
        for(int i=0; i<4; i++){
            System.out.println(ThreadNo+" HEllO " + i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Exiting Child Thread-"+ThreadNo);
    }
}

public class RunnableThread {
    public static void main(String [] args) throws Exception{
        System.out.println("Main Thread Started...");
        NewThread ob1 = new NewThread(1);
        NewThread ob2 = new NewThread(2);

        try{
            ob1.t.join();
            ob2.t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Thread 1 is alive: "+ob1.t.isAlive());
        System.out.println("Thread 2 is alive: "+ob2.t.isAlive());

        System.out.println("Main Thread Exit...");
    }
}
