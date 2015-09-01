import java.util.Random;

/**
 * Created by Sony on 30.06.2015.
 */
public class Ship {
    //пока еще не продумана логика игры и то, как именно будут вызывать переменные и методы из других классов
    //ставлю все методы public.
    Field newField = new Field();
    Random randomCoordinate = new Random();
    int[] types = new int[]{1,2,3,4};
    int amount;
    int length;
    //установка первой ячейки в произвольную позицию.
    // Пока без проверки на то, чтобы рядом не было других кораблей
    int xFirstCell = randomCoordinate.nextInt(newField.FIELDWIDTH);
    int yFirstCell = randomCoordinate.nextInt(newField.FIELDHEIGHT);
    String[] orientations = new String[]{"horizontal", "vertical"};
    String shipOrientation = orientations[randomCoordinate.nextInt(orientations.length)];
    int[][] coordinates;

    //определяем тип корабля
    public int shipsAmount(int type){
        switch (type){
            case 1: amount = 4;
                break;
            case 2: amount = 3;
                break;
            case 3: amount = 2;
                break;
            case 4: amount = 1;
                break;
            default:
                System.out.println("Error type");
                break;
        }
        return amount;
    }
//Длина корабля равна его типу и пока никак не используется, поэтому метод ниже сейчас создан "на всякий случай".
// Скорее всего будет удален.
    public int shipLength(int type){
        switch (type){
            case 1: length = 1;
                break;
            case 2: length = 2;
                break;
            case 3: length = 3;
                break;
            case 4: length = 4;
                break;
            default:
                System.out.println("Error type");
                break;
        }
        return length;
    }

    //установка корабля в произвольную позицию в зависимости от ориентации.
    // Пока без проверки на то, чтобы рядом не было других кораблей и чтобы корабль не выходил за пределы поля.ра
    //TODO По моей текущей задумке корабли устанавливаются рандомно в любое место на двумерном поле горизонтально или вертикално.
    // TODO я еще не успела придумать, как осуществлять проверку того, чтобы при установке не было других кораблей рядом.
    //TODO и еще не придумала, как отслеживать их количество при установке.
    public int[][] getCoordinates(String shipOrientation, int type) {
        if (shipOrientation == "vertical") {
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    for (int j = 0; j < length; j++) {
                        coordinates[i][j] = xFirstCell;
                    }
                } else if (i != 0) {
                    for (int j = 0; j < length; j++) {
                        coordinates[i][j] = yFirstCell + j;
                    }
                }
            }
        } else if (shipOrientation == "horizontal") {
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    for (int j = 0; j < length; j++) {
                        coordinates[i][j] = xFirstCell + i;
                    }
                } else {
                    if (i != 1) {
                        for (int j = 0; j < length; j++) {
                            coordinates[i][j] = yFirstCell;
                        }
                    }
                }
            }
        }
        return coordinates;
    }
}
