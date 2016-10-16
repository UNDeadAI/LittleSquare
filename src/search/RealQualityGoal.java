package search;

import math.metric.DistanceOrder;
import sort.Order;
import types.real.Abs;

public abstract class RealQualityGoal<T> implements Goal<T, Double> {
    protected double goal_value;

    protected Order<Double> order = new DistanceOrder<Double>(new Abs(), goal_value);

    public RealQualityGoal(double goal_value) {
        this.goal_value = goal_value;
    }

    public Order<Double> order() {
        return order;
    }
}