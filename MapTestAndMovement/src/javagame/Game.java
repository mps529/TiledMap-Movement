package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class Game extends BasicGameState {

        // State given
    private static int gameState;
        // Map
    private TiledMap worldMap;
        // Map bounds
    int mapBoundsLayer;
        // Map above layer
    int mapWalkUnder;

        // This is the players different sprite states
    Animation bucky, movingUp, movingDown, movingLeft, movingRight, movingUpRun, movingDownRun, movingLeftRun, movingRightRun;
        // Animation duration
    int[] duration = { 450, 450 };
    int[] durationMan = { 70, 70, 70, 70, 70, 70, 70, 70 };
    int[] durationManRun = { 50, 50, 50, 50, 50, 50, 50, 50 };

        // This is the init coords for the map
    int mapCoordX = 0, mapCoordY = 0;
        // This is for the map skew
    int mapSkewX = 0, mapSkewY = 0;
        // This is where the player will start on the map
    int playerX = 10, playerY = 10;
        // Player Skew
    int playerSkewX = 0, playerSkewY = 0;
        // How fast the player will move
    int playerSpeed = 200;
    boolean isPlayerWalking = false;

    public Game( int state ) {
        gameState = state;
    }

    public void init( GameContainer gc, StateBasedGame sbg ) throws SlickException {
            // Declaring the Image array for movement
        Image[] walkUp = { new Image( "MapTestAndMovement/res/person/walkUp/walkUp2.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp3.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp4.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp5.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp6.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp7.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp8.png" ),  new Image( "MapTestAndMovement/res/person/walkUp/walkUp9.png" ) };
        Image[] walkDown = { new Image( "MapTestAndMovement/res/person/buckysFront.png" ),  new Image( "MapTestAndMovement/res/person/buckysFront.png" ) };
        Image[] walkLeft = { new Image( "MapTestAndMovement/res/person/buckysLeft.png" ),  new Image( "MapTestAndMovement/res/person/buckysLeft.png" ) };
        Image[] walkRight = { new Image( "MapTestAndMovement/res/person/buckysRight.png" ),  new Image( "MapTestAndMovement/res/person/buckysRight.png" ) };
            // Setting character animation
            // Would be better if we had walking Images but o well for now this will do

        movingUp = new Animation( walkUp, durationMan, true );
        movingDown = new Animation( walkDown, duration, true );
        movingLeft = new Animation( walkLeft, duration, true );
        movingRight = new Animation( walkRight, duration, true );

        movingUpRun = new Animation( walkUp, durationManRun, true );
        movingDownRun = new Animation( walkDown, duration, true );
        movingLeftRun = new Animation( walkLeft, duration, true );
        movingRightRun = new Animation( walkRight, duration, true );

            // This is so he is looking at us when we start
        bucky = movingDown;

        worldMap = new TiledMap("MapTestAndMovement/res/map/plains.tmx");
            // This will get the collision layer
        mapBoundsLayer = worldMap.getLayerIndex( "collision" );
        mapWalkUnder = worldMap.getLayerIndex( "above" );
    }

    public void render( GameContainer gc, StateBasedGame sbg, Graphics g ) throws SlickException {

        worldMap.render( mapCoordX*32, mapCoordY*32 );
            // Middle of the Screen
        bucky.draw(gc.getWidth() / 2, gc.getHeight() / 2);

        worldMap.render( mapCoordX*32, mapCoordY*32, mapWalkUnder );


    }

    public void update( GameContainer gc, StateBasedGame sbg, int delta ) throws SlickException {

        Input input = gc.getInput();

            // Change Speed
        if( input.isKeyPressed( Input.KEY_SPACE ) ) {
            if( playerSpeed == 100 ) {
                playerSpeed = 200;
            }
            else {
                playerSpeed = 100;
            }
        }
            // Character Movement
        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
            if( worldMap.getTileId( playerX, playerY-1, mapBoundsLayer) == 0 ) {
                bucky = movingUp;
                bucky.start();
                moveUp();

            }
        }
        else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
            if( worldMap.getTileId( playerX+1, playerY, mapBoundsLayer) == 0 ) {
                bucky = movingRight;
                moveRight();
            }
        }
        else if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
            if( worldMap.getTileId( playerX, playerY+1, mapBoundsLayer) == 0 ) {
                bucky = movingDown;
                moveDown();
            }
        }
        else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
            if( worldMap.getTileId( playerX-1, playerY, mapBoundsLayer) == 0 ) {
                bucky = movingLeft;
                moveLeft();
            }
        }
        else {
            bucky.stop();
            //bucky.setCurrentFrame(0);
        }

        if( playerSkewX < 0 ) {
            mapSkewX++;
            playerSkewX = 32;
        }
        if( playerSkewX > 32 ) {
            mapSkewX--;
            playerSkewY = 0;
        }
        if( playerSkewY < 0 ) {
            mapSkewY++;
            playerSkewY = 32;
        }
        if( playerSkewY > 32 ) {
            mapSkewY--;
            playerSkewY = 0;
        }

    }


    private void moveUp() {
        try {
            Thread.sleep( playerSpeed );
            playerY--;
            playerSkewY--;
            mapCoordY++;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
    private void moveDown() {
        try {
            Thread.sleep( playerSpeed );
            playerY++;
            playerSkewY++;
            mapCoordY--;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
    private void moveLeft() {

        try {
            Thread.sleep( playerSpeed );
            playerX--;
            mapSkewX--;
            mapCoordX++;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
    private void moveRight() {
        try {
            Thread.sleep( playerSpeed );
            playerX++;
            mapSkewX++;
            mapCoordX--;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }

    public int getID() {
        return gameState;
    }

}
