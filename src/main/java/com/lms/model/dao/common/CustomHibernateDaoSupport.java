package com.lms.model.dao.common;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class CustomHibernateDaoSupport extends HibernateDaoSupport {

    @Autowired
    public void setSession(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
}
