package Controller;

import Model.Field;
import Model.Ship;

import java.util.Random;


/**
 * Created by Sony on 30.06.2015.
 */
public class ShipLocation {
    static Field newField = new Field();
    static Ship newShip = new Ship();
    static Random randomCoordinate = new Random();
    public static int horFirstCoordinate;
    public static int verFirstCoordinate;
    static String[] orientations = new String[]{"horizontal", "vertical"};
    static String shipOrientation;
    public static boolean isValidShipLocation;
    public static boolean isVertical;

    //TODO По моей текущей задумке корабли устанавливаются рандомно в любое место на двумерном поле
    // todo горизонтально или вертикално.
    //TODO и еще не придумала, как отслеживать их количество при установке. - см. выше - массивы кораблей
    //todo если корабль подбит, помечать ячейку квадратом с крестом
    //если у корабля горизонтальное расположение, то координата первой ячейки по горизонтали подбирается так,
    // чтобы горизонтальная координата + длина корабля не выходили за пределы поля
     //todo - везде, где вызывается isvertical - не метод вызывать, а передать его значение переменной.
    public static int setFirstHorCoordinate() {
        if (isVertical) {
            do {
                horFirstCoordinate = randomCoordinate.nextInt(newField.FIELDWIDTH);
            } while ((horFirstCoordinate + newShip.length - 1) < newField.FIELDWIDTH);
            return horFirstCoordinate;
        } else {
            horFirstCoordinate = randomCoordinate.nextInt(newField.FIELDWIDTH);
            return horFirstCoordinate;
        }
    }

    // если у корабля вертикальное расположение, то координата первой ячейки по вертикали подбирается так,
    //чтобы вертикальная координата + длина корабля не выходили за пределы поля
    public static int setFirstVerCoordinate() {
        if (isVertical) {
            do {
                verFirstCoordinate = randomCoordinate.nextInt(newField.FIELDHEIGHT);
            } while ((verFirstCoordinate + newShip.length - 1) < newField.FIELDHEIGHT);
            return verFirstCoordinate;
        } else {
            verFirstCoordinate = randomCoordinate.nextInt(newField.FIELDHEIGHT);
            return verFirstCoordinate;
        }
    }

    //метод случайного выбора горизонтального или вертикального расположения
    public static boolean defineOrientation() {
        shipOrientation = orientations[randomCoordinate.nextInt(orientations.length)];
        if (shipOrientation.equals("vertical")) {
            return isValidShipLocation = true;
        } else {
            return isValidShipLocation = false;
        }
    }

