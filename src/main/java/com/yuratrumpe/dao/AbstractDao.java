package com.yuratrumpe.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

@Transactional
public abstract class AbstractDao<T> implements GenericDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    public AbstractDao() {
       this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> loadAll() {
        Session session = sessionFactory.getCurrentSession();
        String genericClassName = persistentClass.toGenericString();
        genericClassName = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);
        String hql = "FROM " + genericClassName;
        return session.createQuery(hql).getResultList();
    }

    @Override
    public T loadById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(persistentClass,id);
    }

    @Override
    public void store(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);

    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(loadById(id));

    }
}
