/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search.local;

import search.Goal;
import search.solution.Solution;
import search.space.Space;
import search.variation.Variation_1_1;
import tracer.Tracer;

/**
 * @author jgomez
 */
public class VariationReplaceLocalSearch<T> extends LocalSearch<T, Double> {
    protected Variation_1_1<T> variation;
    protected Replacement<T> replace;

    public VariationReplaceLocalSearch(Variation_1_1<T> variation, Replacement<T> replace) {
        super();
        this.variation = variation;
        this.replace = replace;
    }

    @Override
    public Solution<T> apply(Solution<T> x, Space<T> space) {
        // Check if non stationary
        Solution<T> y = variation.apply(space, x);
        y.set(Goal.class.getName(), x.data(Goal.class.getName()));
        Solution<T> z = replace.apply(x, y);
        Tracer.trace(Solution.class, z, PathTracer.PARENT, z);
        return z;
    }
}