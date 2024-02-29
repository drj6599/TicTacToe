package TicTacToe.model;

import TicTacToe.service.BotPlayingStrategy.BotPlayingStrategy;
import TicTacToe.service.BotPlayingStrategy.RandomBotPlayingStrategy;

public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    public Bot(int id,  char symbol,  BotDifficultyLevel botDifficultyLevel) {
        super(id, "ROBOT", symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayingStrategy = new RandomBotPlayingStrategy();
    }

    @Override
    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(this,board);
    }
}
