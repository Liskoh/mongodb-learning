package io.github.mdb.objects.datas;

import io.github.mdb.repositores.Repository;

public interface IData<T> {

    public T save(Repository<T> repository);
    public T update(Repository<T> repository);
    public void delete(Repository<T> repository);
}
