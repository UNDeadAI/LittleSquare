/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import search.solution.Solution;
import search.solution.SolutionManager;
import search.space.Space;
import search.space.SpaceSampler;

/**
 * @author jgomez
 */
public interface Search<T, R> extends SpaceSampler<T>, SolutionManager<T> {
    public Solution<T> solve(Space<T> space, Goal<T, R> goal);

    @Override
    public default T apply(Space<T> space) {
        @SuppressWarnings("unchecked")
        Goal<T, R> goal = (Goal<T, R>) space.data(Goal.class.getName());
        if (goal != null) {
            return unwrap(solve(space, goal));
        }
        return null;
    }
}