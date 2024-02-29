package TicTacToe.service.winningstrategy;

import TicTacToe.model.Board;
import TicTacToe.model.Cell;
import TicTacToe.model.Move;
import TicTacToe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private List<HashMap<Character,Integer>> rowHashMapList;
    private List<HashMap<Character,Integer>> colHashMapList;
    private HashMap<Character,Integer> leftDiagonal;
    private HashMap<Character,Integer> rightDiagonal;
    private HashMap<Character,Integer> cornerHashMap;

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        rowHashMapList = new ArrayList<>();
        colHashMapList = new ArrayList<>();
        rightDiagonal = new HashMap<>();
        leftDiagonal = new HashMap<>();
        cornerHashMap = new HashMap<>();
        for(int i=0;i<dimension;i++){
            rowHashMapList.add(new HashMap<>());
            colHashMapList.add(new HashMap<>());
        }
    }



    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        boolean isWinner = checkWinnerForCorner(board.getMatrix(),symbol)
                || checkAndUpdateForRowHashMap(row,symbol)
                || checkAndUpdateForColHashMap(col,symbol)
                || checkAndUpdateForLeftDiagonal(symbol,row,col)
                || checkAndUpdateRightDiagonal(symbol,row,col);

        if(isWinner)
        {
            return player;
        }
        return null;
    }

    private boolean checkAndUpdateRightDiagonal(char symbol, int row, int col) {
        if(!checkRightDiagonal(row,col))
        {
            return false;
        }
        rightDiagonal.put(symbol,rightDiagonal.getOrDefault(symbol,0)+1);
        return rightDiagonal.get(symbol) == dimension;
    }

    private boolean checkRightDiagonal(int row, int col) {
        return (row+col) == dimension-1;
    }

    private boolean checkAndUpdateForLeftDiagonal(char symbol, int row, int col) {
        if(!checkLeftDiagonal(row,col))
        {
            return false;
        }
        leftDiagonal.put(symbol,leftDiagonal.getOrDefault(symbol,0)+1);
        return leftDiagonal.get(symbol) == dimension;
    }

    private boolean checkLeftDiagonal(int row, int col) {
        return row==col;
    }

    private boolean checkAndUpdateForColHashMap(int col, char symbol) {
        HashMap<Character,Integer> colHashMap = colHashMapList.get(col);
        colHashMap.put(symbol,colHashMap.getOrDefault(symbol,0)+1);
        return colHashMap.get(symbol) == dimension;
    }

    private boolean checkAndUpdateForRowHashMap(int row, char symbol) {

        HashMap<Character,Integer> rowHashMap = rowHashMapList.get(row);
        rowHashMap.put(symbol,rowHashMap.getOrDefault(symbol,0)+1);
        return rowHashMap.get(symbol) == dimension;
    }

    private boolean checkWinnerForCorner(List<List<Cell>> matrix, char symbol) {

        if(cornerHashMap.containsKey(symbol))
        {
            cornerHashMap.put(symbol,cornerHashMap.get(symbol)+1);
            return cornerHashMap.get(symbol) == 4;
        }
        else
        {
            cornerHashMap.put(symbol,1);
        }

        return false;
    }
}
