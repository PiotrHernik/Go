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
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MultiThread extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public MultiThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            PrintStream output = new PrintStream(socket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.println("Wiadomosc od serwera");
            output.flush();

            Object line;
            while(true){
                //odebranie wiadomości od klienta
                line = input.readLine();
                System.out.println(line);
                //output.println("Chyba coś dostałem");
                if(line.equals("Exit")) break;

            }

            socket.close();
        }
        catch(IOException e){
            System.out.println("Server exception: " + e.getMessage());
        }
    }

}
