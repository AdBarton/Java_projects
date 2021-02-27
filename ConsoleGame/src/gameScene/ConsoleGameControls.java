package gameScene;

import gameLogic.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleGameControls implements GameControls{

    private Scanner sc= new Scanner(System.in);

    @Override
    public String password() {
        String passwd;
        System.out.println("Zadej heslo:");
        passwd=sc.nextLine().trim();
        Pattern p = Pattern.compile("\\w{3,8}");
        Matcher m = p.matcher(passwd);
        if(!m.matches()){
            System.out.println("Heslo musi mit minimalne 3 a maximalne 8 znaku");
            passwd=password();
        }
        return passwd;
    }

    @Override
    public String chooseHero() {
        System.out.println("Vyber si hrdinu: (Warrior(W)/Hunter(H))");
        String choice= sc.nextLine();
        if(!choice.equalsIgnoreCase("W") && !choice.equalsIgnoreCase("H")){
            System.out.println("Vybral jsi spatne");
            choice=chooseHero();
        }
        return choice;
    }

    @Override
    public int setMoveCount() {
        System.out.println("Zadej pocet tahu");
        int pocetTahu;
        try{
            pocetTahu=sc.nextInt();
            sc.nextLine();
        }catch (Exception e){
            System.out.println("Chyba zadavani");
            pocetTahu = setMoveCount();
        }

        return pocetTahu;
    }

    @Override
    public void printResultOfGame(Game game) {
        if(game.getWinningPlayer().equals("draw")){
            System.out.println("Hra skoncila remizou");
        }else{
            System.out.println("Hra skoncila. Vyhral hrac: "+game.getWinningPlayer());
        }
        sc.close();
    }
    public void printPlayersStats(Game game){
        System.out.println(game.getPlayerStats());
    }

    public String chooseMove(String player){
        System.out.println("Hrac "+player+ " ches utocit(A) nebo branit(B) ?");
        String move=sc.nextLine();
        if(!move.equalsIgnoreCase("a")  && !move.equalsIgnoreCase("b")){
            System.out.println("Spatne zvoleny postup. Hrac "+player+ " ches utocit(A) nebo branit(B) ?");
            move = sc.nextLine();
        }
        return move;
    }
}
