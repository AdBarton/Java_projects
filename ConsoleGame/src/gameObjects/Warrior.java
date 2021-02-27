package gameObjects;

import utils.Utils;

public class Warrior extends Hero{
    private Weapon weapon;

    public Warrior() {
        super("Warrior",2000, 70, 5);
        this.weapon=new Weapon(200,10);
    }

    public void attack(Hero hero){
        int weaponHit= Utils.calculateHit(weapon.getStrength(),weapon.getCritical());
        super.attack(hero,weaponHit);
    }

    public boolean defend(){
        return super.defend();
    }
}
