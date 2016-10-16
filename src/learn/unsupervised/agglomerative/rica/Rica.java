/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.unsupervised.agglomerative.rica;

import clone.Clone;
import dynamic.DynamicSystem;
import dynamic.rain.PickOne;
import dynamic.rain.RainMove;
import dynamic.rain.RainSystem;
import learn.Labeler;
import math.logic.Predicate;
import types.collection.array.ArrayCollection;

/**
 * @author jgomez
 */
public class Rica<T> implements Labeler<T> {
    protected RainMove<T> move;
    protected PickOne<T> pick;
    protected Predicate<DynamicSystem> stop;

    public Rica(RainMove<T> move, Predicate<DynamicSystem> stop, PickOne<T> pick) {
        this.move = move;
        this.pick = pick;
        this.stop = stop;
    }

    @Override
    public int[] label(ArrayCollection<T> set) {
        @SuppressWarnings("unchecked")
        RainMove<T> m = (RainMove<T>) Clone.create(move);
        @SuppressWarnings("unchecked")
        PickOne<T> p = (PickOne<T>) Clone.create(pick);
        @SuppressWarnings("unchecked")
        Predicate<DynamicSystem> c = (Predicate<DynamicSystem>) Clone.create(stop);
        RainSystem<T> rain = new RainSystem<>(set, m, p);
        rain.simulate(c);
        return rain.structures();
    }

    @Override
    public int label(T obj) {
        // TODO Auto-generated method stub
        return 0;
    }

}
