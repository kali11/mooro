package com.lms.model.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public abstract class GenericDaoImpl<E, PK extends Serializable> extends CustomHibernateDaoSupport
implements GenericDao<E, PK> {

    @Override
    @SuppressWarnings("unchecked")
    public PK save(E newInstance) {
        return (PK) getHibernateTemplate().save(newInstance);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(PK id) {
        return getHibernateTemplate().get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll() {
        return (List<E>) getHibernateTemplate().findByCriteria(createDetachedCriteria());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAllByProperty(String propertyName, Object value) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Restrictions.eq(propertyName, value));
        return (List<E>) getHibernateTemplate().findByCriteria(criteria);
    }

    public List<E> findByExample(E object) {
        List<E> resultList = (List<E>) getHibernateTemplate().findByExample(object, 0, 1);
        return resultList;
    }

    public List<E> findByExample(E object, int firstResult, int maxResults) {
        List<E> resultList = (List<E>) getHibernateTemplate().findByExample(object, firstResult, maxResults);
        return resultList;
    }

    @Override
    public void update(E transientObject) {
        getHibernateTemplate().update(transientObject);
    }

    @Override
    public void saveOrUpdate(E transientObject) {
        getHibernateTemplate().saveOrUpdate(transientObject);
    }

    @Override
    public void delete(E persistentObject) {
        getHibernateTemplate().delete(persistentObject);
    }

    protected abstract Class<E> getEntityClass();

    protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }
}
