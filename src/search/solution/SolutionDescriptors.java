package search.solution;

import descriptors.Descriptors;
import search.Goal;

public class SolutionDescriptors<T> extends Descriptors<Solution<T>> {

    @Override
    public double[] descriptors(Solution<T> sol) {
        return new double[]{(Double) sol.info(Goal.class.getName())};
    }
}
