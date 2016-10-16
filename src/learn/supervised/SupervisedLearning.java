/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.supervised;

import learn.Recognizer;
import algorithm.Algorithm;
import types.collection.Collection;

/**
 * @author jgomez
 */
public abstract class SupervisedLearning<T> extends
        Algorithm<Collection<LabeledObject<T>>, Recognizer<T>> {
}
