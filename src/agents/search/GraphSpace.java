package agents.search;

import agents.*;
import search.space.Space;
import types.collection.vector.*;

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
public abstract class GraphSpace<T> extends Space<T> {
    public abstract T succesor(T state, Action action);

    public abstract Vector<Action> succesor(T state);
}
