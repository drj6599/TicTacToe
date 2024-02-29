package TicTacToe.model;

import TicTacToe.exception.InvalidBotCount;
import TicTacToe.exception.InvalidPlayerCount;
import TicTacToe.exception.InvalidSymbolSetup;
import TicTacToe.service.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private List<Move> moves;
    private List<Board> boardStates;
    private int numberOfSymbols;
    private WinningStrategy winningStrategy;
    private GameStatus gameStatus;

    public Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.winningStrategy = winningStrategy;
        this.currentPlayer = null;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.numberOfSymbols = players.size();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public void setBoardStates(List<Board> boardStates) {
        this.boardStates = boardStates;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static class Builder
    {
        private int dimension;
        private Board currentBoard;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setCurrentBoard(Board currentBoard) {
            this.currentBoard = currentBoard;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        private void validate()
        {
            validateBotCount();
            validateNumberOfPlayers();
            validatePlayerSymbols();
        }

        private void validatePlayerSymbols() {
            HashSet<Character> symbols = new HashSet<>();
            for (Player player : players) {
                symbols.add(player.getSymbol());
            }
            if(symbols.size() != players.size())
            {
                throw new InvalidSymbolSetup("Symbols should be unique for all the players");
            }
        }

        private void validateNumberOfPlayers() {
            if (players.size() < currentBoard.getDimension()-2 || players.size() >= currentBoard.getDimension())
            {
                throw new InvalidPlayerCount("Number of Players are wrong");
            }
        }

        private void validateBotCount() {
            int botCount = 0;
            for (Player player : players) {
                if(player.getPlayerType() == PlayerType.BOT)
                {
                    botCount++;
                }
            }

            if (botCount > 1)
            {
                throw new InvalidBotCount("Only 1 bot is allowed in the Game");
            }
        }

        public Game build()
        {
            validate();
            return new Game(new Board(dimension),players,winningStrategy);
        }
    }

}
