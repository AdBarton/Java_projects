package gameObjects;

public class Weapon {
    private int strength;
    private int critical;

    public Weapon(int speed, int critical) {
        this.critical = speed;
        this.strength = critical;
    }

    public int getStrength() {
        return strength;
    }

    public int getCritical() {
        return critical;
    }
}
