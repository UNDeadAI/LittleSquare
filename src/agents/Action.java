package agents;

/**
 * <p>Title: Action </p>
 * <p>
 * <p>Description: Abstract definiton of an action</p>
 * <p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Action {

    public static final int DIE = 0;

    public static final int ABORT = 1;

    public static final int CONTINUE = 2;

    protected String code;

    public Action(String _code) {
        code = _code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public Action clone() {
        return new Action(code);
    }
}
