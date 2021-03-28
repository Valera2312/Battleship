package battleship;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Write your code here

        String[][] field = Main.createField();
        showField(field);
        //----------------------------------------------------------
        int  i = 0;
        int attempts = 0;
        String[] ships = {"Aircraft Carrier","Battleship","Submarine","Cruiser","Destroyer"};
        while (i != 5) {

            if(attempts == 0){
                int n = 5;
                if(ships[i].equals("Cruiser")){
                    n = 6;
                }
                System.out.println("Enter the coordinates of the "+ ships[i] + " ("+ Math.abs(i-n) +" cells):");
            }
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            String[] input_coordinates = input.split(" ");
            int[] shipCoordinates = Main.createCoordinates(input_coordinates[0], input_coordinates[1]);

            if (check(shipCoordinates, ships[i], field)) {
                createShip(shipCoordinates, field);
                showField(field);
                i++;
                attempts = 0;
            }else {
                attempts++;
            }
        }
    }
    public static String[][] createField() {
        String[][] field = new String[11][11];
        char rowNumber = 64;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0) {              //если первая строка
                    if (j == 0) {          //если первый столбел первой строки
                        field[i][j] = " ";
                    } else {
                        field[i][j] = String.valueOf(j);
                    }
                } else {                  //если другие строки
                    if (j == 0) {   //если первый столбел
                        field[i][j] = String.valueOf(rowNumber);
                    } else {
                        field[i][j] = "~";
                    }
                }
            }
            rowNumber++;
        }
        return field;
    }
    public static int[] createCoordinates(String first,String second){

        char[] firstChar = first.toCharArray();
        char[] secondChar = second.toCharArray();

        int y1 = firstChar[0] - 64;
        int x1 = Integer.parseInt(String.valueOf(firstChar[1]));

        int y2 = secondChar[0] - 64;
        int x2 = Integer.parseInt(String.valueOf(secondChar[1]));

        if(secondChar.length > 2){
            x2 = 10;
        }
        if(firstChar.length > 2){
            x1 = 10;
        }
        return new int[]{x1, y1, x2, y2};
    }

    public static void showField(String[][] field) {
        for (String[] strings : field) {
            for (int j = 0; j < strings.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static String[][] createShip(int[] shipCoordinates, String[][] field){
        int x1 = shipCoordinates[0];
        int y1 = shipCoordinates[1];
        int x2 = shipCoordinates[2];
        int y2 = shipCoordinates[3];
        System.out.println(x2 + " " + x1);
        System.out.println(y2 + " " + y1);
        if(x1 - x2 == 0){

            for(int i = Math.min(y1,y2); i <= Math.max(y1,y2); i++){
                field[i][x1] = "O";
            }
        }else if(y1 - y2 == 0){
            for(int i = Math.min(x1,x2); i <= Math.max(x1,x2); i++){
                field[y1][i] = "O";
            }
        }
        return field;
    }
    public static boolean check(int[] shipCoordinates,String shipType,String[][]field){

        int x1 = shipCoordinates[0];
        int y1 = shipCoordinates[1];
        int x2 = shipCoordinates[2];
        int y2 = shipCoordinates[3];

        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Aircraft Carrier",5);
        hashMap.put("Battleship",4);
        hashMap.put("Submarine",3);
        hashMap.put("Cruiser",3);
        hashMap.put("Destroyer",2);

        if(x1 - x2 != 0 && y1 - y2 != 0 ){
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        if(x1 - x2 == 0){
            if(Math.abs(y1 - y2) + 1 != hashMap.get(shipType)){
                System.out.println("Error! Wrong length of the " + shipType +  "! Try again:");
                return false;
            }
            for(int i = Math.min(y1,y2); i <= Math.max(y1,y2); i++) {
                if(x1 != 10) {
                    if (field[i][x1 + 1].equals("O") || field[i][x1 - 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:!!!!");

                        return false;
                    }
                }else {
                    if (field[i][x1 - 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }

            }
            int maxY = Math.max(y1,y2);
            int minY = Math.min(y1,y2);
            if(maxY != 10){
                if(field[x1][maxY+1].equals("O")){
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
            if(field[x1][minY-1].equals("O")){
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }

        }
        else {
            if(Math.abs(x1 - x2) + 1 != hashMap.get(shipType)){
                System.out.println("Error! Wrong length of the " + shipType + "! Try again:");
                return false;
            }
            for(int i = Math.min(x1,x2); i <= Math.max(x1,x2); i++) {
                if(y1 != 10){
                    if(field[y1+1][i].equals("O")||field[y1-1][i].equals("O")){
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }else {
                    if(field[y1-1][i].equals("O")){
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
            }
            int maxX = Math.max(x1,x2);
            int minX = Math.min(x1,x2);
            if(maxX != 10){
                if(field[y1][maxX+1].equals("O")){
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }

            if(field[y1][minX-1].equals("O")){
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            }
        }

        return true;

    }

}








