
import FMath.Numbers.BigInteger;

public class App {
    public static void main(String[] args){
        BigInteger n1 = new BigInteger(-0xFFFFFFFFFFL);
        System.out.println(n1);
        BigInteger n2 = new BigInteger(0xFFFFFFL);
        System.out.println(n2);
        System.out.println(n1.add(n2));
    }
}
