package FMath.Numbers;

public interface FMathNumber<T> {
    Number value = null;
    public T add(T other);
    public T subtract(T other);
    public T multiply(T other);
    public T divide(T other);
}