    //проверка на то, чтобы рядом с каждой ячейкой корабля не было других кораблей
    public static boolean isNotShipNear(Ship newShip, Field newField) {
        isValidShipLocation = true;
//        if (isVertical()) {
        //проверка на то, чтобы в первой ячейке не был установлен корабль ранее
        if (newField.cells[horFirstCoordinate][verFirstCoordinate] == '\u2bc0') {
            isValidShipLocation = false;
        } else if (horFirstCoordinate == 0 && verFirstCoordinate == 0) {
            // проверка ячеек вокруг ячейки (0;0)
            if (newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0') {
                isValidShipLocation = false;
                // если вокруг ячейки (0;0) нет кораблей и корабль однопалубный, то проверка закончена
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если вокруг ячейки (0;0) нет кораблей и корабль многопалубный, то проверяются ячейки вокруг
                // других палуб корабля с учетом уже проверенных ячеек
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipLocation = false;
                    }
                }
            }
        } else if (horFirstCoordinate == 0 && verFirstCoordinate > 0 &&
                verFirstCoordinate != (newField.FIELDHEIGHT - 1)) {
            // проверка ячеек вокруг ячейки (0;n), где n > 0 и меньше 10
            if (newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0') {
                isValidShipLocation = false;
                // если вокруг первой ячейки нет кораблей и корабль однопалубный, то проверка окончена
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                // ячейки (0;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                // то, является ли наш корабль двухпалубным
            } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                if (newShip.length == 2) {
                    return isValidShipLocation = true;
                } else {
                    for (int m = 1; m < newShip.length - 1; m++) {
                        if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                            isValidShipLocation = false;
                        }
                    }
                }
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней ячейки не
                //(0;9), то проверяются ячейки вокруг других палуб корабля общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipLocation = false;
                    }
                }
            }
        } else if (horFirstCoordinate == 0 && verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (0;9)
            if (newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipLocation = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если корабль многопалубный, то таким образом установить корабль нельзя
            } else {
                isValidShipLocation = false;
            }
        } else if (horFirstCoordinate > 0 && horFirstCoordinate < (newField.FIELDWIDTH - 1) &&
                verFirstCoordinate == 0) {
            // проверяются ячейки вокруг ячейки (n;0), где n > 0 и меньше 9
            if (newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipLocation = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если корабль многопалубный, то проверяются ячейки вокруг других палуб общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipLocation = false;
                    }
                }
            }
        } else if (horFirstCoordinate == (newField.FIELDWIDTH - 1) && verFirstCoordinate == 0) {
            // проверяются ячейки вокруг ячейки (9;0)
            if (newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipLocation = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если корабль многопалубный, то проверяются ячейки вокруг других палуб общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipLocation = false;
                    }
                }
            }
        } else if (horFirstCoordinate == (newField.FIELDWIDTH - 1) && verFirstCoordinate > 0
                && verFirstCoordinate < (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (9;n), где n > 0 и меньше 9
            if (newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0') {
                isValidShipLocation = false;
                // если вокруг первой ячейки нет кораблей и корабль однопалубный, то проверка окончена
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                // ячейки (9;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                // то, является ли наш корабль двухпалубным
            } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                if (newShip.length == 2) {
                    return isValidShipLocation = true;
                } else {
                    for (int m = 1; m < newShip.length - 1; m++) {
                        if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                            isValidShipLocation = false;
                        }
                    }
                }
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней ячейки не
                //(9;9), то проверяются ячейки вокруг других палуб корабля общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipLocation = false;
                    }
                }
            }
        } else if (horFirstCoordinate == (newField.FIELDWIDTH - 1) &&
                verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (9;9)
            if (newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0') {
                isValidShipLocation = false;
                // если корабль однопалубный, то его можно ставить. проверка окончена
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
            } else {
                // если корабль многопалубный, то такого корабля не может быть.
                isValidShipLocation = false;
            }
        } else if (horFirstCoordinate > 0 && horFirstCoordinate < (newField.FIELDWIDTH - 1)
                && verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (n;9), где n > 0 и меньше 9
            if (newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipLocation = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipLocation = true;
                // если корабль многопалубный, то такого корабля не может быть
            } else {
                isValidShipLocation = false;
            }
        } else {
            // проверка общего случая на наличие кораблей вокруг первой ячейки
            for (int i = -1; i < 2; i++) {
                for (int k = -1; k < 2; k++) {
                    if (newField.cells[horFirstCoordinate + i][verFirstCoordinate + k] == '\u2bc0') {
                        isValidShipLocation = false;
                        // если корабль однопалубный, то корабль можно устанавливать
                    } else if (newShip.length == 1) {
                        isValidShipLocation = true;
                        // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                        // ячейки (n;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                        // то, является ли наш корабль двухпалубным
                    } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                        if (newShip.length == 2) {
                            return isValidShipLocation = true;
                        } else {
                            for (int m = 1; m < newShip.length - 1; m++) {
                                if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                        newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                        newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                                    isValidShipLocation = false;
                                }
                            }
                        }
                        // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней ячейки не
                        //(n;9), то проверяются ячейки вокруг других палуб корабля общим способом
                    } else {
                        for (int m = 1; m < newShip.length; m++) {
                            if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                                isValidShipLocation = false;
                            }
                        }
                    }
                }
            }

        }

//        }else{
        // аналогичная проверка для горизонтальной ориентации
//    }
        return isValidShipLocation;
    }
}

