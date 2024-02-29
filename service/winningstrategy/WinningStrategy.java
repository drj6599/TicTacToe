package TicTacToe.service.winningstrategy;

import TicTacToe.model.Board;
import TicTacToe.model.Move;
import TicTacToe.model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board , Move lastMove);
}
