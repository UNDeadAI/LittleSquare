/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents.examples.squares;

import agents.Action;
import agents.Agent;
import agents.Percept;
import agents.Clock;
import agents.simulate.Environment;
import agents.simulate.gui.EnvironmentView;
import types.collection.vector.Vector;

/**
 * @author Jonatan
 */
class Squares extends Environment {
    static String PASS = "PASS";
    static String TURN = "play";
    static String TIME = "time";
    static String WHITE = "white";
    static String BLACK = "black";
    static String SPACE = "space";
    static String LEFT = "left";
    static String TOP = "top";
    static String RIGHT = "right";
    static String BOTTOM = "bottom";
    static String SIZE = "size";
    static String TRUE = "true";
    static String FALSE = "false";

    protected Board board = null;
    protected Clock clock = null;

    protected static Vector<Agent> init(Agent white, Agent black) {
        Vector<Agent> a = new Vector<>();
        a.add(white);
        a.add(black);
        return a;
    }

    Squares(Agent white, Agent black) {
        super(init(white, black));
    }


    public void init(Board b, Clock c) {
        clock = c;
        board = b;
    }

    @Override
    public boolean act(Agent agent, Action action) {
        if (board.full()) {
            agents.get(0).die();
            agents.get(1).die();
            int w = board.white_count();
            int b = board.black_count();
            if (w > b) {
                updateViews(EnvironmentView.DONE + ": Blue wins");
            } else {
                if (b > w)
                    updateViews(EnvironmentView.DONE + ": Red wins");
                else
                    updateViews(EnvironmentView.DONE + ": Draw");
            }
        }
        if (clock.white_turn()) {
            if (agent != agents.get(0)) {
                updateViews("Working");
                return false;
            }
            if (clock.white_time() <= 0) {
                agents.get(0).die();
                agents.get(1).die();
                updateViews(EnvironmentView.DONE + ": Red wins");
            }
        } else {
            if (agent != agents.get(1)) {
                updateViews("Working");
                return false;
            }
            if (clock.black_time() <= 0) {
                agents.get(0).die();
                agents.get(1).die();
                updateViews(EnvironmentView.DONE + ": Blue wins");
            }
        }
        String[] code = action.getCode().split(":");
        int i = Integer.parseInt(code[0]);
        int j = Integer.parseInt(code[1]);
        int side = 0;
        if (code[2].equals(LEFT)) side = Board.LEFT;
        if (code[2].equals(TOP)) side = Board.TOP;
        if (code[2].equals(RIGHT)) side = Board.RIGHT;
        if (code[2].equals(BOTTOM)) side = Board.BOTTOM;
        if (board.play(clock.white_turn(), i, j, side)) {
            clock.swap();
            updateViews("Working");
            return true;
        }
        updateViews("Working");
        return false;
    }

    @Override
    public Percept sense(Agent agent) {
        return new SquaresPercept(board, clock);
    }

    @Override
    public void init(Agent agent) {}

    @Override
    public Vector<Action> actions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
