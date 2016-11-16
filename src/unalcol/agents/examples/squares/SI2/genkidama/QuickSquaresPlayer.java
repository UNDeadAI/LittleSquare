package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.examples.squares.Squares;

class QuickSquaresPlayer extends SquaresAnalyzer {

    private int size;
    private SimulatedBoard board;

    QuickSquaresPlayer(SquaresFather father ) {
        super(father);
    }

    @Override
    Action play() {
        size = father.size;
        board = father.board;
        return quickPlay();
    }

    private Action quickPlay(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ( board.lines(i, j) < 2 ) {

                    if ( ( board.values[i][j] & SimulatedBoard.RIGHT) != SimulatedBoard.RIGHT ) {
                        if(j+1<size && ( board.values[i][j+1] & SimulatedBoard.LEFT) != SimulatedBoard.LEFT &&
                                board.lines(i, j+1) < 2)
                            return new Action(i + ":" + j + ":" + Squares.RIGHT);
                    }

                    if ( ( board.values[i][j] & SimulatedBoard.BOTTOM) != SimulatedBoard.BOTTOM ) {
                        if(i+1<size && ( board.values[i+1][j] & SimulatedBoard.TOP) != SimulatedBoard.TOP &&
                                board.lines(i+1, j) < 2)
                            return new Action(i + ":" + j + ":" + Squares.BOTTOM);
                    }

                    if ( ( board.values[i][j] & SimulatedBoard.LEFT) != SimulatedBoard.LEFT ) {
                        if(j-1>=0 && ( board.values[i][j-1] & SimulatedBoard.RIGHT) != SimulatedBoard.RIGHT &&
                                board.lines(i, j-1) < 2)
                            return new Action(i + ":" + j + ":" + Squares.LEFT);
                    }

                    if ( ( board.values[i][j] & SimulatedBoard.TOP) != SimulatedBoard.TOP ) {
                        if(i-1>=0 && ( board.values[i-1][j] & SimulatedBoard.BOTTOM) != SimulatedBoard.BOTTOM &&
                                board.lines(i-1, j) < 2)
                            return new Action(i + ":" + j + ":" + Squares.TOP);
                    }
                }
            }
        }
        return null;
    }
}
