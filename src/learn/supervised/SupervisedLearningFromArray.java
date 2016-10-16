/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.supervised;

import learn.Recognizer;
import types.collection.Collection;
import types.collection.array.ArrayCollection;

/**
 * @author jgomez
 */
public abstract class SupervisedLearningFromArray<T> extends
        SupervisedLearning<T> {

    public abstract Recognizer<T> apply(ArrayCollection<LabeledObject<T>> x);

    @Override
    public Recognizer<T> apply(Collection<LabeledObject<T>> x) {
        if (x instanceof ArrayCollection) {
            return apply((ArrayCollection<LabeledObject<T>>) x);
        }
        return null;
    }

}
