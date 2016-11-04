package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;
import unalcol.agents.examples.squares.SquaresPercept;

import java.util.HashMap;

public class FirstSquaresAgent implements AgentProgram {

    private String color, time;
    private int size, maxDepth;
    private HashMap<Integer, Action> actions;
    private SquaresPercept percept;
    private SimulatedBoard board;

    public FirstSquaresAgent(String color, int depth) {
        maxDepth = depth;
        this.color = color;
        board = new SimulatedBoard(size);
    }

    private boolean terminalState(int depth, SimulatedBoard board) {
        return depth > maxDepth || board.full();
    }

    private int utility(SimulatedBoard board) {
        int w = board.white_count();
        int b = board.black_count();
        if (color.equals(Squares.WHITE))
            return w - b;
        return b - w;
    }

    private void act(boolean whiteTurn, String action, SimulatedBoard b) {
        String[] code = action.split(":");
        int i = Integer.parseInt(code[0]);
        int j = Integer.parseInt(code[1]);
        int side = 0;
        if (code[2].equals(Squares.LEFT)) side = SimulatedBoard.LEFT;
        if (code[2].equals(Squares.TOP)) side = SimulatedBoard.TOP;
        if (code[2].equals(Squares.RIGHT)) side = SimulatedBoard.RIGHT;
        if (code[2].equals(Squares.BOTTOM)) side = SimulatedBoard.BOTTOM;

        b.play(whiteTurn, i, j, side);
    }

    private Action alphaBetaSearch() {
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.TRUE) && !((board.values[i][j] & SimulatedBoard.RIGHT) ==
                        SimulatedBoard.RIGHT))
                    act(!color.equals(Squares.WHITE), action, board);

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.TRUE) && !((board.values[i][j] & SimulatedBoard.BOTTOM) ==
                        SimulatedBoard.BOTTOM))
                    act(!color.equals(Squares.WHITE), action, board);
            }
        }

        actions = new HashMap<>();
        int v = maxValue(Integer.MIN_VALUE, Integer.MAX_VALUE, board, 1);
        Action a = actions.get(v);
        act(color.equals(Squares.WHITE), a.getCode(), board);
        return a;
    }

    private int maxValue(int alpha, int beta, SimulatedBoard board, int depth) {
        if (terminalState(depth, board)) return utility(board);
        int v = Integer.MIN_VALUE, tmp2;
        SimulatedBoard tmp;
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new SimulatedBoard(board);
                    act(color.equals(Squares.WHITE), action, tmp);
                    tmp2 = minValue(alpha, beta, tmp, depth + 1);
                    if (depth == 1)
                        actions.put(tmp2, new Action(action));
                    v = Math.max(v, tmp2);
                    if (v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new SimulatedBoard(board);
                    act(color.equals(Squares.WHITE), action, tmp);
                    tmp2 = minValue(alpha, beta, tmp, depth + 1);
                    if (depth == 1)
                        actions.put(tmp2, new Action(action));
                    v = Math.max(v, tmp2);
                    if (v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
            }
        }
        return v;
    }

    private int minValue(int alpha, int beta, SimulatedBoard board, int depth) {
        if (terminalState(depth, board)) return utility(board);
        int v = Integer.MAX_VALUE;
        SimulatedBoard tmp;
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new SimulatedBoard(board);
                    act(!color.equals(Squares.WHITE), action, tmp);
                    v = Math.min(v, maxValue(alpha, beta, tmp, depth + 1));
                    if (v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new SimulatedBoard(board);
                    act(!color.equals(Squares.WHITE), action, tmp);
                    v = Math.min(v, maxValue(alpha, beta, tmp, depth + 1));
                    if (v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
            }
        }
        return v;
    }

    @Override
    public Action compute(Percept p) {
        if (p.getAttribute(Squares.TURN).equals(color)) {
            int size2 = Integer.parseInt((String) p.getAttribute(Squares.SIZE));

            String s = (color.equals(Squares.WHITE) ? Squares.WHITE : Squares.BLACK) + "_" + Squares.TIME, s2;
            s2 = (String) p.getAttribute(s);

            if( time == null){
                time = s2;
                board = new SimulatedBoard(size);
            }
            else if( time.compareTo(s2) >= 0 )
                time = s2;
            else
                board = new SimulatedBoard(size);

            if (size2 != size) {
                size = size2;
                board = new SimulatedBoard(size);
            }

            percept = (SquaresPercept) p;

            return alphaBetaSearch();
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {}
}
