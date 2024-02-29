package TicTacToe.Controller;

import TicTacToe.model.*;
import TicTacToe.service.winningstrategy.WinningStrategy;
import TicTacToe.service.winningstrategy.WinningStrategyFactory;
import TicTacToe.service.winningstrategy.WinningStrategyName;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, WinningStrategyName name)
    {
        return Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .setCurrentBoard(new Board(dimension))
                .setWinningStrategy(WinningStrategyFactory.getWinningStrategy(name,dimension))
                .build();
    }

    public void displayBoard(Game game){
        game.getCurrentBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Move executeMove(Game game, Player player){
        return player.makeMove(game.getCurrentBoard());
    }

    public Player checkWinner(Game game, Move lastMovePlayed){

        return game.getWinningStrategy().checkWinner(game.getCurrentBoard(),lastMovePlayed);
    }

    public Board undoMove(Game game, Move lastPlayedMove){
        lastPlayedMove.getCell().setPlayer(null);                // made player for last played cell null
        lastPlayedMove.getCell().setCellState(CellState.EMPTY);  // made last played cell status EMPTY
        game.getBoardStates().removeLast();  // removed last board states from board states list
        game.getMoves().removeLast();      // removed last move from moves List
        game.setCurrentBoard(game.getBoardStates().getLast());

        return game.getCurrentBoard();
    }

    public void replayGame(Game game){
        List<Move> movesList = game.getMoves();
        List<Board> boardStates = game.getBoardStates();
        for (int i = 0; i < boardStates.size(); i++) {

            boardStates.get(i).displayBoard();
            if(i < movesList.size()) {
                System.out.println(movesList.get(i).getPlayer().getName() + " played to cell with row : " + movesList.get(i).getCell().getRow() + " and col : " + movesList.get(i).getCell().getCol());
            }
        }
    }
}
