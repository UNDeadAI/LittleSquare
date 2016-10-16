/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.unsupervised.agglomerative.rica;

import java.io.*;

import data.plaintext.RealVectorFile;
import dynamic.rain.*;
import dynamic.rain.interactionfunction.GravityLaw;
import types.collection.vector.Vector;
import types.real.array.RealVectorLinealScale;

/**
 * @author jgomez
 */
public class RicaTest {
    public static void main(String[] args) {
        try {
            String fileName = "/home/jgomez/Repository/data/chameleon/t7.10k.dat";
            Vector<double[]> v = RealVectorFile.load(fileName, ' ');
            RealVectorLinealScale scale = new RealVectorLinealScale(v);
            scale.fastApply(v);
            double EPSILON = 0.0001;
            double dmm = 1.0;
            int D = 3;
            double G = 1.33514404296875E-5;
            double G_scale = 0.9;
            GravityLaw gravity = new GravityLaw(G, D, dmm, G_scale);
            EuclideanMove move = new EuclideanMove(gravity, EPSILON);
            PickOne<double[]> pick = new SimplestPickOne();
            RainStopPredicate predicate = new RainStopPredicate();
            Rica<double[]> rica = new Rica(move, predicate, pick);
            int[] label = rica.label(v);
            FileWriter w = new FileWriter("/home/jgomez/Repository/data/rgc.txt");
            for (int i = 0; i < label.length; i++) {
                w.write("" + label[i] + "\n");
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
