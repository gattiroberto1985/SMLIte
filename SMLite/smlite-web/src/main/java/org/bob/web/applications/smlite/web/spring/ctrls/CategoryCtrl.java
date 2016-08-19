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
package org.bob.web.applications.smlite.web.spring.ctrls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bob.web.applications.smlite.engine.beans.CategoryBean;
import org.bob.web.applications.smlite.engine.exceptions.SMLiteDAOException;
import org.bob.web.applications.smlite.engine.managers.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author roberto.gatti
 */
@Controller
public class CategoryCtrl
/*extends AbstractController*//* implements ApplicationContextAware */{
    
    //private ApplicationContext context;
    
    @Autowired
    private AbstractManager<CategoryBean> categoriesManager;

    @RequestMapping(value = "/web/categories", method = RequestMethod.GET)
    public ModelAndView getCategories(@ModelAttribute CategoryBean category)
    {
        List<CategoryBean> cs = new ArrayList<CategoryBean>();
        ModelAndView model = new ModelAndView("categories");
        try
        {
            //AbstractManager<CategoryBean> cm = (AbstractManager<CategoryBean>) ctx.getBean("categoriesManager");
            cs = categoriesManager.get(null);
        }
        catch (SMLiteDAOException ex)
        {
            Logger.getLogger(CategoryCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addObject("msg", "I am the message!");
        model.addObject("categories", cs);
        model.addObject("category", category);
        return model;
    }

    @RequestMapping(value = "/web/categories", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute CategoryBean category)
    {
        try
        {
            this.categoriesManager.insert(category);
        }
        catch (SMLiteDAOException ex)
        {
            Logger.getLogger(CategoryCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:categories";
    }
    
    /*@Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello world");
        return model;
    }*/
 /*public void setContext(ApplicationContext ctx)
    {
        this.context = ctx;
    }*/
    /*public void setCategoriesManager(AbstractManager am)
    {
        this.categoriesManager = am;
    }*/

    /*@Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException
    {
        this.context = ac;
    }*/
}
