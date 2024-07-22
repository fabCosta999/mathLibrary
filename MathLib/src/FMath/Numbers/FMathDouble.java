package FMath.Numbers;

public class FMathDouble implements FMathNumber<Double> {
        double value;

        public FMathDouble(double v){ value = v; }

        @Override
        public Double add(Double other) {
            return value + other;
        }
        
        @Override
        public Double subtract(Double other) {
            return value - other;
        }

        @Override
        public Double multiply(Double other) {
            return value * other;
        }

        @Override
        public Double divide(Double other) {
            return value / other;
        }
}