/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package random.real;

import random.raw.RawGenerator;

/**
 * @author jgomez
 */
public class PowerLawGenerator extends StandardPowerLawGenerator {
    double x_min = 1.0;

    public PowerLawGenerator(double _alpha, double _x_min) {
        super(_alpha);
        x_min = _x_min;
    }

    @Override
    public double next(double x) {
        return x_min * super.next(x);
    }

    @Override
    public DoubleGenerator new_instance() {
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new PowerLawGenerator(1.0 - 1.0 / coarse_alpha, x_min);
        RawGenerator.set(dg, g.new_instance());
        return dg;
    }

}
