package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class Game extends BasicGameState {

        // State given
    private static int gameState;
        // Map
    private TiledMap worldMap;
        // This is the init coords for the map
    int mapCoordX = 0, getMapCoordY = 0;
        // This is where the player will start on the map
    int playerX = 0, playerY = 0;
        // How fast the player will move
    int playerSpeed = 200;
        // Map bounds
    int mapBoundsLayer;


    public Game( int state ) {
        gameState = state;
    }

    public void init( GameContainer gc, StateBasedGame sbg ) throws SlickException {

    }

    public void render( GameContainer gc, StateBasedGame sbg, Graphics g ) throws SlickException {

    }

    public void update( GameContainer gc, StateBasedGame sbg, int delta ) throws SlickException {

    }

    public int getID() {
        return gameState;
    }

}
