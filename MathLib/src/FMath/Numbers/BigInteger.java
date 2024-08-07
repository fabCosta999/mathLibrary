package FMath.Numbers;

import java.util.*;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class BigInteger implements FMathNumber<BigInteger>, Comparable<BigInteger> {
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
            BigInteger res = new BigInteger(isNegative);
            boolean carry = false;

            int cmp = this.compareTo(other);
            if (cmp == 0) return new BigInteger();
            if (cmp < 0){
                for (int i=0; i<other.num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(num.get(i));
                    long num2 = Integer.toUnsignedLong(other.num.get(i));
                    if (carry) --num1;
                    if (num1 < num2){
                        num1 += 1L<<32;
                        carry = true;
                    }
                    res.num.add((int)(num1-num2));
                }
                for (int i=other.num.size(); i<num.size(); ++i){
                    long n = Integer.toUnsignedLong(num.get(i));
                    if (carry){
                        if (n == 0) {
                            carry = true;
                            n = 0xFFFFFFFFL;
                        }
                        else {
                            carry = false;
                            --n;
                        }
                    }
                    res.num.add((int)n);
                }
                res.isNegative = isNegative;
                res.depurate();
                return res;
            }
            else{
                for (int i=0; i<num.size(); ++i){
                    long num1 = Integer.toUnsignedLong(other.num.get(i));
                    long num2 = Integer.toUnsignedLong(num.get(i));
                    if (carry) --num1;
                    if (num1 < num2){
                        num1 += 1L<<32;
                        carry = true;
                    }
                    res.num.add((int)(num1-num2));
                }
                for (int i=num.size(); i<other.num.size(); ++i){
                    long n = Integer.toUnsignedLong(other.num.get(i));
                    if (carry){
                        if (n == 0) {
                            carry = true;
                            n = 0xFFFFFFFFL;
                        }
                        else {
                            carry = false;
                            --n;
                        }
                    }
                    res.num.add((int)n);
                }
                res.isNegative = !isNegative;
                res.depurate();
                return res;
            }
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

    @Override
    public int compareTo(BigInteger other){
        if (isNegative == other.isNegative){
            int size1 = num.size();
            int size2 = other.num.size();
            if (size1 > size2) return isNegative ? 1 : -1;
            if (size2 > size1) return isNegative ? -1 : 1;
            for (int i=size1-1; i>=0; --i){
                long num1 = Integer.toUnsignedLong(num.get(i));
                long num2 = Integer.toUnsignedLong(other.num.get(i));
                if (num1 > num2) return isNegative ? 1 : -1;
                if (num2 > num1) return isNegative ? -1: 1;
            }
            return 0;
        }
        if (!isNegative) return -1;
        return 1;
    }

    private void depurate(){
        int i;
        for (i=num.size()-1; i>0; --i){
            if (num.get(i) != 0) break;
            num.remove(i);
        }
    }
}
