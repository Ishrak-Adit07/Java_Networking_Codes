package com.example.samplethredings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started...");

        while (true){
            try{
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected...");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Object cMessage = ois.readObject();
                System.out.println("Client Message: "+ (String)cMessage);

                String serverMessage = (String) cMessage;
                serverMessage = serverMessage.toUpperCase();

                oos.writeObject(serverMessage);
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
