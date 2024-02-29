package TicTacToe;

import TicTacToe.Controller.GameController;
import TicTacToe.model.*;
import TicTacToe.service.winningstrategy.WinningStrategyName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        List<Player> players = new ArrayList<>();
        int id = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe Game");
        System.out.println("Please enter the dimension for the board");
        int dimension = sc.nextInt();
        System.out.println("Do you want a bot in the game ? Y or N");
        String botAns = sc.next();
        if(botAns.equalsIgnoreCase("Y")){
            Player bot = new Bot(id++, '$', BotDifficultyLevel.EASY);
            players.add(bot);
        }
        while(id < dimension){
            System.out.println("Please enter the player name:");
            String playerName = sc.next();
            System.out.println("Please enter the player symbol:");
            char symbol = sc.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, symbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }
        Collections.shuffle(players); //randomise the player list
        Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDERONEWINNINGSTRATEGY );
        int playerIndex = -1;
        game.getBoardStates().add(game.getCurrentBoard().clone()); // saving blank board state
        while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            Player winner = gameController.checkWinner(game, movePlayed);
            Board currentBoardState = game.getCurrentBoard().clone();
            game.getBoardStates().add(currentBoardState);
            game.getMoves().add(movePlayed);
            if(movePlayed.getPlayer().getPlayerType().equals(PlayerType.HUMAN)) {
                System.out.println("Would you like to undo your previous move ? Y/N ");
                String playerAns = sc.next();
                if (playerAns.equalsIgnoreCase("Y")) {
                    gameController.undoMove(game, movePlayed);
                }
            }
            if(winner != null){
                System.out.println("WINNER IS : " + winner.getName());
                break;
            }
        }
        System.out.println("Final Board Status");
        gameController.displayBoard(game);
        System.out.println("Do you want to replay? ");
        String replayAns = sc.next();
        if(replayAns.equalsIgnoreCase("Y"))
        {
            gameController.replayGame(game);
        }

    }

}
