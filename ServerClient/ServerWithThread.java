package com.example.runproject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerThread implements Runnable{
    Socket socket;
    Thread t;

    ServerThread(Socket socket){
        this.socket = socket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run(){
        try{
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            //Continue Communication as long as connected with Client
            while(true){
                Object cMessage = ois.readObject();
                if(cMessage == null) break;
                System.out.println("Client Message: "+ (String)cMessage);

                String serverMessage = (String) cMessage;
                serverMessage = serverMessage.toUpperCase();

                oos.writeObject(serverMessage);
            }
        }
        catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ServerWithThread {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started...");

        while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected...");
                new ServerThread(socket);
        }
    }
}
