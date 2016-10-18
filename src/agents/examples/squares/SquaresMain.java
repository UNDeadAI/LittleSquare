/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents.examples.squares;

import agents.Agent;

/**
 * @author Jonatan
 */
public class SquaresMain {

    public static void main(String[] argv) {
        Agent w_agent = new Agent(new FirstSquaresAgent(Squares.WHITE, 3));
        Agent b_agent = new Agent(new FirstSquaresAgent(Squares.BLACK, 3));
        SquaresMainFrame frame = new SquaresMainFrame(w_agent, b_agent);
        frame.setVisible(true);
    }
}

