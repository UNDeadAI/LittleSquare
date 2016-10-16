package types.collection.vector;

import types.collection.*;

import java.util.NoSuchElementException;

/**
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class VectorIterator<T> implements Iterator<T> {
    protected int pos;
    protected ImmutableVector<T> vector;

    public VectorIterator(int pos, ImmutableVector<T> vector) {
        this.vector = vector;
        this.pos = pos;
    }

    public VectorIterator(VectorLocation<T> location) {
        this.vector = location.vector;
        this.pos = location.pos;
    }

    public boolean hasNext() {
        return pos < vector.size;
    }

    public T next() throws NoSuchElementException {
        try {
            T d = vector.buffer[pos];
            pos++;
            return d;
        } catch (Exception e) {
            throw new NoSuchElementException("" + pos);
        }
    }
}