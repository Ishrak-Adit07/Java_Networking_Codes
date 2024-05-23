package com.example.runproject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWithThread {
    public static void main(String[] args) throws Exception{
        System.out.println("Client Started...");
        Socket socket = new Socket("192.168.31.203", 22222);
        System.out.println("Client Connected...");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while(true)
        {
            Scanner str = new Scanner(System.in);
            String msg = str.nextLine();

            if(msg.equalsIgnoreCase("exit")) break;

            //Send to Server...
            oos.writeObject(msg);

            Object msgFromServer = ois.readObject();
            System.out.println((String)msgFromServer);
        }
        socket.close();
    }
}
