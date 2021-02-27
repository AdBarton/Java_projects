package gameScene;

import gameLogic.Game;

public interface GameControls {
    String password();
    String chooseHero();
    int setMoveCount();
    void printResultOfGame(Game game);
    void printPlayersStats(Game game);
    String chooseMove(String player);

}
