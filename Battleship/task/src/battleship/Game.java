package battleship;

import java.util.Scanner;

public class Game {

    private int x;
    private int y;
    Board board;

    public Game(Board board){
        this.board = board;
    }
    public void runGame(){
        System.out.println("The game starts!");
        board.showField();

        while(true){
            System.out.println("Take a shot!");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            createCoordinates(input);
            if(checkCoordinate()){
                    createShot();
                    board.showField();

            }else {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");

                }
        }

    }

    public void createShot(){
        if(board.field[this.x][this.y].equals("O")){
            System.out.println("You hit a ship!");
            board.field[this.x][this.y] = "X";}
        else if(board.field[this.x ][this.y].equals("~")){
            System.out.println("You missed!");
            board.field[this.x][this.y] = "M";

            }

    }


    public void createCoordinates(String input) {

        char[] inputCharArray = input.toCharArray();
        this.x = inputCharArray[0] - 64;

        if (inputCharArray.length > 2) {
            this.y = Integer.parseInt(String.valueOf(inputCharArray[1] + inputCharArray[2]));
        }else {
            this.y = Integer.parseInt(String.valueOf(inputCharArray[1]));
        }

    }

    public boolean checkCoordinate(){
        return this.x <= 10 && this.y <= 10;
    }

}
