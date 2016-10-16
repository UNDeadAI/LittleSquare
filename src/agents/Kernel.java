package agents;

import types.collection.vector.*;

/**
 * <p>Title: Kernel </p>
 * <p>
 * <p>Description: A multi agent problem solution kernel</p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Kernel implements Runnable {

    //Stop/Go checking flag
    public boolean flag = true;

    protected Vector<Agent> agents;

    public Kernel(Agent agent) {
        agents = new Vector<>();
        agents.add(agent);
    }

    public Kernel(Vector<Agent> _agents) {
        agents = _agents;
    }

    public Agent getAgent(int index) {
        return agents.get(index);
    }

    public Agent getAgent() {
        return agents.get(0);
    }

    public void stop() {
        flag = false;
        int n = agents.size();
        for (int i = 0; i < n; i++) {
            agents.get(i).die();
        }
    }

    public void run() {
        flag = true;
        Agent a;
        int n = agents.size();
        for (int i = 0; i < n && flag; i++) {
            a = agents.get(i);
            a.live();
            Thread t = new Thread(a);
            a.setThread(t);
            t.start();
        }
    }
}
