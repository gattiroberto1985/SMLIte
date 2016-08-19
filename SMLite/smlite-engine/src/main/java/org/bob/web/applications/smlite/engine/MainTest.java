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
package org.bob.web.applications.smlite.engine;

import java.util.List;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;
import org.bob.web.applications.smlite.engine.managers.AbstractManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author roberto.gatti
 */
public class MainTest {
    
    private static final MainTest INSTANCE = new MainTest();
    
    private ClassPathXmlApplicationContext ctx;
    
    public static MainTest getInstance() {
        return MainTest.INSTANCE;
    }
    
    private MainTest() {
        this.ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    public List<CategoryBean> getCategories() throws SMLiteDAOException
    {
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        return ( List<CategoryBean> ) cm.get(null);
    }
    
    public CategoryBean addCategory(CategoryBean bean) throws SMLiteDAOException
    {
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        return cm.insert(bean);        
    }

    public CategoryBean updateCategory(CategoryBean bean) throws SMLiteDAOException
    {
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        return cm.update(bean);        
    }

    public void deleteCategory(int id) throws SMLiteDAOException
    {
        AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
        cm.delete(id);        
    }
    
}
