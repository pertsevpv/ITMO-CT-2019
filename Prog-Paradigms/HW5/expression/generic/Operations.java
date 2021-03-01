package expression.generic;

public interface Operations<T extends Number> {

    T add(T a, T b);

    T sub(T a, T b);

    T div(T a, T b);

    T mult(T a, T b);

    T neg(T a);

    T bitCount(T a);

    T max(T a, T b);

    T min(T a, T b);

}
