package optimization.method;

import algorithm.iterative.ForLoopCondition;
import math.logic.Predicate;
import optimization.method.annealing.SimpleSimulatedAnnealingScheme;
import optimization.method.annealing.SimulatedAnnealingReplacement;
import optimization.method.annealing.SimulatedAnnealingScheme;
import optimization.method.hillclimbing.HillClimbingReplacement;
import search.local.IterativeLocalSearch;
import search.local.LocalSearch;
import search.local.VariationReplaceLocalSearch;
import search.solution.Solution;
import search.variation.Variation_1_1;

public class OptimizationFactory<T> {
    public LocalSearch<T, Double>
    hill_climbing(
            Variation_1_1<T> variation,
            HillClimbingReplacement<T> replace,
            Predicate<Solution<T>> tC) {
        return new IterativeLocalSearch<T, Double>(new VariationReplaceLocalSearch<T>(variation, replace), tC);
    }

    public LocalSearch<T, Double>
    hill_climbing(
            Variation_1_1<T> variation,
            boolean neutral, int MAX_ITERS) {
        return hill_climbing(variation,
                new HillClimbingReplacement<T>(neutral),
                new ForLoopCondition<Solution<T>>(MAX_ITERS));
    }

    public LocalSearch<T, Double>
    simulated_annealing(
            Variation_1_1<T> variation,
            SimulatedAnnealingReplacement<T> replace,
            Predicate<Solution<T>> tC) {
        return new IterativeLocalSearch<T, Double>(
                new VariationReplaceLocalSearch<T>(variation, replace), tC);
    }

    public LocalSearch<T, Double>
    simulated_annealing(
            Variation_1_1<T> variation,
            SimulatedAnnealingScheme scheme, int MAX_ITERS) {
        return simulated_annealing(variation,
                new SimulatedAnnealingReplacement<T>(scheme),
                new ForLoopCondition<Solution<T>>(MAX_ITERS));
    }

    public LocalSearch<T, Double>
    simulated_annealing(
            Variation_1_1<T> variation,
            int K, int MAX_ITERS) {
        return simulated_annealing(variation,
                new SimpleSimulatedAnnealingScheme(K),
                MAX_ITERS);
    }

    public LocalSearch<T, Double> simulated_annealing(
            Variation_1_1<T> variation, int MAX_ITERS) {
        return simulated_annealing(variation, MAX_ITERS, MAX_ITERS);
    }
}