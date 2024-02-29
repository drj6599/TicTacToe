package TicTacToe.service.BotPlayingStrategy;

import TicTacToe.model.Board;
import TicTacToe.model.Move;
import TicTacToe.model.Player;

public interface BotPlayingStrategy {
    Move makeMove(Player player, Board board);
}
