package battleship;

import java.util.Scanner;

public class Board {


        final String[][] field = new String[11][11];
        private Ship[] ships;
        int x1;
        private int y1;
        private int x2;
        private int y2;

        public Board() {

            createField();
            showField();
        }
        public void run() {
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            while (i < ships.length) {
                System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n",
                        ships[i].getName(), ships[i].getNumberCells());
                do {
                    String firstCoord = scanner.next();
                    String secondCoord = scanner.next();
                    createCoordinates(firstCoord,secondCoord);
                    if (check(ships[i].getNumberCells(),ships[i].getName())){
                        createShip();
                    } else {
                        continue;
                    }
                    break;
                } while (true);
                showField();
                i++;
            }

        }
        public void createField() {

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
            Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
            Ship battleship = new Ship("Battleship", 4);
            Ship submarine = new Ship("Submarine", 3);
            Ship cruiser = new Ship("Cruiser", 3);
            Ship destroyer = new Ship("Destroyer", 2);
            ships = new Ship[]{aircraftCarrier, battleship, submarine, cruiser, destroyer};

        }
        public void createCoordinates(String first, String second) {

            char[] firstChar = first.toCharArray();
            char[] secondChar = second.toCharArray();

            int y1 = firstChar[0] - 64;
            int x1 = Integer.parseInt(String.valueOf(firstChar[1]));

            int y2 = secondChar[0] - 64;
            int x2 = Integer.parseInt(String.valueOf(secondChar[1]));

            if (secondChar.length > 2) {
                x2 = 10;
            }
            if (firstChar.length > 2) {
                x1 = 10;
            }
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public void showField() {
            for (String[] strings : field) {
                for (int j = 0; j < strings.length; j++) {
                    System.out.print(strings[j] + " ");
                }
                System.out.print("\n");
            }
        }

        public void createShip() {

            if (x1 - x2 == 0) {

                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                    field[i][x1] = "O";
                }
            } else if (y1 - y2 == 0) {
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    field[y1][i] = "O";
                }
            }

        }

        public boolean check(int cells,String name) {

            if (x1 - x2 != 0 && y1 - y2 != 0) {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            }
            if (x1 - x2 == 0) {
                if (Math.abs(y1 - y2) + 1 != cells) {
                    System.out.println("Error! Wrong length of the " + name + "! Try again:");
                    return false;
                }
                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                    if (x1 != 10) {
                        if (field[i][x1 + 1].equals("O") || field[i][x1 - 1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");

                            return false;
                        }
                    } else {
                        if (field[i][x1 - 1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
                int maxY = Math.max(y1, y2);
                int minY = Math.min(y1, y2);
                if (maxY != 10) {
                    if (field[x1][maxY + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
                if (field[x1][minY - 1].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }

            } else {
                if (Math.abs(x1 - x2) + 1 != cells) {
                    System.out.println("Error! Wrong length of the " + name + "! Try again:");
                    return false;
                }
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    if (y1 != 10) {
                        if (field[y1 + 1][i].equals("O") || field[y1 - 1][i].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    } else {
                        if (field[y1 - 1][i].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
                int maxX = Math.max(x1, x2);
                int minX = Math.min(x1, x2);
                if (maxX != 10) {
                    if (field[y1][maxX + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }

                if (field[y1][minX - 1].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }

            return true;
        }
    }

