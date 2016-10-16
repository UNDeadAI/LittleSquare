package optimization.binary;

import optimization.binary.varlength.VarLengthBinarySpace;

public class BinarySpace extends VarLengthBinarySpace {
    public BinarySpace(int n) {
        super(n, n);
    }
}
