package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Init extends StateBasedGame {

        // Game Name
    private static final String gameName = "Test Map!!!";
        // State Integers
    private static final int game = 1;

    public Init( String name ) {
            // Sets game name
        super( name );
            // adds States
        this.addState( new Game( game ) );
    }

    public void initStatesList( GameContainer gc ) throws SlickException {
            // init States
        this.getState( game ).init( gc, this );
            // Goes to state
        this.enterState( game );
    }

    public static void main( String[] args ) {
        AppGameContainer appgc;

        try {
            appgc = new AppGameContainer(new Init(gameName));
            appgc.setDisplayMode(640, 640, false);
            //appgc.setShowFPS(false);
            appgc.start();
        }
        catch ( SlickException e ){
            e.printStackTrace();
        }
    }

}
