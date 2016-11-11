package unalcol.agents.examples.squares.SI2.genkidama;


import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;
import unalcol.agents.examples.squares.SquaresPercept;

public abstract class SquaresFather implements AgentProgram {

    String color, time;
    int size;
    SquaresPercept percept;
    SimulatedBoard board;

    abstract Action play();

    int utility(SimulatedBoard board) {
        int w = board.white_count();
        int b = board.black_count();
        if (color.equals(Squares.WHITE))
            return w - b;
        return b - w;
    }

    void act(SimulatedBoard b, int i, int j, int side, boolean turn) {
        b.play(color.equals(Squares.WHITE) & turn, i, j, side);
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

    void updateBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ( percept.getAttribute(i + ":" + j + ":" + Squares.RIGHT).equals(Squares.TRUE) &&
                        !( ( board.values[i][j] & SimulatedBoard.RIGHT ) == SimulatedBoard.RIGHT ) )
                    act( board, i, j, SimulatedBoard.RIGHT, true );

                if ( percept.getAttribute(i + ":" + j + ":" + Squares.BOTTOM).equals(Squares.TRUE) &&
                        !( ( board.values[i][j] & SimulatedBoard.BOTTOM ) == SimulatedBoard.BOTTOM ) )
                    act( board, i, j, SimulatedBoard.BOTTOM, true );
            }
        }
    }

    @Override
    public Action compute(Percept p) {
        if (p.getAttribute(Squares.TURN).equals(color)) {
            int size2 = Integer.parseInt((String) p.getAttribute(Squares.SIZE));

            String s = (color.equals(Squares.WHITE) ? Squares.WHITE : Squares.BLACK) + "_" + Squares.TIME, s2;
            s2 = (String) p.getAttribute(s);

            if( time == null){
                time = s2;
                board = new SimulatedBoard(size);
            }
            else if( time.compareTo(s2) >= 0 )
                time = s2;
            else
                board = new SimulatedBoard(size);

            if (size2 != size) {
                size = size2;
                board = new SimulatedBoard(size);
            }

            percept = (SquaresPercept) p;

            return play();
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {}
}
