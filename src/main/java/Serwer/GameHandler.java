package Serwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class GameHandler extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;

    public GameHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {

            // Przykład odczytu wiadomości od klienta
            String message = input.readLine();
            System.out.println("Received message from client: " + message);

            // Przykład odpowiedzi klientowi
            output.println("Hello, client!");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
