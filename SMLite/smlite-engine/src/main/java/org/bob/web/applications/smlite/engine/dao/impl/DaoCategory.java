/*
 * The MIT License
 *
 * Copyright 2016 roberto.gatti.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bob.web.applications.smlite.engine.dao.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.engine.dao.AbstractDAO;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;
import org.bob.web.applications.smlite.engine.utils.SMliteDb;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to manage the persistence layer of the category object.
 * 
 * @author roberto.gatti
 */
@Transactional
public class DaoCategory /*extends HibernateDaoSupport*/ implements AbstractDAO<CategoryBean> {
        
    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    @Transactional
    public CategoryBean insert(CategoryBean bean) throws SMLiteDAOException {
        Integer newId = ( Integer ) sessionFactory.getCurrentSession().save( bean ) ;
        bean.setId( newId );
        System.out.println(" Inserita categoria con id: " + newId );
        return bean;
    }
    
    @Override
    @Transactional
    public CategoryBean update(CategoryBean bean) throws SMLiteDAOException {
        //sessionFactory.getCurrentSession().setCheckWriteOperations(false);
        bean.setTmstUpd(new Date());
        sessionFactory.getCurrentSession().update(bean);
        return bean;
        //sessionFactory.getCurrentSession().flush();
    }

    @Override
    @Transactional
    public List<CategoryBean> get(CategoryBean bean) throws SMLiteDAOException {
        if ( bean == null )
        {
            return this.getAllCategories();
        }
        else if ( bean.getId() > 0 )
        {
            return this.getById(bean.getId());
        }
        else
            return this.getByTitle(bean.getTitle());
    }

    @Override
    @Transactional
    public void delete(CategoryBean bean) throws SMLiteDAOException {
        //sessionFactory.getCurrentSession().setCheckWriteOperations(false);
        sessionFactory.getCurrentSession().delete(bean);
        //sessionFactory.getCurrentSession().flush();
    }
    
    private List<CategoryBean> getAllCategories()
    {
        Session cs = this.sessionFactory.getCurrentSession();
        return ( List<CategoryBean> ) cs.getNamedQuery(SMliteDb.NQ_CATEGORIES_ALL_NAME).list();
    }
    
    private List<CategoryBean> getById(int id)
    {
        Session cs = this.sessionFactory.getCurrentSession();
        Query q = cs.getNamedQuery(SMliteDb.NQ_CATEGORIES_BY_ID_NAME).setParameter("id", id);
        return q.list();        
    }
    
    private List<CategoryBean> getByTitle(String title)
    {
        Session cs = this.sessionFactory.getCurrentSession();
        Query q = cs.getNamedQuery(SMliteDb.NQ_CATEGORIES_BY_TITLE_NAME).setParameter("title", title);
        return q.list();
    }
}
