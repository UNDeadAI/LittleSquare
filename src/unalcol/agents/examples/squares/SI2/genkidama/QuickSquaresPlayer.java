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
        SimulatedBoard tmp;
        Action noOption = new Action(0 + ":" + 0 + ":" + Squares.RIGHT);
        int maxV = Integer.MIN_VALUE, aux;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if ( ( board.values[i][j] & SimulatedBoard.RIGHT) != SimulatedBoard.RIGHT ) {
                    tmp = new SimulatedBoard( board );
                    father.act( tmp, i, j, SimulatedBoard.RIGHT, true );
                    aux = utility( tmp );
                    if ( aux >= 0 )
                        return new Action( i + ":" + j + ":" + Squares.RIGHT );
                    if ( aux > maxV ){
                        maxV = aux;
                        noOption = new Action( i + ":" + j + ":" + Squares.RIGHT );
                    }
                }

                if ( ( board.values[i][j] & SimulatedBoard.BOTTOM ) != SimulatedBoard.BOTTOM ) {
                    tmp = new SimulatedBoard( board );
                    father.act( tmp, i, j, SimulatedBoard.BOTTOM, true );
                    aux = utility( tmp );
                    if ( aux >= 0 )
                        return new Action( i + ":" + j + ":" + Squares.BOTTOM );
                    if ( aux > maxV){
                        maxV = aux;
                        noOption = new Action( i + ":" + j + ":" + Squares.BOTTOM );
                    }
                }
            }
        }
        return noOption;
    }
}
