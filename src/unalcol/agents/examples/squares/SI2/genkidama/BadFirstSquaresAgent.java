package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;
import unalcol.agents.examples.squares.SquaresPercept;

import java.util.ArrayList;
import java.util.HashMap;

public class BadFirstSquaresAgent implements AgentProgram {

    protected String color;
    private int size;
    private int maxDepth;
    private HashMap<Integer, Action> actions;
    private SquaresPercept percept;
    private SimulatedBoard board;

    public BadFirstSquaresAgent(String color, int depth) {
        maxDepth = depth;
        this.color = color;
        board = new SimulatedBoard(size);
    }

    private boolean terminalState(int depth) {
        return depth > maxDepth || board.full();
    }

    private int utility(ArrayList<String> moves) {
        SimulatedBoard simulatedBoard = new SimulatedBoard(board);
        for (String s : moves)
            act(color.equals(Squares.WHITE), s, simulatedBoard);

        int w = simulatedBoard.white_count();
        int b = simulatedBoard.black_count();
        if (color.equals(Squares.WHITE))
            return w - b;
        return b - w;
    }

    private void act(boolean whiteTurn, String action, SimulatedBoard board) {
        String[] code = action.split(":");
        int i = Integer.parseInt(code[0]);
        int j = Integer.parseInt(code[1]);
        int side = 0;
        if (code[2].equals(Squares.LEFT)) side = SimulatedBoard.LEFT;
        if (code[2].equals(Squares.TOP)) side = SimulatedBoard.TOP;
        if (code[2].equals(Squares.RIGHT)) side = SimulatedBoard.RIGHT;
        if (code[2].equals(Squares.BOTTOM)) side = SimulatedBoard.BOTTOM;

        board.play(whiteTurn, i, j, side);
    }

    private Action alphaBetaSearch() {
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.TRUE))
                    act(!color.equals(Squares.WHITE), action, board);

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.TRUE))
                    act(!color.equals(Squares.WHITE), action, board);
            }
        }

        actions = new HashMap<>();
        ArrayList<String> moves = new ArrayList<>();
        int v = maxValue(Integer.MIN_VALUE, Integer.MAX_VALUE, moves, 1);
        Action a = actions.get(v);
        if(a != null) {
            act(color.equals(Squares.WHITE), a.getCode(), board);
            return actions.get(v);
        }
        return new Action(0 + ":" + 0 + ":" + Squares.BOTTOM);
    }

    private int maxValue(int alpha, int beta, ArrayList<String> moves, int depth) {
        if (terminalState(depth)) return utility(moves);
        int v = Integer.MIN_VALUE;
        ArrayList<String> tmp;
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new ArrayList<>(moves);
                    tmp.add(action);
                    v = Math.max(v, minValue(alpha, beta, tmp, depth + 1));
                    if (depth == 1)
                        actions.put(v, new Action(action));
                    if (v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new ArrayList<>(moves);
                    tmp.add(action);
                    v = Math.max(v, minValue(alpha, beta, tmp, depth + 1));
                    if (depth == 1)
                        actions.put(v, new Action(action));
                    if (v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
            }
        }
        return v;
    }

    private int minValue(int alpha, int beta, ArrayList<String> moves, int depth) {
        if (terminalState(depth)) return utility(moves);
        int v = Integer.MAX_VALUE;
        ArrayList<String> tmp;
        String action;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                action = i + ":" + j + ":" + Squares.RIGHT;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new ArrayList<>(moves);
                    tmp.add(action);
                    v = Math.min(v, maxValue(alpha, beta, tmp, depth + 1));
                    if (v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if (percept.getAttribute(action).equals(Squares.FALSE)) {
                    tmp = new ArrayList<>(moves);
                    tmp.add(action);
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

            if (s2.equals("0:0:10:0") || s2.equals("0:0:0:0"))
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
    public void init() {
    }
}
