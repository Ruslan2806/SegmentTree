package segtrees;

public interface Updater<T, U> {
    U identity();
    T apply(T value, U update, int length);
    U compose(U parent, U child);
    default boolean isIdentity(U update) {
        return update == null || update.equals(identity());
    }
}