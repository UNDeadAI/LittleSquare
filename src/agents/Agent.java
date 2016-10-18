package agents;

/**
 * <p>Title: Agent</p>
 * <p>
 * <p>Description: Abstract definition of an agent (it can be run in a thread!!)</p>
 * <p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Agent implements Runnable {

    //Thread used by the agent
    protected Thread thread = null;

    //Component used for visualizing the agent in the environment
    private Visualizer visualizer = null;

    //Status of the execution of the agent. It can be DIE, ABORT or CONTINUE
    public int status = Action.CONTINUE;

    private AgentProgram program = null;

    private AgentArchitecture architecture = null;

    public Agent(AgentArchitecture _architecture, AgentProgram _program) {
        program = _program;
        architecture = _architecture;
    }

    public Agent(AgentArchitecture _architecture) {
        architecture = _architecture;
    }

    public Agent(AgentProgram _program) {
        program = _program;
    }

    public void setProgram(AgentProgram _program) {
        program = _program;
    }

    public AgentProgram getProgram() { return program; }

    public void run() {
        while (status != Action.DIE) {
            status = Action.CONTINUE;
            Percept p = architecture.sense(this);
            if (visualizer != null)
                visualizer.show(this, p);
            Action action = program.compute(p);
            if (status != Action.ABORT) {
                try {
                    architecture.act(this, action);
                }
                catch (Exception ignored){}
            }
        }
    }

    public void init() {
        program.init();
    }

    public void die() {
        status = Action.DIE;
    }

    public void live() {
        status = Action.CONTINUE;
    }

    public void setThread(Thread _thread) {
        thread = _thread;
    }

    public void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception ignored) {
        }
    }
}
