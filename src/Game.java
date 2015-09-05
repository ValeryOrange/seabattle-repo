import java.util.Random;

/**
 * Created by Sony on 30.06.2015.
 */
public class Game {
    static Field yourField = new Field();
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
        System.out.println("testing git");
        String yourName = yourPlayer.inputTheName();
        System.out.printf("Hello, %s!\n", yourName);
        showEmptyField();
        messageAboutPlayer = messages[randomMessage.nextInt(messages.length)];
        System.out.println(messageAboutPlayer);
        System.out.println("Game over");
    }

    public static void showEmptyField() {
//        yourField.cells[0] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        for (int i = 1; i < yourField.cells.length; i++) {
            for (int j = 1; j < yourField.cells.length; j++) {
                yourField.cells[i][j] = '.';
                System.out.print(yourField.cells[i][j]);
            }
            System.out.println();
        }
    }
}