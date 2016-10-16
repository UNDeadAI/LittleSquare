package agents.simulate.gui;


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
public interface EnvironmentView {
    static final String DONE = "Done";
    static final String ERROR = "Error";

    void envChanged(String command);
}
