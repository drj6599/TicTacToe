package TicTacToe.model;

import TicTacToe.exception.InvalidCellError;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Move makeMove(Board board)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("It's " + this.name +"'s turn");
        System.out.println("Enter the row for the target Symbol");
        int row = sc.nextInt();
        System.out.println("Enter the col for the target Symbol");
        int col = sc.nextInt();
        if(row < 0 || col < 0 || row > board.getDimension() || col > board.getDimension())
        {
            throw new InvalidCellError("Chosen cell doesn't exist on board");
        }
        Cell playedMoveCell = board.getMatrix().get(row).get(col);
        playedMoveCell.setCellState(CellState.FILLED);
        playedMoveCell.setPlayer(this);

        return new Move(playedMoveCell,this);
    }
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
