package unalcol.agents.examples.squares.SI2.genkidama;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;

public class Dummy2Agent implements AgentProgram {

    protected String color;

    public Dummy2Agent(String color) {
        this.color = color;
    }

    @Override
    public Action compute(Percept p) {
        if (p.getAttribute(Squares.TURN).equals(color)) {
            int size = Integer.parseInt((String) p.getAttribute(Squares.SIZE));
            int i, j;
            String a;
            while (true) {
                i = (int) ((size-1) * Math.random());
                j = (int) ((size-1) * Math.random());
                if ((p.getAttribute(i + ":" + j + ":" + Squares.LEFT)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((p.getAttribute(i + ":" + j + ":" + Squares.TOP)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((p.getAttribute(i + ":" + j + ":" + Squares.BOTTOM)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }
                if ((p.getAttribute(i + ":" + j + ":" + Squares.RIGHT)).equals(Squares.FALSE)) {
                    a = Squares.LEFT;
                    break;
                }

            }
            return new Action(i + ":" + j + ":" + a);
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {}
}
