package Serwer;

import com.google.gson.Gson;

public class Board {
    private GameRules rules;
    private BoardType[][] board;

    public Board(GameRules rules) {
        this.rules = rules;
        int boardSize = 10;
        this.board = new BoardType[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = BoardType.EMPTY;
            }
        }
    }

    public GameRules getRules() {
        return rules;
    }

    public void setRules(GameRules rules) {
        this.rules = rules;
    }

    public BoardType[][] getBoard() {
        return board;
    }

    public void setBoard(BoardType[][] board) {
        this.board = board;
    }

    // Metoda do przekształcania planszy na format JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Metoda do aktualizacji planszy z formatu JSON
    public void updateFromJson(String json) {
        Gson gson = new Gson();
        Board updatedBoard = gson.fromJson(json, Board.class);
        this.rules = updatedBoard.rules;
        this.board = updatedBoard.board;
    }
}
