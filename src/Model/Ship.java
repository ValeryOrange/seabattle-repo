package Model;

import java.util.HashMap;

/**
 * Created by Val on 10.09.15.
 */
public class Ship {
    public int amount;
    public int length;
    public HashMap<String, String> shipTypes = new HashMap<>();

    public HashMap<String, String> defineShipTypes() {
        shipTypes.put("4","1");
        shipTypes.put("3","2");
        shipTypes.put("2", "3");
        shipTypes.put("1", "4");
        return shipTypes;
    }


    public Ship(){

    }
}