/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimization.method.hillclimbing;

import search.Goal;
import search.RealQualityGoal;
import search.local.Replacement;
import search.solution.Solution;
import sort.Order;

/**
 * @author jgomez
 */
public class HillClimbingReplacement<T> implements Replacement<T> {
    protected boolean neutral = false;

    public HillClimbingReplacement() {
    }

    public HillClimbingReplacement(boolean neutral) {
        this.neutral = neutral;
    }

    @Override
    public Solution<T> apply(Solution<T> current, Solution<T> next) {
        String gName = Goal.class.getName();
        double qc = (Double) current.info(gName);
        double qn = (Double) next.info(gName);
        @SuppressWarnings("unchecked")
        RealQualityGoal<T> goal = (RealQualityGoal<T>) current.data(gName);
        Order<Double> order = goal.order();
        if (neutral)
            return order.compare(qc, qn) <= 0 ? next : current;
        else
            return order.compare(qc, qn) < 0 ? next : current;
    }

    @Override
    public void init() {
    }
}