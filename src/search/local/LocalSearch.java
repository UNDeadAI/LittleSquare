/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search.local;

import search.Goal;
import search.Search;
import search.solution.Solution;
import search.space.Space;
import tracer.Tracer;

/**
 * @author Jonatan
 */
public abstract class LocalSearch<T, R> implements Search<T, R> {

    public LocalSearch() {
    }

    public abstract Solution<T> apply(Solution<T> x, Space<T> space);

    @Override
    public Solution<T> solve(Space<T> space, Goal<T, R> goal) {
        Solution<T> x = new Solution<T>(space.pick());
        x.set(Goal.class.getName(), goal);
        Tracer.trace(Solution.class, x);
        return apply(x, space);
    }
}