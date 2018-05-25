package com.yuratrumpe.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> loadAll();

    T loadById(Long id);

    void store(T entity);

    void update(T entity);

    void delete(Long id);
}
