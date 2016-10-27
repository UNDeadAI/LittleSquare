/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.squares;

import unalcol.agents.Agent;
import unalcol.agents.examples.squares.SI2.genkidama.*;

/**
 * @author Jonatan
 */
public class SquaresMain {
    public static void main(String[] argv) {
        Agent w_agent = new Agent(new FirstSquaresAgent(Squares.WHITE, 1));
        Agent b_agent = new Agent(new FirstSquaresAgent(Squares.BLACK, 1));
        SquaresMainFrame frame = new SquaresMainFrame(w_agent, b_agent);
        frame.setVisible(true);
    }

}

