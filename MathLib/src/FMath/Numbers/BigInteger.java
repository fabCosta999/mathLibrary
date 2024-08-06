package FMath.Numbers;

import java.util.*;

public class BigInteger implements FMathNumber<BigInteger> {
    private static int intMask = 0xFFFFFFFF;
    private static long longMask = 0xFFFFFFFF00000000L;
    
    private List<Integer> num = new ArrayList<>();
    private boolean isNegative;

    public BigInteger() { 
        num.add(0); 
        isNegative = false;
    }

    public BigInteger(long value) { 
        if (value >= 0L){
            isNegative = false;
        }
        else{
            value = -value;
            isNegative = true;
        }
        num.add((int) value & intMask); 
        if (value > 0xFFFFFFFFL) num.add((int) (value & longMask) >> 32);
    }

    public BigInteger(BigInteger n){
        num = new ArrayList<>(n.num);
        isNegative = n.isNegative;
    }

    private BigInteger(boolean isNegative){
        this.isNegative = isNegative;
    }

    public BigInteger opposite(){
        BigInteger n = new BigInteger(this);
        n.isNegative = isNegative ? false : true;
        return n;
    }

    public BigInteger magnitude(){
        BigInteger n = new BigInteger(this);
        n.isNegative = false;
        return n;
    }

    @Override
    public BigInteger add(BigInteger other) {
        if (isNegative == other.isNegative){
            BigInteger res = new BigInteger(isNegative);
            
            boolean carry = false;
            if (num.size() == other.num.size()){
                for (int i=0; i<num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(num.get(i));
                    long num2 = Integer.toUnsignedLong(other.num.get(i));
                    long sum = carry ? num1 + num2 + 1 : num1 + num2;
                    if (sum > 0xFFFFFFFFL) carry = true;
                    else carry = false;
                    res.num.add((int) sum);
                }
            }
            else if (num.size() > other.num.size()){
                for (int i=0; i<other.num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(num.get(i));
                    long num2 = Integer.toUnsignedLong(other.num.get(i));
                    long sum = carry ? num1 + num2 + 1 : num1 + num2;
                    if (sum > 0xFFFFFFFFL) carry = true;
                    else carry = false;
                    res.num.add((int) sum);
                }
                for (int i=other.num.size(); i<num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(num.get(i));
                    long sum = carry ? num1 + 1 : num1;
                    if (sum > 0xFFFFFFFFL) carry = true;
                    else carry = false;
                    res.num.add((int) sum);
                }
            }
            else{
                for (int i=0; i<num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(num.get(i));
                    long num2 = Integer.toUnsignedLong(other.num.get(i));
                    long sum = carry ? num1 + num2 + 1 : num1 + num2;
                    if (sum > 0xFFFFFFFFL) carry = true;
                    else carry = false;
                    res.num.add((int) sum);
                }
                for (int i=num.size(); i<other.num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(other.num.get(i));
                    long sum = carry ? num1 + 1 : num1;
                    if (sum > 0xFFFFFFFFL) carry = true;
                    else carry = false;
                    res.num.add((int) sum);
                }
            }
            if (carry) res.num.add(1);
            return res;
        }
        else {
            if (isNegative) return other.subtract(this.magnitude());
            else return this.subtract(other.magnitude());
        }
    }

    @Override
    public BigInteger subtract(BigInteger other) {
        if (isNegative == other.isNegative){
            return new BigInteger();
            // ...
        }
        else{
            if (isNegative) return this.opposite().add(other);
            else return this.add(other.magnitude());
        }
    }

    @Override
    public BigInteger multiply(BigInteger other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }

    @Override
    public BigInteger divide(BigInteger other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'divide'");
    }

    @Override
    public String toString(){
        StringBuilder sb = isNegative ? new StringBuilder("-0x") : new StringBuilder("0x");
        for (int i=num.size()-1; i>=0; --i)
            sb.append(String.format("%08X", Integer.toUnsignedLong(num.get(i))));
        return sb.toString();
    }
}
