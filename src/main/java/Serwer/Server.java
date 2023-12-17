package Serwer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    Board board;
    //Lista w której będą przechowywane odniesienia do klientów, żeby móc wysłać wiadomość do wszystkich
    ArrayList<PrintStream> arrayPrint;

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        arrayPrint = new ArrayList<>();

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
                    //Dodanie printStremu do danego Clienta do listy
                    PrintStream printStream = new PrintStream(socket.getOutputStream());
                    arrayPrint.add(printStream);
                    //Uruchomienie nowego klienta
                    new MultiThread(socket).start();

                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

