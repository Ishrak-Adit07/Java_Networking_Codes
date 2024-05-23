package com.example.samplethredings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        System.out.println("Client Started...");
        Socket socket = new Socket("192.168.31.203", 22222);
        System.out.println("Client Connected...");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner str = new Scanner(System.in);
            String msg = str.nextLine();

            //Send to Server...
            oos.writeObject(msg);

            Object msgFromServer = ois.readObject();
            System.out.println((String)msgFromServer);
        }
}

