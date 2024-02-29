package TicTacToe.service.BotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy()
    {
        return new RandomBotPlayingStrategy();
    }
}
