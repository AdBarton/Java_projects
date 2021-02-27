package gameObjects;

import utils.Utils;

public abstract class Hero {

    private String name;
    private int life;
    private int strength;
    private int speed;
    private boolean isAttackable;

    private int defendCount= 0;

    public Hero(String name,int life,int strength,int speed) {
        this.name=name;
        this.life=life;
        this.strength=strength;
        this.speed=speed;
        this.isAttackable=true;
        this.defendCount=0;
    }

    public String getName(){
        return name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    protected void attack(Hero hero,int weaponHit){
        this.isAttackable=true;
        if (hero.isAttackable){
            int hit= Utils.calculateHit(strength,speed) + weaponHit;
            hero.setLife(hero.getLife()-hit);
        }

    }
    protected boolean defend(){
        if(defendCount<= 2){
            this.isAttackable=false;
            defendCount++;
            return true;
        }
        return false;
    }
}
