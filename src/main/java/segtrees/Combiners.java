package segtrees;

public class Combiners {
    public static Combiner<Long> sumLongs() {
        return new Combiner<>() {
            @Override
            public Long combine(Long a, Long b) {
                return a + b;
            }

            @Override
            public Long neutral() {
                return 0L;
            }
        };
    }

    public static Combiner<Long> minLongs() {
        return new Combiner<>() {
            @Override
            public Long combine(Long a, Long b) {
                return Math.min(a, b);
            }

            @Override
            public Long neutral() {
                return Long.MAX_VALUE;
            }
        };
    }
}
