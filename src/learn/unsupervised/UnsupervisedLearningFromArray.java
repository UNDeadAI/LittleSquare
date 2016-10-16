/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.unsupervised;

import learn.Recognizer;
import types.collection.Collection;
import types.collection.array.ArrayCollection;

/**
 * @author jgomez
 */
public abstract class UnsupervisedLearningFromArray<T> extends
        UnsupervisedLearning<T> {

    public abstract Recognizer<T> apply(ArrayCollection<T> x);

    @Override
    public Recognizer<T> apply(Collection<T> x) {
        if (x instanceof ArrayCollection) {
            return apply((ArrayCollection<T>) x);
        }
        return null;
    }

}
