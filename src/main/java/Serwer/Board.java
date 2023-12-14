package Serwer;

public class Board
{
    GameRules rules;
    public Board(GameRules rules)
    {
        this.rules = rules;

        int boardSize = 10;
        BoardType[][] board = new BoardType[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                board[i][j] = BoardType.EMPTY;
            }
        }
    }
}
