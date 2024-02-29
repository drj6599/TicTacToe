package TicTacToe.model;

import java.util.ArrayList;
import java.util.List;

public class Board implements Prototype<Board> {

    private int dimension;
    private List<List<Cell>> matrix;

    public Board(int dimension) {
        this.dimension = dimension;
        matrix = new ArrayList<>();
        for(int i=0;i<dimension;i++){
            matrix.add(new ArrayList<>());
            for(int j=0;j<dimension;j++){
                matrix.get(i).add(j,new Cell(i,j));
            }
        }
    }

    public Board(Board board)
    {
        this.dimension = board.dimension;
        List<List<Cell>> newMatrix = new ArrayList<>();
        for(int i=0;i<board.dimension;i++){
            newMatrix.add(new ArrayList<>());
            for(int j=0;j< board.dimension;j++){
                Cell newCell = board.matrix.get(i).get(j).clone();
                newMatrix.get(i).add(j,newCell);
            }
        }
        this.matrix = newMatrix;
    }

    public void displayBoard(){
        for (int i = 0; i < dimension; i++) {
            List<Cell> cells = matrix.get(i);
            for (Cell cell: cells) {
                cell.displayCell();
            }
            System.out.println();
        }
    }
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Cell>> matrix) {
        this.matrix = matrix;
    }

    @Override
    public Board clone() {
        return new Board(this);
    }
}
