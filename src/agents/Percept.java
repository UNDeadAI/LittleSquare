package agents;

import java.util.Hashtable;

/**
 * <p>Title: Percept</p>
 * <p>
 * <p>Description: Abstract definition of a perception</p>
 * <p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Percept {
    private Hashtable<String, Object> table = new Hashtable<>();

    public Object getAttribute(String code) {
        return table.get(code);
    }

    public void setAttribute(String key, Object value) {
        table.put(key, value);
    }
}
