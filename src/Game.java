import java.util.Random;

/**
 * Created by Sony on 30.06.2015.
 */
public class Game {
    static Field yourField = new Field();
    static char[] letters = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    static int rowNumber;
    static Player yourPlayer = new Player();
    // временный массив, в котором хранятся варианты того, выиграл игрок или нет
    static Random randomMessage = new Random();
    static String messageAboutPlayer;
    static String[] messages = new String[] {
            "The ship has been damaged! Shoot again.",
            "This ship has been sent to the bottom.", "You overmissed. Try again.", "Your shoot is out of range. Try again.",
            "You lose!", "All ships are killed. You win!"
    };

    public static void main(String[] args) {
        System.out.println("Game start\nEnter your name:");
        String yourName = yourPlayer.inputTheName();
        System.out.printf("Hello, %s!\n", yourName);
        showEmptyField();
        messageAboutPlayer = messages[randomMessage.nextInt(messages.length)];
        System.out.println(messageAboutPlayer);
        System.out.println("Game over");
    }

    public static void showEmptyField() {
        for (int k = 0; k < letters.length; k++){
            System.out.print(letters[k] + "\t");
        }
        System.out.println();
        for (int i = 0; i < yourField.cells.length; i++) {
            rowNumber = i+1;
            System.out.print(rowNumber + "\t");
            for (int j = 0; j < yourField.cells.length; j++) {
                yourField.cells[i][j] = '.';
                System.out.print(yourField.cells[i][j] + "\t");
            }
            System.out.println();
        }
    }
}