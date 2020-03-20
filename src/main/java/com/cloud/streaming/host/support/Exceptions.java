package com.cloud.streaming.host.support;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

public final class Exceptions {
    private Exceptions() {
        throw new UnsupportedOperationException();
    }

    /**
     * returns a value from a lambda (Supplier) that can potentially throw an exception.
     *
     * @param callable Supplier that can throw an exception
     * @param <T>      type of supplier's return value
     * @return a value produces by the callable
     */
    public static <T> T sneak(final Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "'callable' must not be null");
        try {
            return callable.call();
        } catch (final Exception e) {
            return sneakyThrow(e);
        }
    }

    public static <T> T sneak(final Callable<? extends T> callable, Function<Callable<? extends T>, Void> function) {
        Objects.requireNonNull(callable, "'callable' must not be null");
        try {
            return callable.call();
        } catch (final Exception e) {
            function.apply(callable);
            return sneakyThrow(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable, R> R sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
