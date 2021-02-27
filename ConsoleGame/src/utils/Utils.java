package utils;

public class Utils {

    public static int calculateHit(int strength, int critical){
        return strength+((critical/100) * strength);
    }
}
