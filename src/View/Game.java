package View;

import Controller.Player;
import Model.Field;

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
        messageAboutPlayer = messages[randomMessage.nextInt(messages.length)];
        System.out.println(messageAboutPlayer);
        System.out.println("View.Game over");
    }

}