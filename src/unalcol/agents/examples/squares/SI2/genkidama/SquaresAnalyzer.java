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
}
