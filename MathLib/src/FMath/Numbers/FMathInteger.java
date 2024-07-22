package FMath.Numbers;

public class FMathInteger implements FMathNumber<Integer> {
        int value;

        public FMathInteger(int v){ value = v; }

        @Override
        public Integer add(Integer other) {
            return value + other;
        }
        
        @Override
        public Integer subtract(Integer other) {
            return value - other;
        }

        @Override
        public Integer multiply(Integer other) {
            return value * other;
        }

        @Override
        public Integer divide(Integer other) {
            return value / other;
        }
}
