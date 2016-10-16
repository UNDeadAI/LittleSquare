package search.local;

import search.Goal;
import search.solution.Solution;
import search.space.Space;
import search.variation.Variation_1_1;
import tracer.Tracer;

public class AdaptOperatorLocalSearch<T, P> extends VariationReplaceLocalSearch<T> {
    protected AdaptSearchOperatorParameters<P> adapt;


    public AdaptOperatorLocalSearch(Variation_1_1<T> variation,
                                    AdaptSearchOperatorParameters<P> adapt,
                                    Replacement<T> replace) {
        super(variation, replace);
        this.adapt = adapt;
    }

    @Override
    public Solution<T> apply(Solution<T> x, Space<T> space) {
        // Check if non stationary
        Double fx = (Double) x.info(Goal.class.getName());
        Solution<T> y = variation.apply(space, x);
        y.set(Goal.class.getName(), x.data(Goal.class.getName()));
        Double fy = (Double) y.info(Goal.class.getName());
        if (adapt != null) adapt.apply(variation, fx, fy);
        Solution<T> z = replace.apply(x, y);
        Tracer.trace(Solution.class, x, z);
        return z;
    }

}
