package Serwer;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class MultiThread extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public MultiThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            InputStream input = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(input));

            //Wysylanie do socketa
            OutputStream output = socket.getOutputStream();
            out = new PrintWriter(output, true);

            String line;
            line = in.readLine();
            System.out.println(line);
            out.println("Wiadomosc od serwera");
            while(!line.equals("bye")){

            }
            socket.close();
        }
        catch(IOException e){
            System.out.println("Server exception: " + e.getMessage());
        }
    }

}
