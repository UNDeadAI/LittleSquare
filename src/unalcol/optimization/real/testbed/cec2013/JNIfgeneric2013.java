/**
 * Copyright (c) 2013, Giovanni Iacca (giovanniiaccca@incas3.eu)
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package unalcol.optimization.real.testbed.cec2013;

/**
 * JNI connection class for interfacing the CEC 2013 LSGO C-functions.
 */
public class JNIfgeneric2013 {

    /** Load the library, the system-specific filename extension is added by the JVM. */
    static {
        try {
            ClassLoaderHelper.loadFolderFromJar("lib-cec-2013/cdatafiles/");
            ClassLoaderHelper.loadNativeLibraryFromJar("libcec2013lsgo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Native method declaration
    private native long initCEC2013(int funcId, int dim);

    private native void exitCEC2013(long fcnPointer);

    private native double evaluate(long fcnPointer, double[] X);

    private long fcnPointer;

    public JNIfgeneric2013(int funcId, int dim) {
        fcnPointer = initCEC2013(funcId, dim);
    }

    public void destroy() {
        exitCEC2013(fcnPointer);
    }

    public double evaluate(double[] x) {
        return evaluate(fcnPointer, x);
    }

}