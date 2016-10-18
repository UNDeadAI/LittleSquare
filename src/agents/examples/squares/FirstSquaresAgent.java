package agents.examples.squares;

import agents.Action;
import agents.AgentProgram;
import agents.Percept;

import java.util.HashMap;

class FirstSquaresAgent implements AgentProgram {

    protected String color;
    private int size;
    private int maxDepth;
    private HashMap<Integer, Action> actions;

    FirstSquaresAgent(String color, int depth) {
        maxDepth = depth;
        this.color = color;
        actions = new HashMap<>();
    }

    private boolean terminalState(Board board, int depth){
        return depth > maxDepth || board.full();
    }

    private int utility(Board board){
        int w = board.white_count();
        int b = board.black_count();
        if(color.equals(Squares.WHITE))
            return w-b;
        return b-w;
    }

    private Action alphaBetaSearch(Board board){
        int v = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        return actions.get(v);
    }

    private void act(int i, int j, String s, Board board){
        int side = 0;
        if (s.equals(Squares.LEFT)) side = Board.LEFT;
        if (s.equals(Squares.TOP)) side = Board.TOP;
        if (s.equals(Squares.RIGHT)) side = Board.RIGHT;
        if (s.equals(Squares.BOTTOM)) side = Board.BOTTOM;
        board.play(color.equals(Squares.WHITE), i, j, side);
    }

    private int maxValue(Board board, int alpha, int beta, int depth){
        if (terminalState(board, depth)) return utility(board);
        int v = Integer.MIN_VALUE;
        Board tmp;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(SquaresPercept.checkMove(i, j, Squares.LEFT, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.LEFT, tmp);
                    v = Math.max(v, minValue(tmp, alpha, beta, depth+1));
                    if(depth == 1)
                        actions.put(v, new Action(i + ":" + j + ":" + Squares.LEFT));
                    if(v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
                if(SquaresPercept.checkMove(i, j, Squares.RIGHT, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.RIGHT, tmp);
                    v = Math.max(v, minValue(tmp, alpha, beta, depth+1));
                    if(depth == 1)
                        actions.put(v, new Action(i + ":" + j + ":" + Squares.RIGHT));
                    if(v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
                if(SquaresPercept.checkMove(i, j, Squares.TOP, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.TOP, tmp);
                    v = Math.max(v, minValue(tmp, alpha, beta, depth+1));
                    if(depth == 1)
                        actions.put(v, new Action(i + ":" + j + ":" + Squares.TOP));
                    if(v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
                if(SquaresPercept.checkMove(i, j, Squares.BOTTOM, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.BOTTOM, tmp);
                    v = Math.max(v, minValue(tmp, alpha, beta, depth+1));
                    if(depth == 1)
                        actions.put(v, new Action(i + ":" + j + ":" + Squares.BOTTOM));
                    if(v >= beta)
                        return v;
                    alpha = Math.max(v, alpha);
                }
            }
        }
        return v;
    }

    private int minValue(Board board, int alpha, int beta, int depth){
        if (terminalState(board, depth)) return utility(board);
        int v = Integer.MAX_VALUE;
        Board tmp;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(SquaresPercept.checkMove(i, j, Squares.LEFT, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.LEFT, tmp);
                    v = Math.min(v, maxValue(tmp, alpha, beta, depth+1));
                    if(v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
                if(SquaresPercept.checkMove(i, j, Squares.RIGHT, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.RIGHT, tmp);
                    v = Math.min(v, maxValue(tmp, alpha, beta, depth+1));
                    if(v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
                if(SquaresPercept.checkMove(i, j, Squares.TOP, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.TOP, tmp);
                    v = Math.min(v, maxValue(tmp, alpha, beta, depth+1));
                    if(v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
                if(SquaresPercept.checkMove(i, j, Squares.BOTTOM, board).equals(Squares.FALSE)) {
                    tmp = new Board(board);
                    act(i, j, Squares.BOTTOM, tmp);
                    v = Math.min(v, maxValue(tmp, alpha, beta, depth+1));
                    if(v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
            }
        }
        return v;
    }

    @Override
    public Action compute(Percept percept){
        long time = (long) (200 * Math.random());
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
        if (percept.getAttribute(Squares.TURN).equals(color)) {
            size = Integer.parseInt((String) percept.getAttribute(Squares.SIZE));
            return alphaBetaSearch(((SquaresPercept) percept).board);
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init(){}
}
