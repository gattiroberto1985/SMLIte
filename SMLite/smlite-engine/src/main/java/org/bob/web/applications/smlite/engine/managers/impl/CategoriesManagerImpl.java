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
package org.bob.web.applications.smlite.engine.managers.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.engine.dao.AbstractDAO;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;
import org.bob.web.applications.smlite.engine.managers.AbstractManager;

/**
 *
 * @author roberto.gatti
 */
public class CategoriesManagerImpl implements AbstractManager<CategoryBean> {

    //@Autowired
    private AbstractDAO daoCategory = null;
    
    public void setDaoCategory(AbstractDAO daoCategory) {
        this.daoCategory = daoCategory;
    }
    
    public CategoriesManagerImpl(AbstractDAO dao)
    {
        System.out.println("CREATING NEW CATEGORIESMANAGERIMPL");
        this.daoCategory = dao;
    }
    
    /**
     * Bulk insert method for categories.
     * 
     * @param categories a List of CategoryBean to insert
     * 
     * @return the list with all the new objects
     * 
     * @throws SMLiteDAOException if something goes wrong
     */
    /*public List<CategoryBean> bulkInsert(/ *@NotNull* / List<CategoryBean> categories) throws SMLiteDAOException
    {
        List<CategoryBean> result = new ArrayList<CategoryBean>(categories.size());
        for ( CategoryBean cat : categories ) 
        {
            result.add( this.insert( cat ) ) ;
        }
        return result;
    }*/
    
    
    // <editor-fold desc="AbstractManager IMPLEMENTATION" >
    
    @Override
    @Transactional
    public CategoryBean insert(CategoryBean bean) throws SMLiteDAOException 
    {
        return ( CategoryBean) this.daoCategory.insert(bean);
    }

    @Override
    @Transactional
    public CategoryBean update(CategoryBean bean) throws SMLiteDAOException {
        return ( CategoryBean) this.daoCategory.update(bean);
    }

    @Override
    @Transactional
    public List<CategoryBean> get(CategoryBean bean) throws SMLiteDAOException {
        return this.daoCategory.get(bean);
    }

    @Override
    @Transactional
    public void delete(int id) throws SMLiteDAOException {
        this.daoCategory.delete(id);
    }
    
    //</editor-fold>
    
    
}
