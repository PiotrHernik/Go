package Serwer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
            while (clientCount < 3) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                clientCount++;
                new GameHandler(socket, board).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


//package Client;
//        import javafx.scene.control.Alert;
//
//        import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.io.PrintStream;
//        import java.net.InetAddress;
//        import java.net.Socket;
//
//public class ClientConnection
//{
//    //aafsdgssd
//    String string;
//    Client client;
//    private class Client
//
//    {
//        private final Socket socket;
//        private BufferedReader input;
//        private PrintStream output;
//        Client(String str) throws IOException
//        {
//            this.socket = new Socket(InetAddress.getLocalHost().getHostName(), 3357);
//            output = new PrintStream(socket.getOutputStream());
//            displayAlert("Connected");
//            output.println(str);
//            output.flush();
//            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            if (!input.readLine().equals("Success"))
//            {
//                throw new IOException("Error");
//            }
//
//
//        }
//        public void closeConnection()
//        {
//            try
//            {
//                input.close();
//                output.close();
//                socket.close();
//            }
//            catch (IOException e) {
//                displayAlert("Error");
//            }
//        }
//    }
//
//    public ClientConnection(String str) throws IOException
//    {
//        this.string = str;
//        client = new Client(str);
//
//    }
//    private void displayAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}

//package Client;
//
//        import javafx.application.Application;
//        import javafx.application.Platform;
//        import javafx.scene.Scene;
//        import javafx.scene.control.Label;
//        import javafx.scene.layout.GridPane;
//        import javafx.scene.paint.Color;
//        import javafx.scene.shape.Rectangle;
//        import javafx.stage.Stage;
//
//        import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.net.InetAddress;
//        import java.net.Socket;
////fdfsfsfsd
//public class ClientHandlerGUI extends Application
//{
//    private static final int BOARD_SIZE = 10;
//
//    private Rectangle[][] boardCells;
//    private Label[][] boardLabel;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        GridPane pane = new GridPane();
//        this.boardCells = new Rectangle[BOARD_SIZE][BOARD_SIZE];
//        this.boardLabel = new Label[BOARD_SIZE][BOARD_SIZE];
//
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                boardCells[i][j] = new Rectangle(30, 30);
//                boardCells[i][j].setStroke(Color.BLACK);
//                boardLabel[i][j] = new Label();  // Inicjalizacja boardLabel
//                pane.add(boardCells[i][j], i, j);
//                pane.add(boardLabel[i][j], i, j);
//            }
//        }
//
//        Scene scene = new Scene(pane, 300, 300);
//        stage.setTitle("Go Game");
//        stage.setScene(scene);
//        stage.show();
//
//        // Inicjalizacja połączenia z serwerem i uruchomienie wątku tła do odbioru planszy
//        new Thread(() -> {
//            try (Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 3357)) {
//                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//                while (true) {
//                    // Odbiór planszy jako ciągu znaków
//                    String boardString = input.readLine();
//
//                    // Aktualizacja GUI zgodnie z otrzymaną planszą
//                    updateBoardInGUI(boardString);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    private void updateBoardInGUI(String boardString) {
//        // Aktualizacja GUI zgodnie z planszą otrzymaną od serwera
//        char[][] boardArray = parseBoardString(boardString);
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                int finalI = i;
//                int finalJ = j;
//                Platform.runLater(() -> {
//                    char cellValue = boardArray[finalI][finalJ];
//                    // Ustaw białe pole z czarną obramówką jako wartość domyślną
//                    String cellStyle = "-fx-background-color: #ffffff; -fx-border-color: black;";
//
//                    // Odwróć kolory: białe pola będą miały czarną ramkę, a czarne pole białą ramkę
//                    boardCells[finalI][finalJ].setStyle(cellStyle);
//                    boardCells[finalI][finalJ].setFill(cellValue == 'B' ? Color.BLACK : Color.WHITE);
//                });
//            }
//        }
//    }
//
//
//    private char[][] parseBoardString(String boardString) {
//        // W tym miejscu należy dostosować parsowanie ciągu znaków do struktury planszy
//        // W przykładzie zakłada się, że plansza ma rozmiar 10x10, ale można dostosować to do rzeczywistej struktury danych
//        char[][] boardArray = new char[BOARD_SIZE][BOARD_SIZE];
//        int index = 0;
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                boardArray[i][j] = boardString.charAt(index++);
//            }
//        }
//        return boardArray;
//    }
//}

//    <?xml version="1.0" encoding="UTF-8"?>
//<project xmlns="http://maven.apache.org/POM/4.0.0"
//        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
//<modelVersion>4.0.0</modelVersion>
//
//<groupId>com.example</groupId>
//<artifactId>Client</artifactId>
//<version>1.0-SNAPSHOT</version>
//<name>Client</name>
//
//<properties>
//<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//<junit.version>5.9.2</junit.version>  </properties>
//
//<dependencies>
//<dependency>
//<groupId>org.openjfx</groupId>
//<artifactId>javafx-controls</artifactId>
//<version>17.0.6</version>
//</dependency>
//<dependency>
//<groupId>org.openjfx</groupId>
//<artifactId>javafx-fxml</artifactId>
//<version>17.0.6</version>
//</dependency>
//
//<dependency>
//<groupId>org.junit.jupiter</groupId>
//<artifactId>junit-jupiter-api</artifactId>
//<version>${junit.version}</version>
//<scope>test</scope>
//</dependency>
//<dependency>
//<groupId>org.junit.jupiter</groupId>
//<artifactId>junit-jupiter-engine</artifactId>
//<version>${junit.version}</version>
//<scope>test</scope>
//</dependency>
//<dependency>
//<groupId>com.google.code.gson</groupId>
//<artifactId>gson</artifactId>
//<version>2.8.8</version>
//</dependency>
//</dependencies>
//
//<build>
//<plugins>
//<plugin>
//<groupId>org.apache.maven.plugins</groupId>
//<artifactId>maven-compiler-plugin</artifactId>
//<version>3.11.0</version>
//<configuration>
//<source>17</source>
//<target>17</target>
//</configuration>
//</plugin>
//<plugin>
//<groupId>org.openjfx</groupId>
//<artifactId>javafx-maven-plugin</artifactId>
//<version>0.0.9</version>
//<executions>
//<execution>
//<!-- Default configuration for running with: mvn clean javafx:run -->
//<id>default-cli</id>
//<configuration>
//<mainClass>Client.ClientHandlerGUI</mainClass>
//<launcher>app</launcher>
//<jlinkZipName>app</jlinkZipName>
//<jlinkImageName>app</jlinkImageName>
//<noManPages>true</noManPages>
//<stripDebug>true</stripDebug>
//<noHeaderFiles>true</noHeaderFiles>
//</configuration>
//</execution>
//</executions>
//</plugin>
//</plugins>
//</build>
//</project>
 //TODO te klasy to sa z zakutalizowanego clienta, ale coś mi nie szło z połączeniem się z githubem w tamtym projekcie
//TODO ogólnie przyciski trzeba by dodać pewnie na serwerze, że odczytuje z klienta i na kliencie, że klika
