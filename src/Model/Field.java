package Model;

/**
 * Created by Sony on 30.06.2015.
 */
public class Field {
    public final int FIELDWIDTH = 10;
    public final int FIELDHEIGHT = 10;
    public char[][] cells = new char[FIELDWIDTH][FIELDHEIGHT];
    public char[][] boxesWithoutShips = new char [FIELDWIDTH][FIELDHEIGHT];
    static char[] letters = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    static int rowNumber;

    public void initField(){
        for (int k = 0; k < letters.length; k++) {
            System.out.print(letters[k] + "\t");
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++) {
            rowNumber = i + 1;
            System.out.print(rowNumber + "\t");
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = '\u25a2';
                System.out.print(cells[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void initBoxesWithoutShips(){
        for (int k = 0; k < letters.length; k++) {
            System.out.print(letters[k] + "\t");
        }
        System.out.println();
        for (int i = 0; i < boxesWithoutShips.length; i++) {
            rowNumber = i + 1;
            System.out.print(rowNumber + "\t");
            for (int j = 0; j < boxesWithoutShips.length; j++) {
                cells[i][j] = '\u25a2';
                System.out.print(cells[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setShip(){
         //алгоритм установки кораблей
    }

    public void doShoot(){
        // алгоритм стрельбы игрока
    }

    public void showField(){
        // алгоритм вывода в консоль текущего положения поля
    }

    public void showFieldWithoutShips(){
        // алгоритм вывода в консоль текущего положения поля с отображением выстрелов и найденных кораблей
    }
}
