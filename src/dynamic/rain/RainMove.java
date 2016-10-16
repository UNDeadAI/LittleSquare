/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic.rain;

import types.collection.vector.Vector;

/**
 * @author jgomez
 */
public interface RainMove<T> {
    public boolean apply(Vector<T> set, int i, int j);

    public void update();
}
