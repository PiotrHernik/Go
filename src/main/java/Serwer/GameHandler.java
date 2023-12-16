package Serwer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class GameHandler extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private Board board;

    public GameHandler(Socket socket, Board board) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintStream(socket.getOutputStream());
        this.board = board;
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

            // Wysyłanie planszy do klienta
            sendBoard();
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

    private void sendBoard() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                // Ustaw białe pole z czarną obramówką jako wartość domyślną
                char cell = 'W';



                // Obramowanie będzie zawsze czarne
                boardString.append(cell);
            }
        }
        output.println(boardString.toString());
        output.flush();
    }
}
