package agents.simulate;

import agents.*;

import java.util.Hashtable;

/**
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class SimulatedAgent extends Agent {
    protected Hashtable<String, Object> properties = new Hashtable<>();

    public SimulatedAgent(AgentArchitecture _architecture, AgentProgram _program) {
        super(_architecture, _program);
    }

    public void setAttribute(String key, Object value) {
        properties.put(key, value);
    }

    public Object getAttribute(String key) {
        return properties.get(key);
    }
}