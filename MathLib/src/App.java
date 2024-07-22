
import FMath.Numbers.Complex;

public class App {
    public static void main(String[] args){
        Complex i = Complex.immaginaryUnit();
        Complex n = new Complex(3);
        System.out.println(n.add(i).inverse());
    }
}
