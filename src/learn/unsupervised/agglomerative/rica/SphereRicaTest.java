/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.unsupervised.agglomerative.rica;

import java.io.FileWriter;

import data.plaintext.SparseRealVectorFile;
import dynamic.rain.PickOne;
import dynamic.rain.RainStopPredicate;
import dynamic.rain.SimplestPickOne;
import dynamic.rain.SparseSphereMove;
import dynamic.rain.interactionfunction.GravityLaw;
import types.collection.vector.Vector;
import types.real.array.sparse.SparseRealVector;
import types.real.array.sparse.SparseRealVectorSphereNormalization;

/**
 * @author jgomez
 */
public class SphereRicaTest {
    public static void main(String[] args) {
        try {
            String fileName = "/home/jgomez/Repository/data/misc/datasets/tr11.mat";
            Vector<SparseRealVector> v = SparseRealVectorFile.load(fileName, ' ');
            SparseRealVectorSphereNormalization scale = new SparseRealVectorSphereNormalization();
            scale.fastApply(v);
            int D = v.get(0).dim();
            int N = v.size();
            PickOne<SparseRealVector> pick = new SimplestPickOne();
            double EPSILON = 0.7;
            double dmm = 1.0; //(new PyramidalMinMaxDistance()).compute(N, D-1);
            double G = 10.6875;
            double G_scale = 0.99;
            int ITERS = 30;
            GravityLaw gravity = new GravityLaw(G, 3, dmm, G_scale);
            SparseSphereMove move = new SparseSphereMove(gravity, EPSILON);
            //NoMove<SparseRealVector> move = new NoMove(new SparseRealVectorCosineSimilarity(), 0.1); 
            RainStopPredicate predicate = new RainStopPredicate();
            Rica<SparseRealVector> rica = new Rica(move, predicate, pick);
            int[] label = rica.label(v);
            FileWriter w = new FileWriter("/home/jgomez/Repository/data/srgc.txt");
            for (int i = 0; i < label.length; i++) {
                w.write("" + label[i] + "\n");
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
