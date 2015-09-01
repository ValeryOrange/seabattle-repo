import java.util.Scanner;

/**
 * Created by Sony on 30.06.2015.
 */
public class Player {
    public String inputTheName(){
        Scanner name = new Scanner(System.in);
        String playerName = name.nextLine();
        return playerName;
    }
}
