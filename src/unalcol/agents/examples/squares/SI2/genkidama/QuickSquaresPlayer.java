package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.examples.squares.Squares;

public class QuickSquaresPlayer extends SquaresFather {

    public QuickSquaresPlayer(String color) {
        this.color = color;
    }

    @Override
    Action play() { return quickPlay(); }

    private Action quickPlay(){
        SimulatedBoard tmp;
        String action;
        Action noOption = new Action(0 + ":" + 0 + ":" + Squares.RIGHT);
        int maxV = Integer.MIN_VALUE, aux;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                action = i + ":" + j + ":" + Squares.RIGHT;
                if ( !( ( board.values[i][j] & SimulatedBoard.RIGHT) == SimulatedBoard.RIGHT ) ) {
                    tmp = new SimulatedBoard( board );
                    act( color.equals(Squares.WHITE), action, tmp );
                    aux = utility( tmp );
                    if ( aux >= 0 )
                        return new Action(action);
                    if ( aux > maxV ){
                        maxV = aux;
                        noOption = new Action(action);
                    }
                }

                action = i + ":" + j + ":" + Squares.BOTTOM;
                if ( !( ( board.values[i][j] & SimulatedBoard.BOTTOM) == SimulatedBoard.BOTTOM ) ) {
                    tmp = new SimulatedBoard( board );
                    act( color.equals(Squares.WHITE), action, tmp );
                    aux = utility( tmp );
                    if ( aux >= 0 )
                        return new Action( action );
                    if ( aux > maxV){
                        maxV = aux;
                        noOption = new Action( action );
                    }
                }
            }
        }
        return noOption;
    }
}
