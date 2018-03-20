package co.loli.service.redis.utils.impl;

public interface Function<E,T> {
    T callback(E e);
}
