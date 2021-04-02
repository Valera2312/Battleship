package battleship;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Write your code here




        Board player1 = new Board("Player 1");

        player1.run();

        promptEnterKey();

        Board player2 = new Board("Player 2");
        player2.run();


        Game gamePlayer1 = new Game(player1);
        Game gamePlayer2 = new Game(player2);





    while(true){


        promptEnterKey();


        player2.showWithFogOfWarBattleField();
        System.out.println("---------------------");
        player1.showBattleField();
        System.out.println("Player 1, it's your turn:");
        gamePlayer2.runGame();

        promptEnterKey();


        player1.showWithFogOfWarBattleField();
        System.out.println("---------------------");
        player2.showBattleField();
        System.out.println("Player 2, it's your turn:");
        gamePlayer1.runGame();
}



    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










