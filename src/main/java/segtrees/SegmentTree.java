package segtrees;

import java.util.Arrays;

public class SegmentTree<T, U> {
    private final T[] tree;
    private final U[] lazy;
    private final int n;
    private final Combiner<T> combiner;
    private final Updater<T, U> updater;

    public SegmentTree(T[] array, Combiner<T> combiner, Updater<T, U> updater) {
        this.n = array.length;
        this.combiner = combiner;
        this.updater = updater;
        int size = 4 * n;
        this.tree = (T[]) new Object[size];
        this.lazy = (U[]) new Object[size];
        Arrays.fill(lazy, updater.identity());
        build(array, 0, 0, n);
    }

    private void build(T[] array, int node, int lx, int rx) {
        if (rx - lx == 1) {
            tree[node] = array[lx];
            return;
        }
        int mid = (lx + rx) / 2;
        build(array, 2 * node + 1, lx, mid);
        build(array, 2 * node + 2, mid, rx);
        tree[node] = combiner.combine(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public void update(int l, int r, U update) {
        if (l < 0 || r > n || l >= r) {
            throw new IllegalArgumentException("Invalid range");
        }
        update(0, 0, n, l, r, update);
    }

    private void update(int node, int lx, int rx, int l, int r, U update) {
        propagate(node, lx, rx);
        if (rx <= l || lx >= r) return;
        if (lx >= l && rx <= r) {
            applyUpdate(node, update, rx - lx);
            return;
        }
        int mid = (lx + rx) / 2;
        update(2 * node + 1, lx, mid, l, r, update);
        update(2 * node + 2, mid, rx, l, r, update);
        tree[node] = combiner.combine(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public T query(int l, int r) {
        if (l < 0 || r > n || l >= r) {
            return combiner.neutral();
        }
        return query(0, 0, n, l, r);
    }

    private T query(int node, int lx, int rx, int l, int r) {
        propagate(node, lx, rx);
        if (rx <= l || lx >= r) return combiner.neutral();
        if (lx >= l && rx <= r) return tree[node];
        int mid = (lx + rx) / 2;
        T left = query(2 * node + 1, lx, mid, l, r);
        T right = query(2 * node + 2, mid, rx, l, r);
        return combiner.combine(left, right);
    }

    private void propagate(int node, int lx, int rx) {
        if (updater.isIdentity(lazy[node])) return;
        applyUpdate(node, lazy[node], rx - lx);
        if (rx - lx > 1) {
            int mid = (lx + rx) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;
            lazy[leftChild] = updater.compose(lazy[node], lazy[leftChild]);
            lazy[rightChild] = updater.compose(lazy[node], lazy[rightChild]);
        }
        lazy[node] = updater.identity();
    }

    private void applyUpdate(int node, U update, int length) {
        tree[node] = updater.apply(tree[node], update, length);
        if (length > 1) {
            lazy[node] = updater.compose(update, lazy[node]);
        }
    }
}