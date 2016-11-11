package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.examples.squares.Squares;

import java.util.HashMap;

public class MiniMaxPlayer extends SquaresFather {

    private int maxDepth;
    private HashMap<Integer, Action> actions;

    public MiniMaxPlayer(String color, int depth) {
        maxDepth = depth;
        this.color = color;
    }

    private boolean terminalState(int depth, SimulatedBoard board) {
        return depth > maxDepth || board.full();
    }

    @Override
    Action play() {
        updateBoard();
        return alphaBetaSearch();
    }

    private Action alphaBetaSearch() {
        actions = new HashMap<>();
        int v = maxValue( Integer.MIN_VALUE, Integer.MAX_VALUE, board, 1 );
        Action a = actions.get( v );
        act2( color.equals(Squares.WHITE), a.getCode(), board );
        return a;
    }

    private int maxValue(int alpha, int beta, SimulatedBoard board, int depth) {
        if ( terminalState( depth, board ) ) return utility( board );
        int v = Integer.MIN_VALUE, tmp2;
        SimulatedBoard tmp;
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {

                if ( !( ( board.values[i][j] & SimulatedBoard.RIGHT) == SimulatedBoard.RIGHT ) ) {
                    tmp = new SimulatedBoard(board);
                    act( tmp, i, j, SimulatedBoard.RIGHT, true );
                    tmp2 = minValue( alpha, beta, tmp, depth + 1 );
                    if (depth == 1)
                        actions.put( tmp2, new Action( i + ":" + j + ":" + Squares.RIGHT ) );
                    v = Math.max( v, tmp2 );
                    if (v >= beta)
                        return v;
                    alpha = Math.max( v, alpha );
                }

                if ( !( ( board.values[i][j] & SimulatedBoard.BOTTOM) == SimulatedBoard.BOTTOM ) ) {
                    tmp = new SimulatedBoard(board);
                    act( tmp, i, j, SimulatedBoard.BOTTOM, true );
                    tmp2 = minValue(alpha, beta, tmp, depth + 1);
                    if (depth == 1)
                        actions.put( tmp2, new Action( i + ":" + j + ":" + Squares.BOTTOM ) );
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
        if ( terminalState( depth, board ) ) return utility(board);
        int v = Integer.MAX_VALUE;
        SimulatedBoard tmp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if ( !( ( board.values[i][j] & SimulatedBoard.RIGHT) == SimulatedBoard.RIGHT ) ) {
                    tmp = new SimulatedBoard(board);
                    act( tmp, i, j, SimulatedBoard.RIGHT, false );
                    v = Math.min(v, maxValue(alpha, beta, tmp, depth + 1));
                    if (v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }

                if ( !( ( board.values[i][j] & SimulatedBoard.BOTTOM) == SimulatedBoard.BOTTOM ) ) {
                    tmp = new SimulatedBoard(board);
                    act( tmp, i, j, SimulatedBoard.BOTTOM, false );
                    v = Math.min(v, maxValue(alpha, beta, tmp, depth + 1));
                    if (v <= alpha)
                        return v;
                    beta = Math.min(v, beta);
                }
            }
        }
        return v;
    }
}
