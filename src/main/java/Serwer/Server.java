package Serwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    Board board;

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String bye = scanner.nextLine();
                if (bye.equals("bye")) {
                    System.exit(0);
                }
            }
        }).start();

        try (ServerSocket serverSocket = new ServerSocket(3357)) {
            System.out.println("Server is listening on port 3357");


            int clientCount = 1;

            while (true) {
                if(clientCount < 30) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected");
                    clientCount++;
                    new MultiThread(socket).start();

                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


//

//

//
 //TODO te klasy to sa z zakutalizowanego clienta, ale coś mi nie szło z połączeniem się z githubem w tamtym projekcie
//TODO ogólnie przyciski trzeba by dodać pewnie na serwerze, że odczytuje z klienta i na kliencie, że klika
