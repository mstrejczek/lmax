package lmax.model;

public class PaddedBoolean {

    public long p1, p2, p3, p4, p5, p6, p7; // cache line padding
    private volatile boolean value = false;
    public long p8, p9, p10, p11, p12, p13, p14; // cache line padding

    public long sumPadding() {
	return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14;
    }

    public boolean isValue() {
	return value;
    }

    public void setTrue() {
	value = true;
    }
}
