/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math.metric;

/**
 * @author jgomez
 */
public interface Simmilarity<T> extends QuasiMetric<T> {
    public double max(T x);
}