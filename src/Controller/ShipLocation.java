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
    int horFirstCell;
    int verFirstCell;
    String[] orientations = new String[]{"horizontal", "vertical"}; //заменить на 0 и 1 (вертикальный - 1)
    String shipOrientation;

    //TODO По моей текущей задумке корабли устанавливаются рандомно в любое место на двумерном поле горизонтально или вертикално.
    // TODO я еще не успела придумать, как осуществлять проверку того, чтобы при установке не было других кораблей рядом.
    //TODO и еще не придумала, как отслеживать их количество при установке. - см. выше - массивы кораблей
    //todo если корабль подбит, помечать ячейку квадратом с крестом
    //если у корабля горизонтальное расположение, то координата первой ячейки по горизонтали подбирается так,
    // чтобы горизонтальная координата + длина корабля не выходили за пределы поля
    public int setFirstHorCoordinate(){
        if (!isVertical()) {
            do {
                horFirstCell = randomCoordinate.nextInt(newField.FIELDWIDTH);
            } while ((horFirstCell + newShip.length - 1) < newField.FIELDWIDTH);
            return horFirstCell;
        }else{
            horFirstCell = randomCoordinate.nextInt(newField.FIELDWIDTH);
            return horFirstCell;
        }
    }
 // если у корабля вертикальное расположение, то координата первой ячейки по вертикали подбирается так,
    //чтобы вертикальная координата + длина корабля не выходили за пределы поля
    public int setFirstVerCoordinate(){
        if (isVertical()){
            do{
                verFirstCell = randomCoordinate.nextInt(newField.FIELDHEIGHT);
            } while ((verFirstCell + newShip.length - 1) < newField.FIELDHEIGHT);
            return verFirstCell;
        }else {
            verFirstCell = randomCoordinate.nextInt(newField.FIELDHEIGHT);
            return verFirstCell;
        }
    }

     //метод случайного выбора горизонтального или вертикального расположения
    public boolean isVertical(){
        shipOrientation = orientations[randomCoordinate.nextInt(orientations.length)];
        if (shipOrientation == "vertical"){
            return true;
        } else{
            return false;
        }
    }

    //проверка на то, чтобы рядом с каждой ячейкой корабля не было других кораблей
    public boolean isNotShipNear(int ourHorCoordinate, int ourVerCoordinate){
        if (newField.cells[ourHorCoordinate][ourVerCoordinate + 1] == '\u2bc0'){

        }
    }
}

