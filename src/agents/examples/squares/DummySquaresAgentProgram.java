package agents.examples.squares;

import agents.Action;
import agents.AgentProgram;
import agents.Percept;
import types.collection.vector.Vector;

/**
 * @author Jonatan
 */
class DummySquaresAgentProgram implements AgentProgram {

    protected String color;

    DummySquaresAgentProgram(String color) {
        this.color = color;
    }

    @Override
    public Action compute(Percept p) {
        long time = (long) (200 * Math.random());
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
        if (p.getAttribute(Squares.TURN).equals(color)) {
            int size = Integer.parseInt((String) p.getAttribute(Squares.SIZE));
            int i = 0;
            int j = 0;
            Vector<String> v = new Vector<>();
            while (v.size() == 0) {
                i = (int) (size * Math.random());
                j = (int) (size * Math.random());
                if (p.getAttribute(i + ":" + j + ":" + Squares.LEFT).equals(Squares.FALSE))
                    v.add(Squares.LEFT);
                if (p.getAttribute(i + ":" + j + ":" + Squares.TOP).equals(Squares.FALSE))
                    v.add(Squares.TOP);
                if (p.getAttribute(i + ":" + j + ":" + Squares.BOTTOM).equals(Squares.FALSE))
                    v.add(Squares.BOTTOM);
                if (p.getAttribute(i + ":" + j + ":" + Squares.RIGHT).equals(Squares.FALSE))
                    v.add(Squares.RIGHT);
            }
            return new Action(i + ":" + j + ":" + v.get((int) (Math.random() * v.size())));
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {}
}

