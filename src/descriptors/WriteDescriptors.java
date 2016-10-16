package descriptors;

import java.io.Writer;

import io.Write;

//
// Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe//tree/master/services/
//

/**
 * WriteDescriptors
 * <p>Writes the descriptors associated to a given object.</p>
 * <p>
 * <p>
 * <A HREF="https://github.com/jgomezpe//blob/master/src//descriptors/WriteDescriptors.java">
 * Source code </A> is available.
 * <p>
 * <h3>License</h3>
 * <p>
 * Copyright (c) 2014 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 * <p>
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 *         (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
public class WriteDescriptors extends Write<Object> {
    /**
     * Writes an object to the given <i>writer</i>.
     *
     * @param obj    Object to write
     * @param writer The writer object
     * @throws Exception An IOException
     */
    @Override
    public void write(Object obj, Writer writer) throws Exception {
        Write.apply(Descriptors.obtain(obj), writer);
    }
}