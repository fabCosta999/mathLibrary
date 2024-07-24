package FMath.Numbers;

import java.util.*;

public class BigInteger {
    private static int intMask = 0xFFFFFFFF;
    private static long longMask = 0xFFFFFFFF00000000L;
    
    private List<Integer> num = new ArrayList<>();

    public BigInteger() { 
        num.add(0); 
    }

    public BigInteger(long value) { 
        num.add((int) value & intMask); 
        num.add((int) (value & longMask) >> 32);
    }
}
