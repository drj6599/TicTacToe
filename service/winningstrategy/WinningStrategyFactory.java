package TicTacToe.service.winningstrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategyName name , int dimension)
    {
        switch(name)
        {
            case ORDERONEWINNINGSTRATEGY -> { return new OrderOneWinningStrategy(dimension); }
            default -> { return null; }
        }
    }
}
