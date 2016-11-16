package unalcol.agents.examples.squares.SI2.genkidama;


import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;
import unalcol.agents.examples.squares.SquaresPercept;

abstract class SquaresFather implements AgentProgram {

    String color;
    private String time;
    int size, linesAssigned;
    SquaresPercept percept;
    SimulatedBoard board;
    boolean activateMiniMax;

    SquaresFather(String color) { this.color = color; }

    abstract Action play();

    void act(SimulatedBoard b, int i, int j, int side, boolean turn) {
        boolean tmp = color.equals( Squares.WHITE );
        b.play( turn == tmp, i, j, side);
    }

    void updateBoard(){
        linesAssigned = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ( percept.getAttribute(i + ":" + j + ":" + Squares.RIGHT ).equals( Squares.TRUE ) ){
                    linesAssigned++;
                    if ( ( board.values[i][j] & SimulatedBoard.RIGHT ) != SimulatedBoard.RIGHT )
                        act( board, i, j, SimulatedBoard.RIGHT, false );
                }

                if ( percept.getAttribute(i + ":" + j + ":" + Squares.BOTTOM ).equals( Squares.TRUE ) ) {
                    linesAssigned++;
                    if ( ( board.values[i][j] & SimulatedBoard.BOTTOM ) != SimulatedBoard.BOTTOM )
                        act( board, i, j, SimulatedBoard.BOTTOM, false );
                }
            }
        }
        linesAssigned -= 2*size;
    }

    @Override
    public Action compute( Percept p ) {
        if (p.getAttribute(Squares.TURN).equals(color)) {
            int size2 = Integer.parseInt((String) p.getAttribute(Squares.SIZE));

            String s = ( color.equals(Squares.WHITE) ? Squares.WHITE : Squares.BLACK ) + "_" + Squares.TIME, s2;
            s2 = (String) p.getAttribute(s);

            if( time == null){
                time = s2;
                board = new SimulatedBoard(size);
                activateMiniMax = false;
            }
            else if( time.compareTo(s2) >= 0 )
                time = s2;
            else {
                board = new SimulatedBoard(size);
                activateMiniMax = false;
            }

            if (size2 != size) {
                size = size2;
                board = new SimulatedBoard(size);
                activateMiniMax = false;
            }

            percept = (SquaresPercept) p;

            Action action = play();
            if (action != null)
                return action;
            if( !activateMiniMax ) {
                activateMiniMax = true;
                action = play();
                if (action != null)
                    return action;
            }
            return new Action(0 + ":" + 0 + ":" + Squares.RIGHT);
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {}
}
