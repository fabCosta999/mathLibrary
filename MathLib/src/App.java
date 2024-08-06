
import FMath.Numbers.BigInteger;

public class App {
    public static void main(String[] args){
        BigInteger n1 = new BigInteger(0xFFFFFFFFL);
        System.out.println(n1);
        BigInteger n2 = new BigInteger(1);
        System.out.println(n2);
        System.out.println(n1.add(n2));
    }
}
