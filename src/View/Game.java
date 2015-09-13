package View;

import Controller.Player;
import Controller.ShipLocation;
import Model.Field;
import Model.Ship;

import java.util.Random;

/**
 * Created by Sony on 30.06.2015.
 */
public class Game {
    static Field yourField = new Field();
    static Player yourPlayer = new Player();
    static String messageAboutPlayer;
    static Random randomMessage = new Random();
    // временный массив, в котором хранятся варианты того, выиграл игрок или нет
    static String[] messages = new String[] {
            "The ship has been damaged! Shoot again.",
            "This ship has been sent to the bottom.", "You overmissed. Try again.", "Your shoot is out of range. Try again.",
            "You lose!", "All ships are killed. You win!"
    };

    public static void main(String[] args) {
        System.out.println("View.Game start\nEnter your name:");
        String yourName = yourPlayer.inputTheName();
        System.out.printf("Hello, %s!\n", yourName);
        yourField.initField();
        yourField.showEmptyField();
        setShip();
        yourField.showField();
        messageAboutPlayer = messages[randomMessage.nextInt(messages.length)];
        System.out.println(messageAboutPlayer);
        System.out.println("View.Game over");
    }

    public static void setShip(){
        Ship.defineShipTypes();
        for (int i = 4; i > 0; i--) {
            Ship myShip = new Ship();
            myShip.length = i;
            myShip.amount = Ship.shipTypes.get(i);
            for (int k = 0; k < myShip.amount; k++){
                do {
                    ShipLocation.defineOrientation();
                    ShipLocation.setFirstHorCoordinate();
                    ShipLocation.setFirstVerCoordinate();
                    ShipLocation.isNotShipNear(myShip, yourField);
                } while (ShipLocation.isValidShipLocation);
                if (ShipLocation.isVertical){
                    for (int n = 0; n < myShip.length; n++){
                        yourField.cells[ShipLocation.horFirstCoordinate][ShipLocation.verFirstCoordinate + n] =
                                '\u2bc0';
                    }
                }
            }
        }
    }

}