package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.examples.squares.Squares;

abstract class SquaresAnalyzer {

    SquaresFather father;

    abstract Action play();

    SquaresAnalyzer(SquaresFather father) {
        this.father = father;
    }

    int utility(SimulatedBoard board) {
        int w = board.white_count();
        int b = board.black_count();
        if ( father.color.equals( Squares.WHITE ) )
            return w - b;
        return b - w;
    }

    void act2(boolean whiteTurn, String action, SimulatedBoard b) {
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
}
