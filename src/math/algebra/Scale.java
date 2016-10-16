/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math.algebra;

import java.util.Iterator;

import clone.Clone;
import types.collection.array.ArrayCollection;
import types.collection.array.MutableArrayCollection;
import types.collection.vector.Vector;


/**
 * @author jgomez
 */
public abstract class Scale<T> {
    public abstract T fastApply(T x);

    @SuppressWarnings("unchecked")
    public T apply(T x) {
        return fastApply((T) Clone.create(x));
    }

    public Vector<T> apply(ArrayCollection<T> a) {
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while (iter.hasNext()) {
            v.add(apply(iter.next()));
        }
        return v;
    }

    public void fastApply(MutableArrayCollection<T> a) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, fastApply(a.get(i)));
        }
    }
}
