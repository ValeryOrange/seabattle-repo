package Controller;

import Model.Field;
import Model.Ship;

import java.util.Random;


/**
 * Created by Sony on 30.06.2015.
 */
public class ShipLocation {
    Field newField = new Field();
    Ship newShip = new Ship();
    Random randomCoordinate = new Random();
    int horFirstCoordinate;
    int verFirstCoordinate;
    String[] orientations = new String[]{"horizontal", "vertical"};
    String shipOrientation;
    boolean isValidShipCell;

    //TODO По моей текущей задумке корабли устанавливаются рандомно в любое место на двумерном поле
    // todo горизонтально или вертикално.
    //TODO и еще не придумала, как отслеживать их количество при установке. - см. выше - массивы кораблей
    //todo если корабль подбит, помечать ячейку квадратом с крестом
    //если у корабля горизонтальное расположение, то координата первой ячейки по горизонтали подбирается так,
    // чтобы горизонтальная координата + длина корабля не выходили за пределы поля
    public int setFirstHorCoordinate() {
        if (!isVertical()) {
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
    public int setFirstVerCoordinate() {
        if (isVertical()) {
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
    public boolean isVertical() {
        shipOrientation = orientations[randomCoordinate.nextInt(orientations.length)];
        if (shipOrientation == "vertical") {
            return true;
        } else {
            return false;
        }
    }

    //проверка на то, чтобы рядом с каждой ячейкой корабля не было других кораблей
    public boolean isNotShipNear() {
        isValidShipCell = true;
//        if (isVertical()) {
        //проверка на то, чтобы в первой ячейке не был установлен корабль ранее
        if (newField.cells[horFirstCoordinate][verFirstCoordinate] == '\u2bc0') {
            isValidShipCell = false;
        } else if (horFirstCoordinate == 0 && verFirstCoordinate == 0) {
            // проверка ячеек вокруг ячейки (0;0)
            if (newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0') {
                isValidShipCell = false;
                // если вокруг ячейки (0;0) нет кораблей и корабль однопалубный, то проверка закончена
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если вокруг ячейки (0;0) нет кораблей и корабль многопалубный, то проверяются ячейки вокруг
                // других палуб корабля с учетом уже проверенных ячеек
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipCell = false;
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
                isValidShipCell = false;
                // если вокруг первой ячейки нет кораблей и корабль однопалубный, то проверка окончена
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                // ячейки (0;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                // то, является ли наш корабль двухпалубным
            } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                if (newShip.length == 2) {
                    return isValidShipCell = true;
                } else {
                    for (int m = 1; m < newShip.length - 1; m++) {
                        if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                            isValidShipCell = false;
                        }
                    }
                }
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней ячейки не
                //(0;9), то проверяются ячейки вокруг других палуб корабля общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipCell = false;
                    }
                }
            }
        } else if (horFirstCoordinate == 0 && verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (0;9)
            if (newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipCell = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если корабль многопалубный, то таким образом установить корабль нельзя
            } else {
                isValidShipCell = false;
            }
        } else if (horFirstCoordinate > 0 && horFirstCoordinate < (newField.FIELDWIDTH - 1) &&
                verFirstCoordinate == 0) {
            // проверяются ячейки вокруг ячейки (n;0), где n > 0 и меньше 9
            if (newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipCell = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если корабль многопалубный, то проверяются ячейки вокруг других палуб общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipCell = false;
                    }
                }
            }
        } else if (horFirstCoordinate == (newField.FIELDWIDTH - 1) && verFirstCoordinate == 0) {
            // проверяются ячейки вокруг ячейки (9;0)
            if (newField.cells[horFirstCoordinate][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate + 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipCell = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если корабль многопалубный, то проверяются ячейки вокруг других палуб общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipCell = false;
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
                isValidShipCell = false;
                // если вокруг первой ячейки нет кораблей и корабль однопалубный, то проверка окончена
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                // ячейки (9;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                // то, является ли наш корабль двухпалубным
            } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                if (newShip.length == 2) {
                    return isValidShipCell = true;
                } else {
                    for (int m = 1; m < newShip.length - 1; m++) {
                        if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                            isValidShipCell = false;
                        }
                    }
                }
                // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней ячейки не
                //(9;9), то проверяются ячейки вокруг других палуб корабля общим способом
            } else {
                for (int m = 1; m < newShip.length; m++) {
                    if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                            newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                        isValidShipCell = false;
                    }
                }
            }
        } else if (horFirstCoordinate == (newField.FIELDWIDTH - 1) &&
                verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (9;9)
            if (newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0') {
                isValidShipCell = false;
                // если корабль однопалубный, то его можно ставить. проверка окончена
            } else if (newShip.length == 1) {
                isValidShipCell = true;
            } else {
                // если корабль многопалубный, то такого корабля не может быть.
                isValidShipCell = false;
            }
        } else if (horFirstCoordinate > 0 && horFirstCoordinate < (newField.FIELDWIDTH - 1)
                && verFirstCoordinate == (newField.FIELDHEIGHT - 1)) {
            // проверяются ячейки вокруг ячейки (n;9), где n > 0 и меньше 9
            if (newField.cells[horFirstCoordinate - 1][verFirstCoordinate] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate - 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate - 1] == '\u2bc0' ||
                    newField.cells[horFirstCoordinate + 1][verFirstCoordinate] == '\u2bc0') {
                isValidShipCell = false;
                // если корабль однопалубный, то корабль можно устанавливать
            } else if (newShip.length == 1) {
                isValidShipCell = true;
                // если корабль многопалубный, то такого корабля не может быть
            } else {
                isValidShipCell = false;
            }
        } else {
            // проверка общего случая на наличие кораблей вокруг первой ячейки
            for (int i = -1; i < 2; i++) {
                for (int k = -1; k < 2; k++) {
                    if (newField.cells[horFirstCoordinate + i][verFirstCoordinate + k] == '\u2bc0') {
                        isValidShipCell = false;
                        // если корабль однопалубный, то корабль можно устанавливать
                    } else if (newShip.length == 1) {
                        isValidShipCell = true;
                        // если вокруг первой ячейки нет кораблей, корабль многопалубный и координаты последней
                        // ячейки (n;9), то проверяются ячейки вокруг других палуб корабля с выделенной проверкой на
                        // то, является ли наш корабль двухпалубным
                    } else if (verFirstCoordinate + newShip.length == newField.FIELDHEIGHT) {
                        if (newShip.length == 2) {
                            return isValidShipCell = true;
                        } else {
                            for (int m = 1; m < newShip.length - 1; m++) {
                                if (newField.cells[horFirstCoordinate][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                        newField.cells[horFirstCoordinate - 1][verFirstCoordinate + m + 1] == '\u2bc0' ||
                                        newField.cells[horFirstCoordinate + 1][verFirstCoordinate + m + 1] == '\u2bc0') {
                                    isValidShipCell = false;
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
                                isValidShipCell = false;
                            }
                        }
                    }
                }
            }

        }

//        }else{
        // аналогичная проверка для горизонтальной ориентации
//    }
        return isValidShipCell;
    }
}

