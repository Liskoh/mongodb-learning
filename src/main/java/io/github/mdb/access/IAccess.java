package io.github.mdb.access;

public interface IAccess<T> {

        T connect();
        T get();
        void close();
}
