package battleship;

import java.util.Scanner;

public class Game {

    private int x;
    private int y;
    Board board;
    Board withFogOfWar;

    public Game(Board board) {
        this.board = board;
        this.withFogOfWar = new Board();
        setWithFogOfWar();


    }

    public void runGame() {
        System.out.println("The game starts!");
        //board.showField();
        withFogOfWar.showField();
        System.out.println("Take a shot!");
        int countSankShip = 0;
        while (true) {

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            createCoordinates(input);

            if (checkCoordinate()) {

                String hitOrMiss = (createShot() ? "You hit a ship! Try again:" : "You missed. Try again:");
                withFogOfWar.showField();

                if (checkSankShip()) {

                    if(countCells()==0){
                       System.out.println("You sank the last ship. You won. Congratulations!");
                       break;
                    }
                    System.out.println("You sank a ship! Specify a new target:");

                } else {
                    System.out.println(hitOrMiss);
                }

            } else {

                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }

        }
    }


    public boolean createShot() {
        if (board.field[this.x][this.y].equals("O")) {
            board.field[this.x][this.y] = "X";
            withFogOfWar.field[this.x][this.y] = "X";

        } else if (board.field[this.x][this.y].equals("~")) {
            board.field[this.x][this.y] = "M";
            withFogOfWar.field[this.x][this.y] = "M";
            return false;
        }

        return true;
    }

    public void setWithFogOfWar() {

        withFogOfWar.createField();

    }

    public void createCoordinates(String input) {

        char[] inputCharArray = input.toCharArray();
        this.x = inputCharArray[0] - 64;

        if (inputCharArray.length > 2) {
            String coodrY = String.valueOf(inputCharArray[1]) + String.valueOf(inputCharArray[2]);

            this.y = Integer.parseInt(coodrY);

        } else {
            this.y = Integer.parseInt(String.valueOf(inputCharArray[1]));
        }

    }

    public boolean checkCoordinate() {
        return this.x <= 12 && this.y <= 12;
    }

    boolean checkSankShip() {

        if(!board.field[x][y].equals("X")){
            return false;
        }

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int col = (x + i + 11) % 11;
                    int row = (y + j + 11) % 11;
                    if (board.field[col][row].equals("O")) {
                        return false;
                    }
                }

            }

        return true;
    }

    public int countCells(){
        int count = 0;
        for (String[] strings : board.field) {
            for (String string : strings) {
                if (string.equals("O")) {
                    count++;
                }
            }

        }
        return count;
    }



}

