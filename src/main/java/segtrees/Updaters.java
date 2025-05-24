package segtrees;

public class Updaters {
    public static Updater<Long, Long> addLongs() {
        return new Updater<>() {
            @Override
            public Long identity() {
                return 0L;
            }

            @Override
            public Long apply(Long value, Long update, int length) {
                return value + update * length;
            }

            @Override
            public Long compose(Long parent, Long child) {
                return parent + child;
            }
        };
    }

    public static Updater<Long, Long> assignLongs() {
        return new Updater<>() {
            @Override
            public Long identity() {
                return null;
            }

            @Override
            public Long apply(Long value, Long update, int length) {
                return update;
            }

            @Override
            public Long compose(Long parent, Long child) {
                return parent != null ? parent : child;
            }

            @Override
            public boolean isIdentity(Long update) {
                return update == null;
            }
        };
    }
}