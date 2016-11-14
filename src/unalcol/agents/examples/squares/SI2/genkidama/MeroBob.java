package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.examples.squares.Squares;
import unalcol.agents.examples.squares.SquaresPercept;

class MeroBob extends SquaresAnalyzer {

    MeroBob(SquaresFather father) { super(father); }

    @Override
    public Action play() {
        SquaresPercept percept = father.percept;
        String color = father.color;

        if ( percept.getAttribute(Squares.TURN).equals( color ) ) {
            int size = Integer.parseInt((String) percept.getAttribute(Squares.SIZE));
            int i, j;
            String a;
            while (true) {
                i = (int) ((size-1) * Math.random());
                j = (int) ((size-1) * Math.random());
                if ((percept.getAttribute(i + ":" + j + ":" + Squares.LEFT)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((percept.getAttribute(i + ":" + j + ":" + Squares.TOP)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((percept.getAttribute(i + ":" + j + ":" + Squares.BOTTOM)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((percept.getAttribute(i + ":" + j + ":" + Squares.RIGHT)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }

            }
            return new Action(i + ":" + j + ":" + a);
        }
        return new Action(Squares.PASS);
    }
}
