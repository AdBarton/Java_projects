package gameLogic;

import gameObjects.Hero;
import gameObjects.Hunter;
import gameObjects.Warrior;
import gameScene.ConsoleGameControls;

import javax.net.ssl.HostnameVerifier;
import java.security.PublicKey;
import java.util.regex.Pattern;

public class Game {
    private ConsoleGameControls gameControl;
    private int moveCount;
    private String winningPlayer="draw";
    private Hero heroA;
    private Hero heroB;

    public Game() {
        this.gameControl = new ConsoleGameControls();
        this.moveCount = gameControl.setMoveCount();
        this.heroA = gameControl.chooseHero().equalsIgnoreCase("W") ? new Warrior() : new Hunter();
        this.heroB = gameControl.chooseHero().equalsIgnoreCase("W") ? new Warrior() : new Hunter();
    }

    public void gameStart(){
        Hero[] heroes=new Hero[2];
        heroes[0]=heroA instanceof Warrior ? (Warrior)heroA : (Hunter)heroA;
        heroes[1]=heroB instanceof Warrior ? (Warrior)heroB : (Hunter)heroB;
        for(int i=0;i<moveCount;i++){
            String move = gameControl.chooseMove(heroes[i%2].getName());
            if(heroes[0].getLife()>0 || heroes[1].getLife()>0){
                if(move.equalsIgnoreCase("A")){
                    attack(heroes, i);
                }else{
                    defend(heroes, i);
                }
            }else{
                break;
            }
        }
        chooseWinner();
        gameControl.printPlayersStats(this);
        gameControl.printResultOfGame(this);
    }

    public String getPlayerStats(){
        String stats="";
        stats+= "Hrac jedna"+heroA.getName()+" "+ heroA.getLife()+"\n";
        stats+= "Hrac dva"+heroB.getName()+" " + heroB.getLife()+"\n";
        return stats;
    }

    private void defend(Hero[] heroes, int i) {
        if(heroes[i%2] instanceof Warrior){
            Warrior warrior= (Warrior) heroes[i%2];
            if(!warrior.defend()){
                warriorAttack(heroes,i,warrior);
            }
        }else{
            Hunter hunter= (Hunter) heroes[i%2];
            if(!hunter.defend()){
                hunterAttack(heroes,i,hunter);
            }
        }
    }

    private void attack(Hero[] heroes, int i) {
        if(heroes[i %2] instanceof Warrior){
            Warrior warrior=(Warrior) heroes[i %2];
            warriorAttack(heroes, i, warrior);
        }else{
            ((Hunter) heroes[1]).attack(heroes[0]);
        }
    }

    private void warriorAttack(Hero[] heroes, int i, Warrior warrior) {
        if(i %2==0)
            warrior.attack(heroes[1]);
        else
            warrior.attack(heroes[0]);
    }

    private void hunterAttack(Hero[] heroes, int i, Hunter hunter) {
        if(i %2==0)
            hunter.attack(heroes[1]);
        else
            hunter.attack(heroes[0]);
    }

    public String getWinningPlayer() {
        return winningPlayer;
    }

    public void chooseWinner(){
        if(heroA.getLife()>heroB.getLife()){
            setWinningPlayer(heroA.getName());
        }else{
            setWinningPlayer(heroB.getName());
        }
    }

    public void setWinningPlayer(String winningPlayer) {
        this.winningPlayer = winningPlayer;
    }
}
