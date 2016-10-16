/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package types.real.array;

import math.metric.MetricVectorSpace;
import math.metric.QuasiMetric;

/**
 * @author jgomez
 */
public class MetricRealVectorSpace extends RealVectorSpace
        implements MetricVectorSpace<double[]> {
    protected QuasiMetric<double[]> metric;

    public MetricRealVectorSpace(QuasiMetric<double[]> metric) {
        this.metric = metric;
    }

    @Override
    public double apply(double[] x, double[] y) {
        return metric.apply(x, y);
    }
}
