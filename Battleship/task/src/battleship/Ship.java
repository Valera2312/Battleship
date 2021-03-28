package battleship;

public class Ship {

    private String name;
    private int numberCells;

    public Ship(String name, int numberCells) {
        this.name = name;
        this.numberCells = numberCells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCells() {
        return numberCells;
    }

    public void setNumberCells(int numberCells) {
        this.numberCells = numberCells;
    }
}
