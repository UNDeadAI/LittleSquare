/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents.examples.squares;

import agents.Percept;
import agents.Clock;

/**
 * @author Jonatan
 */
public class SquaresPercept extends Percept {

    protected Board board;
    protected Clock clock;

    SquaresPercept(Board board, Clock clock) {
        this.board = board;
        this.clock = clock;
    }

    public static Object checkMove(int i, int j, String s, Board board){
        if (s.equals(Squares.LEFT))
            if ((board.values[i][j] & Board.LEFT) == Board.LEFT) return Squares.TRUE;
            else return Squares.FALSE;
        if (s.equals(Squares.TOP))
            if ((board.values[i][j] & Board.TOP) == Board.TOP) return Squares.TRUE;
            else return (Squares.FALSE);
        if (s.equals(Squares.RIGHT))
            if ((board.values[i][j] & Board.RIGHT) == Board.RIGHT) return Squares.TRUE;
            else return (Squares.FALSE);
        if (s.equals(Squares.BOTTOM))
            if ((board.values[i][j] & Board.BOTTOM) == Board.BOTTOM) return Squares.TRUE;
            else return (Squares.FALSE);
        if (board.lines(i, j) == 4) {
            if ((board.values[i][j] & Board.WHITE) == Board.WHITE) return Squares.WHITE;
            return Squares.BLACK;
        } else
            return Squares.SPACE;
    }

    public Object checkMove(String code, Board board){
        String[] v = code.split(":");
        int i = Integer.parseInt(v[0]);
        int j = Integer.parseInt(v[1]);
        return checkMove(i, j, v[2], board);
    }

    @Override
    public Object getAttribute(String code) {
        if (code.equals(Squares.TURN)) {
            if (clock.white_turn())
                return Squares.WHITE;
            else
                return Squares.BLACK;
        } else {
            if (code.equals(Squares.WHITE + "_" + Squares.TIME)) return clock.white_time_string();
            if (code.equals(Squares.BLACK + "_" + Squares.TIME)) return clock.black_time_string();
            if (code.equals(Squares.SIZE)) return "" + board.values.length;
            return checkMove(code, board);
        }
    }
}
