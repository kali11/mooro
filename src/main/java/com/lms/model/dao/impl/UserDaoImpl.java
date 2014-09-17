package com.lms.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import com.lms.model.dao.UserDao;
import com.lms.model.dao.common.CustomHibernateDaoSupport;
import com.lms.model.entity.User;

@Component
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {

    @Override
    public User getUser(Integer id) {
        Session session = null;// this.sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getUser(String login) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.eq("login", login));
        return (User) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    public void save(User user) {
        getHibernateTemplate().save(user);
    }

}
