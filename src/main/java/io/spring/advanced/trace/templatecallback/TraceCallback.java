package io.spring.advanced.trace.templatecallback;

public interface TraceCallback<T> {
    T call();
}
