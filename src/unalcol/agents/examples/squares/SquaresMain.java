package unalcol.agents.examples.squares;

import unalcol.agents.Agent;
import unalcol.agents.examples.squares.SI2.genkidama.*;

public class SquaresMain {
    public static void main(String[] argv) {
        Agent w_agent = new Agent( new DefinitiveSquaresAgent( Squares.WHITE ) );
        Agent b_agent = new Agent( new DefinitiveSquaresAgent( Squares.BLACK ) );
        SquaresMainFrame frame = new SquaresMainFrame( w_agent, b_agent );
        frame.setVisible(true);
    }
}
