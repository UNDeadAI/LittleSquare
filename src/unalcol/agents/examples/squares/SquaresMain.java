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
        //Agent w_agent = new Agent(new Dummy2Agent(Squares.WHITE));
        Agent w_agent = new Agent(new MiniMaxPlayer(Squares.WHITE, 1));
        Agent b_agent = new Agent(new QuickSquaresPlayer(Squares.BLACK));
        //Agent b_agent = new Agent(new DummySquaresAgentProgram(Squares.BLACK));
        SquaresMainFrame frame = new SquaresMainFrame(w_agent, b_agent);
        frame.setVisible(true);
    }

}

