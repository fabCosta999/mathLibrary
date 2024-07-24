package FMath.Numbers;

public class Complex implements FMathNumber<Complex> {

    public enum angleType {radians, degrees};

    private double real, immaginary;

    /**
     * Creates a complex number with the given real part
     * @return a complex number with real part set to r and immaginary part set to 0
     */
    public Complex (double r) { 
        real=r; 
        immaginary = 0; 
    }

    /**
     * Creates a complex number with the given real and imaginary parts
     * @return a complex number with real part set to r and immaginary part set to i
     */
    public Complex (double r, double i) { 
        real=r; 
        immaginary = i; 
    }

    /**
     * Provides the immaginary number i
     * @return a complex number with real part set to 0 and immaginary part set to 1
     */
    public static Complex immaginaryUnit(){
        return new Complex(0, 1);
    }

    /**
     * Provides the real number 1
     * @return a complex number with real part set to 1 and immaginary part set to 0
     */
    public static Complex realUnity(){
        return new Complex(1);
    }

    /**
     * Creates a complex number with the given magnitude and phase
     * @return a complex number with real part set to m*cos(p) and immaginary part set to m*sin(p)
     */
    public static Complex magnitudeAndPhase(double m, double p){
        return new Complex(m * Math.cos(p), m * Math.sin(p));
    }

    /**
     * Given the complex number x+iy, computes its magnitude as sqrt(x^2+y^2)
     * @return the magnitude of the number
     */
    public double magnitude(){
        return Math.sqrt(real * real + immaginary * immaginary);
    }

    /**
     * Computes the phase of the complex number in radians [0, 2pi)
     * @return the phase of the number
     */
    public double phase(){
        return phase(angleType.radians);
    }

    /**
     * Computes the phase of the complex number in radians [0, 2pi) or degrees [0, 360)
     * @param t the type of the angle representation: radians or degrees
     * @return the phase of the number
     */
    public double phase(angleType t){
        double x;
        if (real == 0 && immaginary == 0) throw new PhaseError();
        if (real == 0) x = immaginary > 0 ? Math.PI/2 : 3 * Math.PI/2;
        else x = Math.atan(immaginary / real);
        if (real < 0) x -= Math.PI;
        if (x < 0) x += 2*Math.PI;
        return t == angleType.radians ? x : x * 180 / Math.PI; 
    }

    /**
     * Given the complex number x+iy, computes its inverse 1/(x+iy)
     * @return the inverse of the number
     */
    public Complex inverse(){
        return new Complex(real / (real * real + immaginary * immaginary), -immaginary/(real * real + immaginary * immaginary));
    }

    /**
     * Adds two complex numbers togheter
     * @param other the number to add
     * @return the result of the sum
     */
    @Override
    public Complex add(Complex other) {
        return new Complex(real + other.real, immaginary + other.immaginary);
    }

    /**
     * Subtracts another complex number from this number
     * @param other the number to subtract
     * @return the result of the subtraction
     */
    @Override
    public Complex subtract(Complex other) {
        return new Complex(real - other.real, immaginary - other.immaginary);
    }

    /**
     * Multiplies two complex numbers togheter
     * @param other the number to multiply
     * @return the result of the moltiplication
     */
    @Override
    public Complex multiply(Complex other) {
        return new Complex(real * other.real - immaginary * other.immaginary, real * other.immaginary + immaginary * other.real);
    }

    /**
     * Divides this number by another complex number
     * @param other the number to divide by
     * @return the result of the division
     */
    @Override
    public Complex divide(Complex other) {
        return this.multiply(other.inverse());
    }


    @Override
    public String toString(){
        return immaginary > 0 ? real + "+" + immaginary + "i" : real + "" + immaginary + "i";
    }
}
