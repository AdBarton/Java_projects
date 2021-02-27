package gameObjects;

import utils.Utils;

public class Hunter extends Hero {
    private Weapon weapon;

    public Hunter() {
        super("Hunter",2000,70,20);
        this.weapon=new Bow(100,50);
    }

    public void attack(Hero hero){
        int weaponHit= Utils.calculateHit(weapon.getStrength(),weapon.getCritical());
        super.attack(hero,weaponHit);
    }

    public boolean defend(){
        return super.defend();
    }
}
