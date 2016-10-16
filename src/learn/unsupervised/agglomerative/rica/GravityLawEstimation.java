/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.unsupervised.agglomerative.rica;

import dynamic.rain.AttractionMove;
import dynamic.rain.PickOne;
import math.metric.Distance;
import types.collection.array.ArrayCollection;

/**
 * @author jgomez
 */
public abstract class GravityLawEstimation<T> {
    protected int D;
    protected double dmm;
    protected double G_scale;
    protected PickOne<T> pick;

    public GravityLawEstimation(int D, double dmm, double G_scale, PickOne<T> pick) {
        this.D = D;
        this.dmm = dmm;
        this.G_scale = G_scale;
        this.pick = pick;
    }

    public abstract double G(ArrayCollection<T> set, AttractionMove<T> move);
}
