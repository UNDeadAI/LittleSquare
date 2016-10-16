/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package types.real.array;

import math.algebra.Scale;

/**
 * @author jgomez
 */
public class RealVectorSphereNormalization extends Scale<double[]> {
    protected DoubleArrayDotProduct dot = new DoubleArrayDotProduct();
    protected RealVectorSpace scale = new RealVectorSpace();

    /**
     * Applies the transformation on the data record
     *
     * @param x Data record to be transformed
     */
    @Override
    public double[] fastApply(double[] x) {
        double d = dot.norm(x);
        scale.fastDivide(x, d);
        return x;
    }
}
