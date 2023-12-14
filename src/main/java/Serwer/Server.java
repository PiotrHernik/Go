package Serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while(true) {
                String bye = scanner.nextLine();
                if(bye.equals("bye")){
                    System.exit(0);
                }
            }
        }).start();

        try (ServerSocket serverSocket = new ServerSocket(3357)) {
            System.out.println("Server is listening on port 3357");

            int clientCount = 1;
            while (clientCount < 3) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                clientCount++;
                new GameHandler(socket).start();


            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
