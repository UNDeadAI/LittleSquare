package agents.examples.labyrinth.variable;

import agents.Action;
import agents.AgentProgram;
import agents.Percept;
import random.raw.RawGenerator;

public class WallDaemon implements AgentProgram {
    protected double probability;

    public WallDaemon(double p) {
        probability = p;
    }

    @Override
    public Action compute(Percept p) {
        if (RawGenerator.next(this) < probability) {
            return new Action("change_walls");
        }
        return new Action("no_op");
    }

    @Override
    public void init() {
    }
}