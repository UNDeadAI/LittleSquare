package agents.simulate;

import agents.*;
import types.collection.vector.*;
import agents.simulate.gui.*;

/**
 * <p>Title: Environment </p>
 * <p>
 * <p>Description: The environment for the given agents problem </p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class Environment extends Kernel implements AgentArchitecture {
    protected long delay = 0;
    private Vector<EnvironmentView> views = new Vector<>();

    public Environment(Agent agent) {
        super(agent);
        int n = agents.size();
        for (int i = 0; i < n; i++) {
            agents.set(i, new SimulatedAgent(this, agents.get(i).getProgram()));
        }
    }

    public Environment(Vector<Agent> _agents) {
        super(_agents);
        int n = agents.size();
        for (int i = 0; i < n; i++)
            agents.set(i, new SimulatedAgent(this, agents.get(i).getProgram()));
    }

    public void setDelay(long _delay) {
        delay = _delay;
    }

    public void registerView(EnvironmentView view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    protected void updateViews(String message) {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).envChanged(message);
        }
    }

    public int agentsNumber() {
        return agents.size();
    }
}
