package unalcol.agents.examples.squares;

import unalcol.agents.Agent;
import unalcol.agents.examples.squares.SI2.genkidama.*;

public class SquaresMain {
    public static void main(String[] argv) {
        //Agent w_agent = new Agent( new MiniMaxPlayer( Squares.WHITE, 3 ) );
        Agent w_agent = new Agent( new QuickSquaresPlayer(Squares.WHITE) );
        //Agent b_agent = new Agent( new QuickSquaresPlayer( Squares.BLACK ) );
        Agent b_agent = new Agent( new MiniMaxPlayer( Squares.BLACK, 3 ) );
        //Agent b_agent = new Agent(new MeroBob(Squares.BLACK));
        SquaresMainFrame frame = new SquaresMainFrame( w_agent, b_agent );
        frame.setVisible(true);
    }
}

